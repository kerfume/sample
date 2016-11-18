package controllers

import play.api._
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.iteratee.{Concurrent, Iteratee}
import play.api.mvc._
import actor._
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by sunakawamegumi on 11/16/16.
  */
class Application extends Controller{
  val nameForm = Form("name" -> text)


  def index = Action {
    Ok(views.html.index("Your new application is ready.HaHa",nameForm))
  }

  def chat = Action { implicit request =>
    val name = nameForm.bindFromRequest.get
    Ok(views.html.chat(name))
  }

  def socket(name:String) = WebSocket.using{ request =>
    val (enumerator,channel) = Concurrent.broadcast[String]
    masterRep.rep ! StartChat(name,channel)
    val in = Iteratee.foreach[String] {
      msg => masterRep.rep ! msg
    }
    (in,enumerator)

  }

}
