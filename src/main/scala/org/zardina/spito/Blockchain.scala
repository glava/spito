package org.zardina.spito

import scala.collection.mutable.ListBuffer

case class Transaction(sender: String, recepient: String, amount: Double)

case class Block(index: Int, transaction: ListBuffer[Transaction], proof: Int, previousHash: String, timestamp: Long)

class Blockchain {

  val currentTransactions: ListBuffer[Transaction] = ListBuffer.empty
  val chain: ListBuffer[Block] = ListBuffer.empty

  /**
    *
    * @param sender    - address of the Sender
    * @param recepient - address of the Recipient
    * @param amount
    * @return - The index of the Block that will hold this transaction
    */
  /**/
  def newTransaction(sender: String, recepient: String, amount: Double): Int = {
    currentTransactions.append(Transaction(sender, recepient, amount))
    currentTransactions.length - 1
  }

  /**
    * Create a new Block in the Blockchain and appends it to chain
    *
    * @param proof        The proof given by the Proof of Work algorithm
    * @param previousHash Hash of previous Block
    * @return new Block
    */
  /**/
  def newBlock(proof: Int, previousHash: Option[String]): Block = {
    val block = Block(
      index = currentTransactions.size + 1,
      transaction = currentTransactions,
      proof = proof,
      previousHash = previousHash.getOrElse(chain.hashCode().toString),
      timestamp = java.time.LocalDate.now().toEpochDay
    )

    currentTransactions.drop(currentTransactions.size)
    chain.append(block)
    block
  }
}