package org.zardina.spito

case class Transaction(sender: String, recepient: String, amount: Double)

class Blockchain {

  val currentTransactions: scala.collection.immutable.List[Transaction] = scala.collection.immutable.List.empty


  def newTransaction(sender: String, recepient: String, amount: Double): Int = {
    currentTransactions.+:(Transaction(sender, recepient, amount))
    currentTransactions.length - 1
  }

}



