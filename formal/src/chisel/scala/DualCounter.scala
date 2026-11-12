package dualCounter

import chisel3._
import chiseltest._
import chiseltest.formal._
import org.scalatest.flatspec.AnyFlatSpec
import chisel3.ltl.AssertProperty

class DualCounter extends Module {
  val io = IO(new Bundle {
    val en = Input(Bool())
  })

  val prevCount = RegInit(0.U(8.W))
  val count = RegInit(1.U(8.W))

  when(io.en) {
    prevCount := count
    count := count + 1.U
  }

  // check that the register is monotonically increasing
  AssertProperty(!io.en || count > prevCount)
}

class DoubleCounterTest extends AnyFlatSpec with ChiselScalatestTester with Formal {
  behavior of "DualCounter"

  it should "return unsat on k < 256" in {
    val res = verifyRes(
      new DualCounter,
      Seq(
        BoundedCheck(25),
        BTORMCBackend
      )
    )

    assertResult(Unsat)(res)
  }

  it should "return SAT on k > 256" in {
    val res = verifyRes(
      new DualCounter,
      Seq(
        BoundedCheck(256),
        BTORMCBackend
      )
    )
    assert(res match { case Sat(_) => true; case _ => false })
  }
}
