//Define an object: Basics.  The object keyword creates a singleton object,
// meaning there is only one object of that type.
// The object keyword replaces the functionality of the static keyword in Java:
// coming from Java, static members of a class are associated with the class itself rather than instances of the class
// in Scala, we write companion objects for our classes that contain members associated with the class itself.

/** Basics scaladoc!
  */
object Basics {

  /** this is the run method, defined in the Basics singleton object
    * run takes no arguments and returns nothing, which we signify with
    * the Unit return type
    */
  def run(): Unit = {
    println("inside of Basics.run")

    //define variables with var (mutable) and val (immutable)
    // no need to declare type if it can be inferred
    var myVariable = 4
    val myValue = 5

    myVariable = 10
    //myValue = 20 compilation failure

    //template literals let us interpolate values into strings:
    println("sum of myValue and myVariable: " + (myValue + myVariable))
    println(s"sum of myValue and myVariable: ${myValue + myVariable}")
    println(s"template literal fun: $myValue, ${1 + 2 + 3}, $myVariable")

    // Some Scala types to know:
    // Integral types: Byte, Short, Int, Long, Char
    // Char is unicode, 2 bytes, positive values only.
    // Byte is 1 byte, short is 2, int is 4, long is 8
    println(s"Byte in Scala: max: ${Byte.MaxValue}, min: ${Byte.MinValue}")
    println(s"Short in Scala: max: ${Short.MaxValue}, min: ${Short.MinValue}")
    // We'll mostly use the default, which is Int
    var myIntegralVariable = 37
    // Know about the others in case we have a large array/data structure.

    // to write a long literal, append an L
    var myLargeIntegralValue = 2000000000000L

    //Decimal types: Float and Double
    //float is 4 bytes, double is 8 bytes
    //float is precise to ~5 digits, double is precise to ~15
    // default is double, write float literals with an "f"
    val myDouble = 44.44
    val myFloat = 44.44f

    // Boolean: true or false
    var myBool = true
    myBool = !myBool
    println(myBool)

    // All of these types, and all the types we see in Scala, are subclasses of Any
    // Any fulfills a similar role to Object in java
    // AnyVal and AnyRef are immediate subclasses of Any, with AnyVal being analogous to primitive types
    // in Java and AnyRef being analogous to Object

    // Strings in scala are similar to Strings in java.
    // String is an object, they are immutable, their values cannot be changed, but they can be used to produce new strings
    // the value is stored as an array of chars under the hood
    // Strings have a bit of special behaviour with the String Pool, which we'll hit when we discuss stack + heap
    var myString: String = "Hello world"

    // We'll try exploring flow control via scala book this afternoon.  Let's jump to JVM internals, since they're coming up.

    // real quick before we do that, similar to Java, classes are used to create instances (objects) using a constructor:
    // name of the class, followed by parentheses (and constructor arguments) and preceded by the new keyword
    val basicsInstance = new Basics("blue", 44)
    val secondBasicsInstance = new Basics("echidna", 100)
    // we have only references to objects on the heap
    // this copies the reference stored in basicsInstance into a variable basicsReferenceNumberTwo
    var basicsReferenceNumberTwo = basicsInstance

    val deepCopy = new Basics(basicsInstance.name, basicsInstance.luckyNumber)

    // What is printed out below?
    basicsReferenceNumberTwo.name = "mystery"
    
    println(deepCopy)

    // two different instance of the Basics class
    println(basicsInstance.equals(secondBasicsInstance))

    println(s"basicsInstance name is ${basicsInstance.name}")
    //basicsInstance = new Basics("red", 2302)
    basicsInstance.name = "new name!"
    println(s"basicsInstance name is ${basicsInstance.name}")

  }

}

//The class keyword creates a class, which is a blueprint you can use to create objects
// When we define an object and a class with the same name in the same file, we create
// a class Basics with "static" fields and methods attached to the object Basics

class Basics(var name: String, var luckyNumber: Int) {
  // here we define both the class and the constructor behaviour
  // there is no separate constructor method *required* in Scala
  // though we can write auxilliary constructors

  //Scala class syntax is an exercise in reducing boilerplate in comparison to Java syntax
  //we don't need anything else in order to have 2 mutable fields provided in the constructor

  override def toString(): String = {
    s"$name $luckyNumber"
  }

}
