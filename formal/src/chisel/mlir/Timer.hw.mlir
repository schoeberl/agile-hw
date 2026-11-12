module {
  hw.module @Timer(in %clock : !seq.clock, in %reset : i1, out io_done : i1) {
    %c-1_i3 = hw.constant -1 : i3
    %c1_i3 = hw.constant 1 : i3
    %c0_i3 = hw.constant 0 : i3
    %count = seq.firreg %0 clock %clock reset sync %reset, %c0_i3 : i3
    %0 = comb.add bin %count, %c1_i3 {sv.namehint = "_count_T"} : i3
    %1 = comb.icmp bin eq %count, %c-1_i3 {sv.namehint = "io_done"} : i3
    hw.output %1 : i1
  }
}
