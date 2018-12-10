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

package org.maogogo.moho.http

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import com.google.inject.Inject
import org.slf4s.Logging

class RestHttpServer @Inject() (
  implicit
  system: ActorSystem,
  mat: ActorMaterializer) extends Logging {

  import system.dispatcher

  private def route: Route = {
    pathEndOrSingleSlash {
      get {
        complete("hello world")
      }
    }
  }

  val bind = Http().bindAndHandle(route, interface = "0.0.0.0", port = 9000)

  bind.onComplete {
    case scala.util.Success(binding) ⇒ log.info(s"http server started! ${
      binding.localAddress
    }")
    case scala.util.Failure(ex) ⇒ log.error("http server start failed!", ex)
  }

}
