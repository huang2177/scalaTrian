package com.huang.scalaTrian

import java.io.File

import scala.io.Source


object train01 extends  App {

  implicit class RichFile(file: File) {
    def read(): String = {
      Source.fromFile(file).mkString.trim
    }
  }

  new File("F:/test.txt").read()
    .replace(" ", ",")
    .replace("*", "")
    .split(",")
    .filter(!_.isEmpty)
    .map(x => (x, 1))
    .groupBy(_._1)
    .map(x => (x._1, x._2.length))
    .toList
    .sortBy(-_._2)
    .foreach(println)
}


