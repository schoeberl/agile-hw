package timer

import scala.math._
import chisel3._
import chisel3.util._
import chisel3.ltl._
import chiseltest._
import chiseltest.formal._
import _root_.circt.stage.ChiselStage


/// Timer circuit generator, that counts up to a given parameter n
/// When n is reached, the counter emits a done signal that remains
/// high so long as the circuit isn't reset.
/// @param n: Int | 0 < n < 2^32
class Timer(n : Int) extends Module {
    val io = IO(new Bundle{
        val done = Output(Bool())
    })
    // sanity check
    require(n > 0)

    // compute the smallest width to store n
    val width: Int = log2Ceil(n)
    val count = RegInit(0.U(width.W))

    // increment counter every cycle
    count := count + 1.U

    // emit done when count surpasses n
    io.done := (count > (n - 1).U)

    // Spec: count is monotonically increasing
    // Spec: when count is n we should be done 
    val pastCount = RegNext(count)
    AssertProperty(pastCount < count)
    AssertProperty(!(count === n.U) || io.done)
}

object TimerTop extends App with Formal {
  // Test the generator with manual inputs
  for {i <- 1 until 256} {
      verifyRes(new Timer(i), Seq(
          BoundedCheck(500),
          BTORMCBackend
      )) match {
        case Sat(w) => println(s"Found a counter example for n = ${i}")
        case Unsat => println(s"Design matches specification for n = ${i}")
      }
  }
//   println(ChiselStage.emitCHIRRTL(
//     new Timer(7)
//   ))
//
//   println(ChiselStage.emitFIRRTLDialect(
//     new Timer(7),
//     firtoolOpts = Array("-disable-all-randomization", "-strip-debug-info", "-default-layer-specialization=enable")
//   ))
//
//   println(ChiselStage.emitHWDialect(
//     new Timer(7),
//     firtoolOpts = Array("-disable-all-randomization", "-strip-debug-info", "-default-layer-specialization=enable")
//   ))
//
//   println(ChiselStage.emitBtor2(
//     new Timer(7),
//     firtoolOpts = Array("-disable-all-randomization", "-strip-debug-info", "-default-layer-specialization=enable")
//   ))
}
