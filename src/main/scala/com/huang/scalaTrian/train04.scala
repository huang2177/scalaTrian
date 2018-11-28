package com.huang.scalaTrian

object train04 {
  implicit val msg: String = "哈哈"

  implicit def fDouble2Int(b: Double): Int = {
    println("---fDouble2Int--")
    b.toInt
  }

  implicit val double2Int: Double => Int = (b: Double) => {
    println("---double2Int--")
    b.toInt
  }

  /**
    * 会在当前的上下文中寻找一个 implicit String类型的变量，若没有，就会报错
    * implicit 修饰的参数只能放在最后
    */
  def say(implicit msg: String): Unit = {
    println(msg)
  }

  def add(a: Int)(b: Int, c: Int): Int = a + b + c

  def add1(a: Int = 10, b: String = "10"): Int = a + b.toInt

  def main(args: Array[String]): Unit = {
    //    say
    //    println(add(3)(2, 4))
    //    println(add1())

    /**
      * age 本身是一个Int类型的，此时20.5是一个double类型的，
      * 此时编译器会在当前上下文寻找一个能把double变为int的隐式转换
      * 默认寻找顺序： 函数（double2Int） ->方法（fDouble2Int）
      */
    val age: Int = 20.5
    println(age)
  }
}
