package com.revature.introdemos

object FunctionsOperators {
  def run(): Unit = {
    //Scala supports FP, which means functions are first class citizens.  We can pass functions into other functions
    // and we can return functions from other functions.

    //There are a few ways to define functions: def, lambdas, shorthand with _
    def func1(x: Int): Int = {
      x + 3
    }

    println(func1(5))

    //a lambda is an inline, anonymous function.  We can store a lambda in a variable, which makes it work
    // similar to def
    var func2 = (x: Int) => { x + 7 }

    println(func2(5))
    //we don't need to store lambdas to use them:
    println(((x: Int) => { x + 11 })(5))

    //we're rarely going to immediately invoke lambdas like ^
    // instead, we'll use lambads when we pass a function into another function
    //start off with a List.foreach, which calls a function passing in each element of the List:
    // add a call to .map before the .foreach to transform the list
    List(1, 2, 3, 4, 5).map(func2).foreach(println)
    // lambdas instead:
    List(1, 2, 3, 4, 5)
      .map((x: Int) => { x + 7 })
      .foreach((x: Int) => { println(s"The number is $x") })

    // _ shorthand:
    List(1, 2, 3, 4, 5)
      .map(_ + 7)
      .foreach((x: Int) => { println(s"The number is $x") })

    // multiply each element in a List by 3, then subtract 5:
    List(1, 2, 3, 4, 5)
      .map(_ * 3 - 5)
      .foreach(println)

    //subtract 2 from each element, filter out elements > 1, then print
    List(1, 2, 3, 4, 5)
      .map(_ - 2)
      .filter(_ <= 1)
      .foreach(println)

    //can return functions from other functions, we did this in the REPL:
    def adderProducer(y: Int) = { (x: Int) =>
      { x + y }
    }

    val addFive = adderProducer(5)
    println(addFive(5))
    List(1, 2, 3, 4, 5)
      .map(adderProducer(20))
      .foreach(println)

    //Operators in Scala like +, -, *, ! , ...
    // work a little differently.  There aren't many real operators in scala, almost all the operators we
    // use are in fact methods.
    // We're allowed to write methods that take one argument as if they were operators, even if the method
    // name is a word/phrase.
    var x = 5
    println(x.+(3))
    println(x + 3) // same logic, operator syntax
    println(x.equals(5))
    println(x equals 5) //same logic, operator syntax
    

    class MagicNum(var value: Int) {

      def +(other: MagicNum) = {
        //define our own custom MagicNum addition here: sum and then subtract 3
        this.value + other.value - 3
      }

      override def toString() = {
        value.toString
      }
    }

    var m1 = new MagicNum(5)
    var m2 = new MagicNum(7)
    println(m1 + m2)

    //reduce takes a function that takes 2 arguments
    // the reducing function is called repeatedly, passing in the result of the prior reduction
    // and the next element.  In this way the entire collection is reduced to a single value
    println(List(1,2,3,4,5).reduce((x:Int, y:Int) => {
      println(s"$x + $y = ${x+y}")
      x+y
    }))
    // Adam's guess for the operations:
    // 1 + 2 => 3
    // 3 + 3 => 6
    // 6 + 4 => 10
    // 10 + 5 => 15

    //another reduce for max
    println(List(1,2,3,6,4,5).reduce((x:Int, y:Int) => {
      println(s"$x , $y")
      //if is an expression in scala.  This means it returns a value.
      // we don't have the ternary operator ( ? : ) in Scala, but if-expressions work similarly
      if (x>y) x else y
    }))

    println(List(1,2,6,3,4,5).reduce(_ max _))
    //(x:Int,y:Int) => {x max y}
    //most functions above are pure functions, except the ones that print
    // printing to the console is a side effect, which makes the function impure
    // a pure function takes only arguments as input, produces only return values as output
    // has no other effects and reads from nowhere else other than its argument(s)

  }
}
