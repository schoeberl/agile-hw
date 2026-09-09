# Chisel Generator with Generics

Chisel can use generics to create more flexible and reusable hardware components (like the `Mux` component in Chisel). By parameterizing your modules with type parameters, you can easily adapt them to different data types and widths.

## Example

Here's an example of the syntax for a generic Chisel module:

```scala
class GenericComponent[T <: Data](gen: T) extends Module {
  val io = IO(new Bundle {
    val in = Input(gen)
    val out = Output(gen)
  })

  ???
}
```

In this example, `GenericComponent` can be instantiated with any data type that extends `Data`, allowing for a wide range of use cases.

## Exercise

Implement a generic component that performs a simple operation. Add tests to verify its functionality with different data types.

Feel free to decide on your own component. However, if you need some ideas:
- A generic 4:1 multiplexer
- A generic buffer with read/write signals and indication of empty/full
- A generic FIFO queue with configurable depth (a bit advanced, could use the buffer from above)

* Emit Verilog for your design to synthesize it to an FPGA or ASIC.