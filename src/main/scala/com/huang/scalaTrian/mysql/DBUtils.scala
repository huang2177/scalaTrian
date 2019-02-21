package com.huang.scalaTrian.mysql

import java.sql.{Connection, DriverManager}

object DBUtils {
  val IP = "127.0.0.1"
  val Port = "3306"
  val DBType = "mysql"
  val DBName = "scala"
  val username = "root"
  val password = "root"
  val url = "jdbc:" + DBType + "://" + IP + ":" + Port + "/" + DBName
  classOf[com.mysql.jdbc.Driver]


  /**
    * 获取数据的连接
    */
  def connect(): Connection = {
    DriverManager.getConnection(url, username, password)
  }

  /**
    * 关闭连接
    */
  def close(conn: Connection): Unit = {
    try {
      if (conn != null && !conn.isClosed) {
        conn.close()
      }
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }
}
