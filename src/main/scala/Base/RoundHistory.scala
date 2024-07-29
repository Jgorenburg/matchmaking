package Base

import scala.collection.mutable.ArrayBuffer

class RoundHistory(val roundNum: Int):
  val matches = ArrayBuffer[MatchHistory]()

  def addMatch(game: Match): Unit = matches += game.history
