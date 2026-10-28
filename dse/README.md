# MaxFinder design space exploration

Generate three implementations of the same function: the maximum of eight unsigned 16-bit values. Compare a chain, a balanced tree, and a pipelined tree using LibreLane. All three use **one configuration and one clock target: 20 ns (50 MHz)**.

## Generate the Verilog

From this directory, using the course's Java/sbt setup:

```sh
sbt test
make generate
```

`Generate` writes `MaxFinder.sv` into `build/rtl/chain/`, `build/rtl/tree/`, and `build/rtl/pipeline/`. The `generate` target invokes `sbt 'runMain Generate build/rtl'`. Run it again after changing the Scala generator; the LibreLane targets do not regenerate automatically.

## Run LibreLane

Enter your installed LibreLane environment. On the teaching machine:

```sh
nix-shell ~/librelane/shell.nix
make
```

Or run one design at a time:

```sh
make chain
make tree
make pipeline
```

The single `config.yaml` supplies the settings for every run. The Makefile selects the generated RTL directory using `--design-dir` and stops at `OpenROAD.STAPrePNR`, after synthesis and static timing analysis. The PDK defaults to `~/.ciel`; use `make PDK_ROOT=/path/to/pdks` if needed. LibreLane and SKY130 should be installed before the lab.

Each design has its own `build/rtl/<architecture>/runs/timing/` directory. Rerunning a target **overwrites that design's previous `timing` run**. Copy reports elsewhere first if you want to keep a comparison.

## Explore the timing reports

Print the estimated minimum clock period and maximum frequency for all three architectures and all analyzed corners:

```sh
make reports
```

For example, open the tree's slow-corner clock report:

```sh
less build/rtl/tree/runs/timing/*-openroad-staprepnr/nom_ss_100C_1v60/clock.rpt
```

Near the end, find `report_clock_min_period`:

```text
clock period_min = 15.48 fmax = 64.58
```

In this SKY130 setup, the period is in **ns** and fmax in **MHz**: `1000 / 15.48 ≈ 64.6 MHz`. This is OpenSTA's estimated clock limit for the mapped circuit at that corner, not simply the requested 50 MHz. Compare the **lowest fmax across the analyzed corners**, not just the typical corner. Corner names can differ with PDK versions; `make reports` shows the actual paths.

Then inspect the overall timing summary and a critical setup path:

```sh
less build/rtl/tree/runs/timing/*-openroad-staprepnr/summary.rpt
less build/rtl/tree/runs/timing/*-openroad-staprepnr/nom_ss_100C_1v60/max.rpt
```

In `max.rpt`, identify the startpoint, endpoint, cell delays, data arrival time, required time, and slack. How many compare/select levels are on the path? Does synthesis preserve the structure you expected? The summary also reports hold, slew and capacitance violations. A completed early flow is not a guarantee that all constraints passed.

This fmax estimate comes from register-to-register clock timing under the configured constraints. It does not prove IO timing, hold timing or physical signoff. We have not placed or routed the circuit; wire parasitics and clock-tree implementation can change the result. Use the reported minimum-period analysis rather than converting arbitrary overall slack into fmax.

## Compare the architectures

| Architecture | Comparator levels per stage | Register banks on a path | Acceptance-to-output latency |
| --- | --- | --- | --- |
| chain | 7 | 2 | 1 cycle |
| tree | 3 | 2 | 1 cycle |
| pipeline | 1 | 4 | 3 cycles |

All variants sample one vector on every rising clock edge. An input sampled at edge k appears after edge k+1 for chain/tree and k+3 for pipeline. There is no valid signal or reset logic; ignore the output until the data pipeline has filled.

At the fixed 50 MHz target, a timing-passing design sustains 50 million vectors/s. Pipelining does not increase throughput at the same clock; it can allow a higher clock, at the cost of registers and more cycles of latency. Use fmax to discuss that potential without claiming we ran a second clock target.

Before running, predict which architecture is fastest and smallest. Then compare the clock reports, inspect one critical path, and explain the difference. See [VALIDATION.md](VALIDATION.md) for the measured teaching-machine results and `example-results/` for saved reports.

[LibreLane timing closure documentation](https://librelane.readthedocs.io/en/latest/usage/timing_closure/index.html)

## Chisel tree reduction

The generator uses Chisel's `Vec.reduceTree`:

```scala
// Balanced combinational tree, then an output register
io.out := RegNext(inputs.reduceTree(maximum))

// Alternative: register every compare/select result
io.out := inputs.reduceTree((a, b) => RegNext(maximum(a, b)))
```

`inputs` is a `VecInit` of input registers. The power-of-two requirement gives every leaf the same depth; the `layerOp` callback is not needed here. That callback handles unpaired values promoted through a reduction level, rather than registering every combined result.
