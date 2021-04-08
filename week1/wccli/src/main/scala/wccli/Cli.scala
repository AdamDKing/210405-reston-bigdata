package wccli

import scala.io.StdIn
import scala.util.matching.Regex
import java.io.FileNotFoundException

/** CLI class, used to communicate with the user
  */
class Cli {

  //I'm going to use a little regex to make our lives easier
  // good to look into!
  // we're going to extract commands and argument(s) from user input
  val commandArgPattern: Regex = "(\\w+)\\s*(.*)".r

  /** runs the main menu, on a loop
    */
  def menu(): Unit = {
    printWelcome()
    var continueMenuLoop = true
    while (continueMenuLoop) {
      printMenuOptions()
      //we can get user input via StdIn.  I'm going to grab a line at a time:
      // this is blocking, so program flow will not continue until input is received
      var input = StdIn.readLine()
      input match {
        case commandArgPattern(cmd, arg) if cmd == "echo" => {
          println(arg)
        }
        case commandArgPattern(cmd, arg) if cmd == "getfiles" => {
          FileUtil.getTopLevelFilenames().foreach(println)
        }
        case commandArgPattern(cmd, arg) if cmd == "first100chars" => {
          getFirst100Chars(arg)
        }
        case commandArgPattern(cmd, arg) if cmd == "wordcount" => {
          wordcount(arg)
        }
        case commandArgPattern(cmd, arg) if cmd == "exit" => {
          continueMenuLoop = false
        }
        case commandArgPattern(cmd, arg) => {
          println(
            s"Parsed command $cmd with args $arg did not correspond to an option"
          )
        }
        case _ => {
          //default case if no other cases are matched
          println("Failed to parse command.")
        }
      }

    }

  }

  def printWelcome(): Unit = {
    println("Welcome to WC CLI!")
  }

  def printMenuOptions(): Unit = {
    List(
      "Menu options:",
      "echo [word]: repeats word back to you",
      "getfiles: lists files in current directory",
      "first100chars [filename]: print the first 100 characters of a file",
      "wordcount [filename]: print the count of each word in a file",
      "exit: exits WC CLI"
    ).foreach(println)

  }

  def getFirst100Chars(filename: String): Unit = {
    try {
      println(FileUtil.getTextContent(filename).substring(0, 100))
    } catch {
      //inside of the catch block, we write cases for each Exception we want to catch
      // a case for Exception will catch all Exceptions
      case fnfe: FileNotFoundException => {
        //now the FileNotFoundException is caught, we need to decide how to handle it
        // leaving this empty is a terrible idea
        // let's just display an appropriate message to the user and continue the menu
        println(s"File not found: ${fnfe.getMessage()}")
      }
    }
  }

  def wordcount(filename: String): Unit = {
    // this line produces an array of "words" that needs to be cleaned up and counted

    FileUtil.getTextContent(filename).split("\\s+")
    //
  }

}
