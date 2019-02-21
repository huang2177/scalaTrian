package com.huang.scalaTrian

/**
  * @time ： 2019/2/19 0019
  * @author ： Huangshuang
  * @des ： scala构造器
  */
//主构造器定义在类名后面
class Scala01(name: String, var age: Int) {

  def this(name: String) {
    this(name, 26) //必须先调用主构造器
  }

  //辅助构造器
  def this() = this("huang", 26)


  def main(args: Array[String]): Unit = {
    val s1 = new Scala01()
    val s2 = new Scala01("huang")
    val s3 = new Scala01("huang", 26)
  }
}