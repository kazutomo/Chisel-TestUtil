package foobar

import chisel3.util._
import chisel3.iotesters
import chisel3.iotesters.{Driver, PeekPokeTester}
import testutil._

class FooUnitTester(c: Foo) extends PeekPokeTester(c) {

  val nsteps = 10

  poke(c.io.en, 1)
  for (i <- 0 until nsteps) {
    expect(c.io.out,  i)
    expect(c.io.out2, i)
    printf("enabled cnt : %08d\n",  peek(c.io.out).toInt)
    step(1)
  }

  poke(c.io.en, 0)
  for (i <- 0 until nsteps) {
    expect(c.io.out,  nsteps)
    expect(c.io.out2, nsteps)
    printf("disabled cnt: %08d\n",  peek(c.io.out).toInt)
    step(1)
  }
}

object FooTest {

  def run(args: Array[String]) {
    val (argsrest, opt) = TestUtil.getopts(args,
      Map("bw" -> "16") )

    val bwval = opt("bw").toInt
    println("FooTest: bitwidth=" + bwval)

    val dut = () => new Foo(bwval)
    val tester = c => new FooUnitTester(c)

    TestUtil.driverhelper(argsrest, dut, tester)
  }
}
