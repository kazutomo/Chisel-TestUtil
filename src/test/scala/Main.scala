//
// test driver demo
//
// written by Kazutomo Yoshii <kazutomo.yoshii@gmail.com>
// 
package foobar

import testutil._

object TestMain extends App {

  if (args.length < 2) {
    println("Usage: foobar.TestMain command target [options]")
    println("")
    System.exit(1)
  }

  val args2 = args.drop(2) // drop the command

  // default params and component target list
  // key is the name of target module
  // value contains the run method function and the description
  val targetmap = Map(
    "Foo"             -> (() => FooTest.run(args2), "dummy")
  )

  TestUtil.launch(args, targetmap)
}

