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

package org.maogogo.moho.cluster

import akka.actor.Actor
import akka.cluster.Cluster
import akka.cluster.ClusterEvent._
import com.google.inject.Inject
import org.slf4s.Logging

class SimpleClusterListener @Inject() (cluster: Cluster) extends Actor with Logging {

  override def preStart(): Unit = {
    cluster.subscribe(self, initialStateMode = InitialStateAsEvents,
      classOf[MemberEvent], classOf[UnreachableMember])
  }

  override def postStop(): Unit = cluster.unsubscribe(self)

  def receive = {
    case MemberUp(member) ⇒
      log.info(s"Member is Up: ${member.address}")
    case UnreachableMember(member) ⇒
      log.info(s"Member detected as unreachable: ${member}")
    case MemberRemoved(member, previousStatus) ⇒
      log.info(
        s"Member is Removed: ${member.address} after ${previousStatus}")
    case _: MemberEvent ⇒ // ignore
  }

}
