package com.huang.scalaTrian

object train06 {
  def main(args: Array[String]): Unit = {
    //        val array = twoSum(Array(3, 2, 4, 6), 6)
    //        println(s"x:${array(0)} --- y:${array(1)}")

    //    println(s"${if (isUgly(6)) "是丑数" else "不是丑数"}")

    println(missNumber(Array(0, 1, 3, 4, 7, 6, 9, 10, 8, 5)))
  }

  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    var x, y: Int = 0
    for (i <- nums.indices) {
      x = i
      for (j <- i + 1 until nums.length) {
        y = j
        if (nums(x) + nums(y) == target) {
          return Array(x, y)
        }
      }
    }
    Array(x, y)
  }


  //判断一个数是不是丑数
  def isUgly(num: Int): Boolean = {
    var n = num
    while (n % 2 == 0) {
      n /= 2
    }
    while (n % 3 == 0) {
      n /= 3
    }
    while (n % 5 == 0) {
      n /= 5
    }
    n == 1
  }

  //  给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
  def missNumber(num: Array[Int]): Int = {
    //1.先求出0~n的和
    val sum = num.length * (num.length + 1) / 2
    //2.求出数组的和
    var tem = 0;
    num.foreach(i => tem += i)
    sum - tem
  }
}
