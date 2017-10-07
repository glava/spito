package org.zardina.spito
import fs2.Task
import org.http4s._
import org.http4s.dsl._
import org.http4s.server.blaze.BlazeBuilder
import org.http4s.util.StreamApp


object Service extends StreamApp {
  val routes = HttpService {
    case GET -> Root / "mine" =>
      Ok(s"Start mining")
    case GET -> Root / "chain" =>
      Ok(s"Chaining bro")
    case POST -> Root / "transactions" / "new" =>
      Ok("I'm adding transaction")
  }

  override def stream(args: List[String]): fs2.Stream[Task, Nothing] = {
    BlazeBuilder
      .bindHttp(8080, "localhost")
      .mountService(routes, "/")
      .serve
  }
}
