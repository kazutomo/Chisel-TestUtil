package foobar

import chisel3._

class SimpleCounter(val bw:Int = 16) extends Module {
  val io = IO(new Bundle {
    val out = Output(UInt(bw.W))
  })

  val cntReg = RegInit(0.U(bw.W))
  cntReg := cntReg + 1.U

  io.out := cntReg
}

class Foo(val bw:Int = 16) extends Module {
  val io = IO(new Bundle {
    val en = Input(Bool())
    val out = Output(UInt(bw.W))
    val out2 = Output(UInt(bw.W))
  })

  val gatedclock = (clock.asUInt()(0) & io.en).asClock()

  withClock(gatedclock) {
    val sc = Module(new SimpleCounter(bw))
    io.out := sc.io.out
  }
  // withClockAndReset for both clock and reset

  withClock(gatedclock) {
    val cntReg = RegInit(0.U(bw.W))
    cntReg := cntReg + 1.U
    io.out2 := cntReg
  }
}
