package com.revature.introdemos

import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.util.Success
import scala.util.Failure

object Futures {

  def run() = {
    // to use Futures, we need to declare an implicit ExecutionContext (don't worry much about it)
    //implicit val ec = scala.concurrent.ExecutionContext.global
    
    // this works, which was a surprise to me:
    // importing an implicit val means that implicit val is used for implicit params
    import ImplicitImport.ec

    //give the Future a body that specifies the code it should run:
    val myFuture = Future {
      Thread.sleep(1000L) //sleep for at least 1000 ms
      println("hi from Future")
      100 //can return values
    }

    println(myFuture)

    //wait for the future to be done.  This is necessary because if the main thread exits
    // then the future will be cancelled.
    
    //Thread.sleep(500L) //this is bad, because we're either being inefficient (waiting too long) or not waiting enough and exiting early
    // Await.ready will wait for myFuture to complete, with a maximum wait time (that can be infinity)
    Await.ready(myFuture, Duration.create(10L, "second")) //Duration.Inf to wait forever

    //getting the value is an Option of a Try of an Int
    // Try is similar to option, but contains Success(value) or Failure(value)
    // We can pass error messages via Failures in a Try.
    println(myFuture.value)

    //Await.result to wait for the return value
    println(Await.result(myFuture, Duration.Inf))

    //Adam's preferred method: onComplete
    val myFutureOnComplete = Future {
      //sleep for some amount of time under 10s
      Thread.sleep((Math.random()*10000).toLong)
      "hello from second future!"
    }

    // write a function to run when the future completes, no need to check back in
    myFutureOnComplete.onComplete((result) => {
      result match {
        case Success(value) => {println(value)}
        case Failure(exception) => {println(exception)}
      }
    })

  }
}
