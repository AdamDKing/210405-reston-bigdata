package com.revature.introdemos

/** Entrypoint to our Scala application
  */
object Main {
  // Everything inside of our object that extends App will just run.
  /*
  multi line comment
  println("Hello, World!")
   */

  //main method is the traditional entrypoint
  def main(args: Array[String]): Unit = {
    //a little interesting, methods that don't take arguments can be called without ():
    // this has a fancy name: Uniform Access Principle
    //Basics.run
    //StackHeap.run
    //FunctionsOperators.run
    //Options.run
    QuickExceptions.run
  }
}
