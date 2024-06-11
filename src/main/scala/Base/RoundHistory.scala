package Base

import scala.collection.mutable.ArrayBuffer

class RoundHistory(val roundNum: Int):
  val matches = ArrayBuffer[MatchHistory]()

  def addMatch(matchRecord: MatchHistory): Unit = matches += matchRecord
