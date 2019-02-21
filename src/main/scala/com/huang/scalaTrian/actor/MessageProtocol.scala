package com.huang.scalaTrian.actor


//client向server发生注册消息的
case class RegisterWorkerInfo(id: String, memory: Int, cores: Int)

//server向client反馈注册成功的信息
case class RegisteredWorker(clientHost: String)

//该伴生对象用于worker本地发生消息给自己
case object SendHeartBeat

//client发生心跳给server
case class HeartBeat(id_client: String)

//该伴生对象用于server定时检测超时的client
case object CheckTimeOutWork

class WorkerInfo(val id: String, val memory: Int, val cores: Int) {
  var lastHeartBeatTine: Long = 0
}