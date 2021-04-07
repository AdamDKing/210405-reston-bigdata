package wccli

import java.io.File

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

}