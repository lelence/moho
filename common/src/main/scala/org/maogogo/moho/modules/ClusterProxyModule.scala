package org.maogogo.moho.modules

import com.google.inject.AbstractModule
import com.typesafe.scalalogging.LazyLogging
import net.codingwell.scalaguice.ScalaModule

object ClusterProxyModule extends LazyLogging {

  //  def apply(): AbstractModule with ScalaModule = {
  //    new AbstractModule with ScalaModule with AssistedInjectFactoryScalaModule[Binder] {
  //      override def configure(): Unit = {
  //
  //        bind[Map[String, ActorRef]]
  //          .annotatedWithName(Constants.akka_cluster_routees)
  //          .toProvider[ProxyRouteesProvider]
  //          .asEagerSingleton()
  //
  //        bindFactory[ProxyActorProvider, ProxyActor]()
  //      }
  //    }
  //  }
  //
  //  class ProxyRouteesProvider @Inject()(
  //    config: Config,
  //    system: ActorSystem) extends Provider[Map[String, ActorRef]] {
  //
  //    import scala.collection.JavaConverters._
  //
  //    override def get(): Map[String, ActorRef] = {
  //
  //      def proxy(named: String, system: ActorSystem): ActorRef = {
  //        system.actorOf(
  //          ClusterSingletonProxy.props(
  //            singletonManagerPath = s"/user/${named}",
  //            settings = ClusterSingletonProxySettings(system)),
  //          name = s"proxy_${named}")
  //      }
  //
  //      config.getStringList("akka.cluster.routees").asScala.map { path ⇒
  //        path → proxy(path, system)
  //      } toMap
  //    }
  //  }


}
