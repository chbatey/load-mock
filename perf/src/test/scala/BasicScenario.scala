import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class BasicScenario extends Simulation {

  val httpConf = http.baseURL("http://localhost:8080")

  val browse = repeat(10000) {
    exec(http("ping")
      .get("/ping"))
  }

  val scn = scenario("BasicSimulation").exec(browse)

  setUp(
    scn.inject(rampUsers(10) over (1 seconds))
  ).protocols(httpConf)
}
