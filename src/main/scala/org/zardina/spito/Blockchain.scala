package org.zardina.spito

import scala.collection.mutable.ListBuffer

case class Transaction(sender: String, recepient: String, amount: Double)

  val currentTransactions: scala.collection.immutable.List[Transaction] = scala.collection.immutable.List.empty

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

}



