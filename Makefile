# Chisel sample makefile
# written by Kazutomo Yoshii <kazutomo.yoshii@gmail.com>

T=Foo
M=test

all:
	@echo "Use run.sh for testing, simulating and generating verilog codes"

clean:
	rm -rf project target test_run_dir generated *.class
	rm -rf *.fir *.anno.json *.v
