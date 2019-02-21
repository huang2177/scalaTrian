package com.huang.scalaTrian

import scala.collection.mutable

object train05 {

  def main(args: Array[String]): Unit = {
    val map = collection.mutable.HashMap[String, Int]()
    val list = List(1, 23, 46, 567, 76, 775, 6765, 23, 113, 34, 23, 2, 3, 4, 34, 3, 42, 42, 132, 33)

    list.map(x => (x, 1))
      .groupBy(_._1)
//      .toList
      .map(x => ("'" + x._1 + "'", x._2.length))
      //.sortBy(x => x)
      .foreach(println)
  }

}
