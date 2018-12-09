package org.maogogo.moho.modules

import akka.stream.alpakka.slick.scaladsl.SlickSession
import com.google.inject.AbstractModule
import com.typesafe.scalalogging.LazyLogging
import net.codingwell.scalaguice.ScalaModule
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

object DatabaseModule extends LazyLogging {

  def apply(path: String = "slick-mysql"): AbstractModule with ScalaModule = {
    new AbstractModule with ScalaModule {
      override def configure(): Unit = {
        val databaseConfig = DatabaseConfig.forConfig[JdbcProfile](path)
        bind[SlickSession].toInstance(SlickSession.forConfig(databaseConfig))
      }
    }
  }

}
