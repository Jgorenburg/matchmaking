import scala.collection.mutable.ArrayBuffer
class TournamentHistory {
  val rounds = ArrayBuffer[RoundHistory]()

  def addRound(round: RoundHistory): Unit = rounds += round
}