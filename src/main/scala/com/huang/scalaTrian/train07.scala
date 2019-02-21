package com.huang.scalaTrian

object train07 {

  def main(args: Array[String]): Unit = {
    //    test(fun1)
    //    test(fun2)
    println(getPerson(person, 18))
  }

  def fun1(a: Int = 1, b: Int): Unit = {
    println("hello")
  }

  def fun2: (Int, Int) => Unit = (a: Int, b: Int) => {
    println(a + b)
  }


  def test(f: (Int, Int) => Unit): Unit = {
    f(2, 7)
  }



  def getPerson(h: Int => String, f: Int): String = {
    h(f)
  }

  def person(a: Int): String = {
    s"I am $a years old!"
  }

}
