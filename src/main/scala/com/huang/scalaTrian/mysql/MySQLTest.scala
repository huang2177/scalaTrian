package com.huang.scalaTrian.mysql

object MySQLTest {
  def main(args: Array[String]): Unit = {
    val op = new Operations()
    val user = op.User("5", "Allen", 34)
    //Insert
    println(op.add(user))

    //Delete
//    println(op.delete("5"))

    //update
    //println(op.update(user))

  }
}
