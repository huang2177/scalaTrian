package com.huang.scalaTrian

object train08 extends App {

  sum(2)(3)

  def sum(a: Int)(b: Int): Unit = {
    println(a * b)
  }
}
