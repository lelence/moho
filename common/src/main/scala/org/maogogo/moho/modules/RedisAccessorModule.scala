package org.maogogo.moho.modules

import akka.actor.ActorSystem
import com.google.inject._
import com.typesafe.scalalogging.LazyLogging
import net.codingwell.scalaguice.ScalaModule

object RedisAccessorModule extends LazyLogging {

  def apply(): AbstractModule with ScalaModule = {
    new AbstractModule with ScalaModule {
      override def configure(): Unit = {
        // bind[ReidsByteStringAccessor].toProvider[RedisAccessorProvider].asEagerSingleton()
      }
    }
  }

//  private class RedisAccessorProvider @Inject()(
//    implicit
//    system: ActorSystem) extends Provider[ReidsByteStringAccessor] {
//    override def get(): ReidsByteStringAccessor = {
//      import scala.collection.JavaConverters._
//
//      val config = system.settings.config
//
//      val redisSettings = config.getObjectList("redis").asScala.map(_.toConfig).map { cfg ⇒
//        val host = cfg.getString("host")
//        val port = cfg.getInt("port")
//        (host, port)
//      }
//
//      require(redisSettings.nonEmpty, "lost redis settings")
//
//      val redisCommand: RedisCommands = redisSettings match {
//        case Seq((h, p)) ⇒ RedisClient(host = h, port = p)
//        case Seq(_*)     ⇒
//          RedisCluster(redisSettings.map {
//            case (h, p) ⇒ RedisServer(host = h, port = p)
//          })
//      }
//
//      new ReidsByteStringAccessor(redisCommand)
//    }
//  }

}
