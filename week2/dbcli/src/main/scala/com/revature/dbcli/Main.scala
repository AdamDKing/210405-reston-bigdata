package com.revature.dbcli

import java.sql.DriverManager

object Main {
  def main(args: Array[String]): Unit = {
    println("hello!")
    //We're going to write some logic here that will be split out into other objects/methods in the future

    //manually load the driver
    classOf[org.postgresql.Driver].newInstance()

    //use JDBC's DriverManager to get a connection.  JDBC is DB agnostic
    //getConnection takes 3 arguments.  I'm going to hardcode creds for now, since this is local
    // postgres is my username and wasspord is my password
    val conn = DriverManager.getConnection(
      "jdbc:postgresql://localhost:5026/chinook",
      "postgres",
      "wasspord"
    )
    //use the drivermanager to get a connection
    //use the connection to prepare a sql statement
    val stmt = conn.prepareStatement("SELECT * FROM track WHERE length(name) > ? AND name LIKE ?;") //the ? is a parameter
    stmt.setInt(1, 70) // this sets an int value for the first parameter in our preparedStatement
    stmt.setString(2, "Symphony%") // this sets a String value for the second parameter
    stmt.execute()
    //after executing the statement, use it to get a resultset
    val rs = stmt.getResultSet()
    while(rs.next) {
      println(rs.getString("name")) //retrieve parts of records off the resultset
    }
  }
}
