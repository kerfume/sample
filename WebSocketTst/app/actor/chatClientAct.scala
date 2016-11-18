package actor

import akka.actor.{Actor, PoisonPill}
import play.api.libs.iteratee.Concurrent

/**
  * Created by kei on 2016/11/18.
  */

case class CloseChat(name:String)

class chatClientAct(channel:Concurrent.Channel[String]) extends Actor{
  def receive = {
    case msg:String =>{ println(msg); channel.push(msg)}
    case CloseChat(name) => {
      channel.end()
      self ! PoisonPill
    }
  }
}
