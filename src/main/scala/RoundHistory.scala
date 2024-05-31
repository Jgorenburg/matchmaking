package base

import scala.collection.mutable.ArrayBuffer

class RoundHistory(roundNum: Int):
  val matches = ArrayBuffer[MatchHistory]()

  def addMatch(matchRecord: MatchHistory): Unit = matches += matchRecord
  def getRoundNum() = roundNum
  def getMatches() = matches
