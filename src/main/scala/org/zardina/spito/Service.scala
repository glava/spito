package org.zardina.spito
import fs2.Task
import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl._
import org.http4s.server.blaze.BlazeBuilder
import org.http4s.util.StreamApp

case class Respone(chain: List[Block], length: Long)
case class TransactionRequest(sender: String, recipient: String, amount: Long)

object Service extends StreamApp {

  val b = new Blockchain

  val routes = HttpService {
    case GET -> Root / "mine" =>
      Ok(s"Start mining")
    case GET -> Root / "chain" =>
      Ok(Respone(b.chain.toList, b.chain.length).asJson)
    case req @ POST -> Root / "transactions" / "new" =>
      for {
        tq <- req.as(jsonOf[TransactionRequest])
        resp <- Ok(tq.asJson)
      } yield resp

  }

  override def stream(args: List[String]): fs2.Stream[Task, Nothing] = {
    BlazeBuilder
      .bindHttp(8080, "localhost")
      .mountService(routes, "/")
      .serve
  }
}
