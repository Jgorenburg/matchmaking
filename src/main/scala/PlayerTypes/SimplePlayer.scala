package PlayerTypes

import scala.collection.mutable.HashMap
import scala.collection.mutable.Queue
import scala.util.Random
import scala.collection.mutable.ArrayBuffer
import Base.{
  Champion,
  MatchupBlind,
  MatchupBlindRecordGetter,
  Player,
  PlayerMaker,
  Record,
  RecordSetter,
  RecordGetter,
  SingleChampMemoryHandling
}

class SimplePlayer(val champions: Array[Champion])
    extends Player
    with SingleChampMemoryHandling
    with MatchupBlind:

  type RecordType = Record
  def makeRecord() = new Record
  def chooseChampion(
      bans: Option[List[Champion]],
      oppChoice: Option[Champion]
  ): Champion =
    val unplayable = (bans, oppChoice) match
      case (Some(arr), Some(champ)) => Some(arr :+ champ)
      case (None, Some(champ))      => Some(List(champ))
      case (_, None)                => bans
    getBestRecord(champions, unplayable)

object SimplePlayerMaker extends PlayerMaker:
  def makePlayer(champions: Array[Champion]) = new SimplePlayer(champions)
