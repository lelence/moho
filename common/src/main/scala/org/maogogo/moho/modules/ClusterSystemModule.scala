package org.maogogo.moho.modules

import akka.actor.ActorSystem
import akka.cluster.Cluster
import com.google.inject._
import com.typesafe.scalalogging.LazyLogging
import net.codingwell.scalaguice.ScalaModule

object ClusterSystemModule extends LazyLogging {

  def apply(): AbstractModule with ScalaModule =
    new AbstractModule with ScalaModule {
      override def configure(): Unit = {
        bind[Cluster].toProvider[ClusterProvider].asEagerSingleton()
        // bind[ClusterSingletonBuilder].annotatedWithName("cluster_actor_builder").to[ClusterSingletonBuilderImpl]
        // bind[SimpleClusterListener]
      }
    }

  class ClusterProvider @Inject()(system: ActorSystem) extends Provider[Cluster] {
    override def get(): Cluster = Cluster(system)
  }

}
