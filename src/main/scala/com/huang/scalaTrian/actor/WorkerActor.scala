package com.huang.scalaTrian.actor

import java.util.UUID

import akka.actor.{Actor, ActorSelection, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

class WorkerActor(masterUrl: String) extends Actor {

  var serverActorRef: ActorSelection = _ // 服务端的代理对象

  val wk_id: String = UUID.randomUUID().toString

  // 在receive方法之前调用
  override def preStart(): Unit = {
    // akka.tcp://Server@127.0.0.1:8088
    serverActorRef = context.actorSelection(masterUrl)
  }

  // mailbox ->receive
  override def receive: Receive = {
    case "start" => {
      //注册信息到master
      serverActorRef ! RegisterWorkerInfo(wk_id, 4 * 1024, 4)
    }
    case RegisteredWorker(str) => {
      println(str)
      //定时发送心跳
      import context.dispatcher
      import scala.concurrent.duration._
      context.system.scheduler.schedule(0 millis, 1500 millis, self, SendHeartBeat)
    }
    case SendHeartBeat => {
      serverActorRef ! HeartBeat(wk_id)
      println("定时发送心跳")
    }

  }
}

object WorkerActor {
  def main(args: Array[String]): Unit = {
    if (args.length < 4) {
      println("参数不完整！<host> <port> <workName> <masterUrl>")
      sys.exit()
    }

    //指定客户端的IP和端口
    val host = args(0)
    val port = args(1)
    val workName = args(2)
    val masterUrl = args(3)

    /**
      * 使用ConfigFactory的parseString方法解析字符串,指定客户端IP和端口
      */
    val config = ConfigFactory.parseString(
      s"""
         |akka.actor.provider="akka.remote.RemoteActorRefProvider"
         |akka.remote.netty.tcp.hostname=$host
         |akka.remote.netty.tcp.port=$port
        """.stripMargin)

    /**
      * 将config对象传递给ActorSystem并起名为"Server"，为了是创建客户端工厂对象(clientActorSystem)。
      */
    val actorSystem = ActorSystem("client", config)

    // 创建dispatch | mailbox
    val workActorRef = actorSystem.actorOf(Props(new WorkerActor(masterUrl)), workName)

    workActorRef ! "start" // 自己给自己发送了一条消息 到自己的mailbox => receive
  }
}
