package foobar

import chisel3._

class Foo(val bw:Int = 16) extends Module {
  val io = IO(new Bundle {
    val in  = Input(UInt(bw.W))
    val out = Output(UInt(bw.W))
  })

  val fReg = RegInit(0.U(bw.W))

  fReg := io.in  

  io.out := fReg
}
