package com.revature.introdemos

object Options {
  //access modifiers:
  //default is public
  private val secrets = "this is only accessible inside this object"
  protected val lessSecretSecrets =
    "this is only accessible in this object, subclasses" //different from Java!

  def run(): Unit = {
    // Option is a useful type in Scala, that works like a container that may or may not hold a value
    // Option lets us avoid using null (no object) in many cases, and allows us to write functions
    // that optionally return a value

    var possibleString: Option[String] = Some(
      "hello from the Option!"
    ) //use Some to provide a value
    var possibleInt: Option[Int] = Some(33)
    var possibleDouble: Option[Double] = None

    println(possibleString)
    println(possibleInt)
    println(possibleDouble)

    var myString: String = possibleString.get
    if (!possibleInt.isEmpty) println(possibleInt.get)
    println(myString)

    // Return an optional value
    def possibleStringProducer(condition: Boolean): Option[String] = {
      if (condition) Some("heres your string") else None
    }

    // Work with a List of Options, use match case to extract values
    List(1, 2, 3, 4, 5)
      .map((x: Int) => {
        if (x % 2 == 1) Some(x) else None
      })
      .flatten
      .foreach(println)
    // flatten takes nested datastructures and collapses them down to a single layer:
    // List[List[Int]] -> List[Int]
    // List[Option[Int]] -> List[Int]

    // we can shorten a call to .map followed by a call to .flatten by using .flatmap

    // Option is very useful for FP style code, because Option allows us to have functions
    // fail to return without returning null (which may break future functions) and without
    // throwing an Exception (which is a side effect)

    List(1, 2, 3, 4, 5)
      .map((x: Int) => {
        if (x % 2 == 1) Some(x) else None
      })
      .foreach((possibleInt: Option[Int]) => {
        possibleInt match {
          case Some(x) => { println(s"number: $x") }
          case None    => { println("no number found") }
        }
      })

    //quick flatmap to get all characters from a List[String]:
    val vowels = Set("a", "e", "i", "o", "u")
    List("the", "quick", "brown", "fox", "jumped")
      .flatMap(_.split("")) //flatmap allows us to increase the number of elements in a collection while transofrming it 5 -> 22
      .filter(!vowels.contains(_)) //filter out vowels
      .foreach(println)

  }
}
