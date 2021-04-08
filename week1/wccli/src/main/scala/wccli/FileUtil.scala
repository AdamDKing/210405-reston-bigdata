package wccli

import java.io.File
import scala.io.BufferedSource
import scala.io.Source

/**
  * Performs basic file IO
  * 
  * All of the file IO logic exists in this util, methods here should only return Strings or data structures.
  */
object FileUtil {

  /**
    * retrieves all the top-level files in the current directory (directory at top of project)
    *
    * @return
    */
  def getTopLevelFilenames(): Array[String] = {
    val currentDir = new File(".")
    currentDir.listFiles()
      .filter((f: File) => {f.isFile() && !f.isHidden()})
      .map(_.getName())
  }

  /**
    * Retrieves the text content of a file as a string.
    * 
    * Accessing files is an exception-prone process -- it's very easy for some problems to occur
    * We should keep this in mind
    *
    * @throws FileNotFoundException
    * @param filename
    * @return entire file content
    */
  def getTextContent(filename: String): String = {
    //Whenever we open an external resource like a file or a database connection
    // we want to close it once we're done with it.  Otherwise it remains open, using our
    // resources and a connection slot on our DB server (until it gets cleaned up at some future point)

    //declare our opened file
    var openedFile: BufferedSource = null

    //to ensure our connection is closed even in the case of errors, we wrap our connection logic
    // in a try block, and we close the connection in a finally block
    try {
      openedFile = Source.fromFile(filename)
      // get the entire content of the file as one big String:
      openedFile.getLines().mkString(" ")
    } finally {
      if (openedFile != null) openedFile.close()
    }
  }

}