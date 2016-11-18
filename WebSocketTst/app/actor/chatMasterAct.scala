package actor

import akka.actor.{Actor, ActorRef, Props}
import actor.chatClientAct
import akka.util.Timeout
import play.api.libs.concurrent.Akka
import play.api.libs.iteratee.Concurrent
import play.api.libs.concurrent.Execution.Implicits._
import scala.concurrent.duration._
import play.api.Play.current
/**
  * Created by kei on 2016/11/18.
  */

case class StartChat(name:String,channel:Concurrent.Channel[String])

class chatMasterAct extends Actor{
  private[this] implicit val timeout = Timeout(5 second)
  var childs = Map[String,ActorRef]()
  def receive = {
    case StartChat(name,channel) =>{
      childs += (name -> context.actorOf(Props(classOf[chatClientAct],channel)))
    }
    case originName:String if childs.contains(originName) => {
      childs.foreach{case (name,act) => act ! s"close at $originName"}
      childs(originName) ! CloseChat(originName)
      childs -= originName
    }
    case msg:String => {
      childs.foreach{case (name,act) => act ! msg}
    }
  }

}

object masterRep{
  val rep = Akka.system.actorOf(Props[chatMasterAct])
}
