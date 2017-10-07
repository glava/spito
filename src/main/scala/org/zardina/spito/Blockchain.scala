package org.zardina.spito

import java.nio.charset.StandardCharsets

import org.apache.commons.codec.binary.Hex
import org.apache.commons.lang3.SerializationUtils

import scala.collection.mutable.ListBuffer

case class Transaction(sender: String, recepient: String, amount: Double)

case class Block(index: Int, transaction: ListBuffer[Transaction], proof: Int, previousHash: String, timestamp: Long) {

  /**
    * create a SHA-256 hash of a Block
    */
  lazy val hash: String = Hash(SerializationUtils.serialize(this))
}

object Hash {
  def apply(byte: Array[Byte]): String = {
    val sha = java.security.MessageDigest.getInstance("SHA-256")
    Hex.encodeHexString(sha.digest(byte))
  }
}

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

  /**
    *
    * Simple Proof of Work Algorithm:
    * Find a number p' such that isValidProof(p, p') is true
    * p is the previous proof, and p' is the new proof
    *
    * @param lastProof
    * @return
    */
  /**/
  def proofOfWork(lastProof: Int): Int = {
    var proof = 0
    while (!isValidProof(lastProof, proof)) {
      proof = proof + 1
    }
    proof
  }

  /**
    * @param lastProof
    * @param proof
    * @return true if hash("lastProof"+"proof") contains 4 last zeros
    */
  /*
  * */
  def isValidProof(lastProof: Int, proof: Int): Boolean =
    Hash(s"$lastProof$proof".getBytes(StandardCharsets.UTF_8)).takeRight(4) == "0000"
}