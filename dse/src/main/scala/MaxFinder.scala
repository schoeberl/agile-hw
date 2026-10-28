import chisel3._
import circt.stage.ChiselStage
import java.nio.file.{Files, Paths}

/** Same unsigned maximum, different elaboration-time architectures. */
class MaxFinder(n: Int, width: Int, architecture: String) extends Module {
  require(n >= 2 && (n & (n - 1)) == 0, "n must be a power of two >= 2")
  require(width > 0)
  require(Set("chain", "tree", "pipeline")(architecture))
  val io = IO(new Bundle {
    val in = Input(Vec(n, UInt(width.W)))
    val out = Output(UInt(width.W))
  })
  def maximum(a: UInt, b: UInt): UInt = Mux(a > b, a, b)
  val inputs = VecInit(io.in.map(x => RegNext(x)))

  if (architecture == "chain") {
    io.out := RegNext(inputs.reduceLeft(maximum))
  } else if (architecture == "tree") {
    io.out := RegNext(inputs.reduceTree(maximum))
  } else {
    // Register every compare/select result: one register per tree level.
    // The power-of-two input count gives every leaf the same depth.
    io.out := inputs.reduceTree((a, b) => RegNext(maximum(a, b)))
  }
}

object Generate extends App {
  require(args.length == 1, "Usage: runMain Generate <output directory>")
  // Hold the workload constant: eight unsigned 16-bit values per transaction.
  val n = 8
  val width = 16
  for (architecture <- Seq("chain", "tree", "pipeline")) {
    val dir = Paths.get(args(0), architecture)
    Files.createDirectories(dir)
    val rtl = ChiselStage.emitSystemVerilog(
      new MaxFinder(n, width, architecture),
      firtoolOpts = Array("-disable-all-randomization", "-strip-debug-info",
        "--lowering-options=disallowLocalVariables,disallowPackedArrays"))
    Files.writeString(dir.resolve("MaxFinder.sv"), rtl)
  }
}
