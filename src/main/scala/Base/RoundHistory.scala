package Base

import scala.collection.mutable.ArrayBuffer

class RoundHistory(roundNumParam: Int):
  val matches = ArrayBuffer[MatchHistory]()
  val roundNum = roundNumParam

  def addMatch(matchRecord: MatchHistory): Unit = matches += matchRecord
