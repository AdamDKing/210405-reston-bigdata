package com.revature.introdemos

import java.io.FileNotFoundException

object QuickExceptions {

  def run() = {
    println("start run")
    try {
      f1()
    } catch {
      case fnfe: FileNotFoundException => {
        println(fnfe.getMessage())
        println("in catch block for fnf exception!")
      }
      case e: Exception => {
        println(e.getMessage())
        println("in catch block for general Exception -- dangerous!")
      }
    }
    println("end run")
  }

  def f1() = {
    println("start f1")
    f2()
    println("end f1")
  }

  def f2() = {
    //Array(1,2,3)(50) //array index out of bounds exception
    //4 / 0 //arithmetic exception
    //throw new StackOverflowError("dont do this, but Errors use the same machinery: Error and Exception are both subclasses of Throwable")
    throw new ArithmeticException("hello!")
  }
}