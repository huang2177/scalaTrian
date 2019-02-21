package com.huang.scalaTrian

/**
  * 伴生对象和伴生类
  * 如果一个class，还有一个与之同名的object
  * 则称该object是该class的半生对象，该class是object的伴生类
  *
  * object本身就是一个单例对象
  */

object ApplyTest {
  def main(args: Array[String]): Unit = {
    ApplyApp("s").test("4")
  }
}

class ApplyApp(s: String) {
  var count = 0
  var name = "s"

  def this(a: Int) = this("")

  def this(a: Int, string: String) = this(a)


  def test(count: String): Unit = {
    count match {
      case "2" => println("good!")
      case "3" => println("bad!")
      case _ if name == s => println("just so so!!!")
      case _ => println("nothing to match!")
    }
  }
}

object ApplyApp {

  println("object enter...")
  println("object leave...")

  def apply(s: String): ApplyApp = {
    new ApplyApp(2)
  }
}
