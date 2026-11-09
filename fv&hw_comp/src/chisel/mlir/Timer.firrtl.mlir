module {
  firrtl.circuit "Timer" {
    firrtl.module @Timer(
      in %clock: !firrtl.clock, in %reset: !firrtl.uint<1>,
      out %io_done: !firrtl.uint<1>
    ) {
      %c6_ui3 = firrtl.constant 6 : !firrtl.uint<3>
      %c1_ui1 = firrtl.constant 1 : !firrtl.uint<1>
      %c0_ui3 = firrtl.constant 0 : !firrtl.uint<3>

      %io_done_0 = firrtl.wire {name = "io_done"} : !firrtl.uint<1>
      firrtl.matchingconnect %io_done, %io_done_0 : !firrtl.uint<1>

      %count = firrtl.regreset %clock, %reset, %c0_ui3 : !firrtl.uint<3>
      %_count_T = firrtl.add %count, %c1_ui1 : !firrtl.uint<4>
      %_count_T_1 = firrtl.bits %_count_T 2 to 0 : !firrtl.uint<3>
      firrtl.matchingconnect %count, %_count_T_1 : !firrtl.uint<3>

      %_io_done_T = firrtl.gt %count, %c6_ui3 : !firrtl.uint<1>
      firrtl.matchingconnect %io_done_0, %_io_done_T : !firrtl.uint<1>
    }
  }
}
