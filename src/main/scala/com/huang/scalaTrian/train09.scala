package com.huang.scalaTrian

import scala.collection.mutable._

object train09 {

  def main(args: Array[String]): Unit = {
    val arr = Array(1, 2, 3)

    println("--------map---------")
    arr.map(x => (x, x * 10)).foreach(println)

    println("--------flatten---------")
    val arr1 = Array(Array(1, 2, 3), Array(10, 20, 30))
    arr1.flatten.foreach(println)

    println("--------flatMap---------") //先执行map，在执行flatten


    println("--------HashMap---------")
    val map = new scala.collection.mutable.HashMap[Int, String]
    map(1) = ""
    map(2) = "1"
    val a = map.getOrElse(1, "---")
    println(a)


    val l = ArrayBuffer(1, 2, 3)
    println(s"foldLeft -- ${l.foldLeft(0)(_ - _)}") // (((0-1)-2)-3)
    println(s"foldRight -- ${l.foldRight(0)(_ - _)}") // (1-(2-(3-0)))
    println(s"reduceLeft -- ${l.reduceLeft(_ - _)}") // 1-2-3
    println(s"reduceRight -- ${l.reduceRight(_ - _)}") // (1-(2-3))


    println(s"aggregate -- ${l.aggregate(0)(_ - _, _ + _)}") // (1-(2-3))

    val l1 = ArrayBuffer(6, 7, 8)
    println(s"zip--${l.zip(l1)}")

  }

}
