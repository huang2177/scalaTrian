package com.huang.scalaTrian.mysql

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class Operations {

  case class User(id: String, name: String, age: Int)

  //insert
  def add(user: User): Boolean = {
    val conn = DBUtils.connect()
    try {
      val sql = new StringBuilder()
        .append("INSERT INTO user(id, name, age)")
        .append("     VALUES(?, ?, ?)")
      val pstm = conn.prepareStatement(sql.toString())
      pstm.setObject(1, user.id)
      pstm.setObject(2, user.name)
      pstm.setObject(3, user.age)

      pstm.executeUpdate() > 0
    }
    finally {
      conn.close()
    }
  }

  //delete
  def delete(id: String): Boolean = {
    val conn = DBUtils.connect()
    try {
      val sql = "DELETE FROM user WHERE id = ?"
      val pstm = conn.prepareStatement(sql)
      pstm.setObject(1, id)
      pstm.executeUpdate() > 0
    }
    finally {
      conn.close()
    }
  }

  //update
  def modify(user: User): Boolean = {
    val conn = DBUtils.connect()
    try {
      val sql = "UPDATE user SET age = ? WHERE id = ?"
      val pstm = conn.prepareStatement(sql)
      pstm.setObject(1, user.age)
      pstm.setObject(2, user.id)

      pstm.executeUpdate() > 0
    }
    finally {
      conn.close()
    }
  }

  //select
  def query(id: Int): ArrayBuffer[mutable.HashMap[String, Any]] = {
    val conn = DBUtils.connect()
    try {
      val sql = new StringBuilder()
        .append("SELECT name, age")
        .append("  FROM user")
        .append(" WHERE id >  ?")
      val pstm = conn.prepareStatement(sql.toString())
      pstm.setObject(1, id)
      val rs = pstm.executeQuery()
      val rsmd = rs.getMetaData()
      val size = rsmd.getColumnCount()
      val buffer = new ArrayBuffer[mutable.HashMap[String, Any]]()
      while (rs.next()) {
        val map = mutable.HashMap[String, Any]()
        for (i <- 1 to size) {
          map += (rsmd.getColumnLabel(i) -> rs.getString(i))
        }
        buffer += map
      }

      buffer
    }
    finally {
      conn.close()
    }
  }
}
