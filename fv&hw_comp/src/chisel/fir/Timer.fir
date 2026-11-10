circuit Timer :
  public module Timer :
    input clock : Clock
    input reset : UInt<1>
    output io : { done : UInt<1> }

    regreset count : UInt<3>, clock, reset, UInt<3>(0h0)
    connect count, tail(add(count, UInt<1>(0h1)), 1)
    connect io.done, gt(count, UInt<3>(0h6))
