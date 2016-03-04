package info.batey

import org.scalatest.{Matchers, FunSuite}

class PrimeLoaderTest extends FunSuite with Matchers {

  test("Load dependencies from class path") {
    val underTest = new PrimeLoader()

    val primes = underTest.loadPrime("server/src/test/resources/primes/")

    primes should equal(List(Prime(
      Request("GET", "/api/mytest"),
      Response(200, "More content\n"))
    ))

  }
}
