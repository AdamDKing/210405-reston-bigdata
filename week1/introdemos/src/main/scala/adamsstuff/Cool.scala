package adamsstuff

import com.revature.introdemos.Basics

object Cool {
  def printSomething() = {
    //objects and classes in packages have fully qualified classnames:
    // package.Class is the format
    // so this is adamsstuff.Cool

    // we can import from other packages and use their code here
    // in Scala we can have import statements in the code, they don't need to be at the top of the file
    
    //Basics.run()

    println("something")
  }
}