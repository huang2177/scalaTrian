package com.huang.scalaTrian

/**
  * @time ： 2019/2/19 0019
  * @author ：Huangshuang
  * @des ：集合---map
  */
object train10 {

  def main(args: Array[String]): Unit = {
    val array = Array(("huang", 26), ("zhao", 25))
    println("fold --- " + array.foldRight(0)(_._2 + _))
    println("reduce --- " + array.map(_._2).toBuffer.sum)
    println("reduce --- " + array.reduce((x, y) => ("hello", x._2 + y._2)))
  }
}
