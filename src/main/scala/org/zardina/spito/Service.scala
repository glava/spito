package org.zardina.spito

import fs2.Task
import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl._
import org.http4s.server.blaze.BlazeBuilder
import org.http4s.util.StreamApp

case class ChainResponse(chain: List[Block], length: Long)

case class TransactionResponse(message: String,
                               index: Int,
                               transactions: List[Transaction],
                               proof: Int,
                               previous_hash: String)

case class TransactionRequest(sender: String, recipient: String, amount: Long)

object Service extends StreamApp {

  val blockchain = new Blockchain

  val routes = HttpService {
    case GET -> Root / "mine" =>
      val proof: Int = blockchain.proofOfWork(blockchain.lastBlock.proof)
      blockchain.newTransaction("0", "this-node-id", amount = 1)
      val block = blockchain.newBlock(proof)
      Ok(
        TransactionResponse(
          "New block forged",
          block.index,
          block.transaction.toList,
          block.proof,
          block.previousHash
        ).asJson
      )
    case GET -> Root / "chain" =>
      Ok(ChainResponse(blockchain.chain.toList, blockchain.chain.length).asJson)
    case req@POST -> Root / "transactions" / "new" =>
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
