package com.huang.scalaTrian.actor

import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.collection.mutable

class MasterActor extends Actor {

  var works = new mutable.HashMap[String, WorkerInfo]()

  override def preStart(): Unit = {
    //定时检测worker是否超时
    import context.dispatcher
    import scala.concurrent.duration._
    context.system.scheduler.schedule(0 millis, 1500 millis, self, CheckTimeOutWork)
  }

  override def receive: Receive = {
    case "start" => {
      println("master已启动！")
    }
    case RegisterWorkerInfo(wk_id, memory, cores) => {
      val workerInfo = new WorkerInfo(wk_id, memory, cores)
      works += ((wk_id, workerInfo))
      sender() ! RegisteredWorker("注册成功！")
    }

    case HeartBeat(id_client) => {
      val workInfo = works(id_client)
      workInfo.lastHeartBeatTine = System.currentTimeMillis()

      println(s"收到${id_client}心跳检测")
    }

    case CheckTimeOutWork => {
      val currentTime = System.currentTimeMillis()
      works.filter(wkInfo => currentTime - wkInfo._2.lastHeartBeatTine > 3000)
        .foreach(wkInfo => works.remove(wkInfo._2.id))
      println(s"当前存活的worker数量：${works.size}个！")
    }
  }
}

object MasterActor {
  def main(args: Array[String]): Unit = {
    if (args.length < 3) {
      println("参数不完整！<host> <port> <masterName>")
      sys.exit()
    }

    val host = args(0)
    val port = args(1)
    val masterName = args(2)

    val config = ConfigFactory.parseString(
      s"""
         |akka.actor.provider="akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname=$host
         |akka.remote.netty.tcp.port=$port
        """.stripMargin)

    val master = ActorSystem.create("master", config)
    val masterActorRef = master.actorOf(Props[MasterActor], masterName)

    //给自己发送消息表示已经完全启动
    masterActorRef ! "start"
  }
}
