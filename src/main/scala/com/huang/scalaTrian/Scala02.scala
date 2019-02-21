package com.huang.scalaTrian

/**
  * @time ： 2019/2/19 0019
  * @author ： Huangshuang
  * @des ： trait 特质（相当于java的接口）
  */
trait Scala02 {
  //定义未实现的方法
  def eat(name: String)

  //定义实现的方法
  def sleep(name: String): Unit = {
    println(s"$name 在睡觉！")
  }
}

object Pig extends Scala02 {
  override def eat(name: String): Unit = {

  }
}