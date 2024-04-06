import scala.collection.mutable.ArrayBuffer
class RoundHistory() {
  val matches = ArrayBuffer[MatchHistory]()

  def addMatch(matchRecord: MatchHistory): Unit = matches += matchRecord
}
