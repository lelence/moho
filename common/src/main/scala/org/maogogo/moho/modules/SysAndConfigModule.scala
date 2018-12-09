package org.maogogo.moho.modules

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.google.inject._
import com.typesafe.config.{ Config, ConfigFactory }
import com.typesafe.scalalogging.LazyLogging
import net.codingwell.scalaguice.ScalaModule

object SysAndConfigModule extends LazyLogging {

  def apply(config: Option[Config] = None): AbstractModule with ScalaModule =
    new AbstractModule with ScalaModule {
      override def configure(): Unit = {

        val system = ActorSystem("", config.getOrElse(ConfigFactory.load()))
        bind[ActorSystem].toInstance(system)
        bind[Config].toInstance(system.settings.config)

        // bind[ActorBuilder].annotatedWithName("actor_builder").to[ActorBuilderImpl]
        bind[ActorMaterializer].toProvider[ActorMaterializerProvider].asEagerSingleton()
      }
    }

  class ActorMaterializerProvider @Inject()(system: ActorSystem) extends Provider[ActorMaterializer] {
    override def get(): ActorMaterializer = ActorMaterializer()(system)
  }

}
