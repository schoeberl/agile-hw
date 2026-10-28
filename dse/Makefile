PDK_ROOT ?= $(HOME)/.ciel

.PHONY: all generate chain tree pipeline reports
all: chain tree pipeline

generate:
	sbt 'runMain Generate build/rtl'

# Run make generate before the LibreLane targets.
chain tree pipeline:
	librelane --pdk-root "$(PDK_ROOT)" --design-dir build/rtl/$@ \
		--run-tag timing --overwrite --to OpenROAD.STAPrePNR config.yaml

reports:
	grep -H 'period_min' build/rtl/*/runs/timing/*-openroad-staprepnr/*/clock.rpt
