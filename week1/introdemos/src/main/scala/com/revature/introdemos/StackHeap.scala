package com.revature.introdemos

object StackHeap {
  def run(): Unit = {
    //start with just a stack exploration
    third()
  }

  def first(): Unit = {
    val oneApple = new Apple()
    val twoApple = new Apple("red")
    val threeApple = new Apple("yellow")
    // List is a handy way to store multiple apples
    val appleList = List(oneApple, twoApple, threeApple)
    println(appleList)
    oneApple.color = "blue"
    fourth(twoApple, threeApple)
    println(appleList)
  }

  def second(i: Int): Int = {
    i*i
  }

  def third(): Unit = {
    val i = 20
    first()
    // it is at this point that the 3 apples created in our first() call
    // are eligible for garbage collection
    // object on the Heap become eligible for garbage collection when no more references to them exist
    // the next time the garbage collector runs, it will remove those objects to free up space
    println("hello") 
    import adamsstuff.Cool
    Cool.printSomething()
  }

  def fourth(a: Apple, b: Apple) {
    b.rot()
  }

}

/**
  * Basic Apple class, with default color green
  *
  * @param color
  */
class Apple(var color:String = "green") {
  //In Java, the first thing a constructor does is call the parent class's constructor
  // this means every constructor executes a call to the constructor for the class at the root of the
  // class hierarchy (Object in Java), this is how objects are created on the Heap.

  def rot(): Unit = {
    this.color = "brown"
  }

  override def toString(): String = {
    s"a(n) $color apple"
  }

}