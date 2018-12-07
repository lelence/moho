package org.maogogo.moho.actors

import akka.actor.Actor

class MergeringActor extends Actor {

  override def receive: Receive = {
    case s: String ⇒
      println("s => " + s)
  }
}
