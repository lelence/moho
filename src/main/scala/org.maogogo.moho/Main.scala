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

import com.google.inject.Guice
import org.maogogo.moho.http.RestHttpServer
import org.maogogo.moho.protobuf.data.ApplicationSettings

object Main extends App {

  new scopt.OptionParser[ApplicationSettings]("scopt") {
    head("cocoa", "1.0")

    opt[Int]('p', "port").action((x, c) => {
      c.copy(port = x)
    }).text("server port")

    opt[String]('f', "file").action((x, c) => {
      c.copy(file = Some(x))
    }).text("server config file")

    opt[Seq[String]]('s', "seeds").action((x, c) => {
      c.copy(seeds = x)
    }).text("cluster seeds")

    opt[Seq[String]]('r', "roles").action((x, c) => {
      c.copy(roles = x)
    }).text("cluster role")

  }.parse(args, ApplicationSettings(0, None, Seq.empty, Seq.empty)) match {
    case Some(settings) ⇒

      val injector = Guice.createInjector(ServicesModule())

      import net.codingwell.scalaguice.InjectorExtensions._

      injector.instance[RestHttpServer]

      println(logo)
    case _ ⇒
  }

  lazy val logo =
    """
      |    __  ___      __
      |   /  |/  /___  / /_  ____
      |  / /|_/ / __ \/ __ \/ __ \
      | / /  / / /_/ / / / / /_/ /
      |/_/  /_/\____/_/ /_/\____/
      |
    """.stripMargin

}
