import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec
import scala.collection.mutable
import scala.util.Random

class MaxFinderTest extends AnyFlatSpec with ChiselScalatestTester {
  for (n <- Seq(4, 8); architecture <- Seq("chain", "tree", "pipeline")) {
    s"$architecture with $n inputs" should "produce one maximum per cycle" in {
      test(new MaxFinder(n, 16, architecture)) { dut =>
        val stages = if (architecture == "pipeline") 1 + Integer.numberOfTrailingZeros(n) else 2
        val expected = mutable.Queue[BigInt]()
        val random = new Random(42)
        for (cycle <- 0 until 100 + stages) {
          val values = Seq.tabulate(n) { i =>
            if (cycle < n) { if (i == cycle) BigInt(65535) else BigInt(0) }
            else if (cycle == n || cycle >= 100) BigInt(0)
            else if (cycle == n + 1) BigInt(65535)
            else BigInt(16, random)
          }
          dut.io.in.zip(values).foreach { case (port, value) => port.poke(value.U) }
          expected.enqueue(values.max)
          dut.clock.step()
          // Ignore the uninitialized output until the pipeline has filled.
          if (expected.size >= stages) dut.io.out.expect(expected.dequeue().U)
        }
      }
    }
  }
}
