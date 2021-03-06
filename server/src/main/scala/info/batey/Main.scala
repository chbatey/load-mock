package info.batey

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Sink

object Main {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  val serverSource = Http().bind(interface = "0.0.0.0", port = 8080)

  val requestHandler: HttpRequest => HttpResponse = {

    case HttpRequest(GET, Uri.Path("/ping"), _, _, _) =>
      HttpResponse(entity = "PONG!")

    case _: HttpRequest =>
      HttpResponse(404, entity = "Not Found")
  }


  def main(args: Array[String]): Unit = {
    serverSource.to(Sink.foreach { connection =>
      connection.handleWithSyncHandler(requestHandler)
    }).run()
  }
}