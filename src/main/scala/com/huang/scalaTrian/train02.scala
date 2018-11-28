package com.huang.scalaTrian


object train02 {

  val def0 = new PartialFunction[Int, Float] {
    override def isDefinedAt(x: Int): Boolean = x != 0

    override def apply(v1: Int): Float = {
      if (isDefinedAt(v1)) {
        return 100f / v1
      }
      0
    }
  }

  val def1: PartialFunction[Int, String] = {
    case 1 => "ssss"
    case 2 => ""
  }

  def main(args: Array[String]): Unit = {
    println(def0(12))
    def1(2)
  }
}
