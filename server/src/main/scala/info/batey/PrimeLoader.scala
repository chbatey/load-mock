package info.batey

import java.io.File

import com.typesafe.scalalogging.LazyLogging
import spray.json._
import scala.io.Source._

object PrimeJsonProtocol extends DefaultJsonProtocol {

  implicit val requestFormat = jsonFormat2(Request)
  implicit val responseFormat = jsonFormat2(Response)
  implicit val primeFormat = jsonFormat2(Prime)
}

class PrimeLoader extends LazyLogging {

  import PrimeJsonProtocol._

  def loadPrime(location: String): List[Prime] = {
    val primeLocations: File = new File(location)
    if (!primeLocations.exists()) throw new RuntimeException("File does not exist: " + primeLocations.getAbsoluteFile)
    val fileNames: List[File] = primeLocations.listFiles().toList
    val data: List[String] = fileNames.map(f => fromFile(f).mkString)
    val primes = data.map(_.toJson.convertTo[Prime])
    primes
  }

}

case class Request(method: String, url: String)
case class Response(status: Int, body: String)
case class Prime(request: Request, response: Response)
