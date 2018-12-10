/*
 * Copyright 2018 maogogo organization
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.maogogo.moho

import akka.actor.ActorSystem
import akka.cluster.Cluster
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.google.inject._
import com.typesafe.config.{ Config, ConfigFactory }
import net.codingwell.scalaguice.ScalaModule
import org.maogogo.moho.cluster.SimpleClusterListener

import scala.concurrent.duration._

object ServicesModule {

  lazy val systemName = "MyClusterSystem"

  def apply(config: Option[Config] = None): AbstractModule with ScalaModule =
    new AbstractModule with ScalaModule {
      override def configure(): Unit = {

        implicit val system = ActorSystem(systemName, config.getOrElse(ConfigFactory.load()))
        bind[ActorSystem].toInstance(system)
        bind[Config].toInstance(system.settings.config)
        bind[ActorMaterializer].toInstance(ActorMaterializer())

        bind[Cluster].toInstance(Cluster(system))
        bind[SimpleClusterListener].in[Singleton]

        bind[Timeout].toInstance(Timeout(3 seconds))
      }
    }

}