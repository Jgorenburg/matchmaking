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
      notPlayable: Option[Array[Champion]],
      oppChoice: Option[Champion]
  ): Champion =
    getBestRecord(champions, notPlayable)

object SimplePlayerMaker extends PlayerMaker:
  def makePlayer(champions: Array[Champion]) = new SimplePlayer(champions)
