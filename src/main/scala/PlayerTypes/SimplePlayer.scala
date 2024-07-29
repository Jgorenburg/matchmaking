package PlayerTypes

import scala.collection.mutable.HashMap
import scala.collection.mutable.Queue
import scala.util.Random
import scala.collection.mutable.ArrayBuffer
import Base.{
  Champion,
  Composition,
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
  def getChampion(
      offLimits: List[Champion],
      oppComp: Composition
  ): Champion =
    getBestRecord(champions, offLimits)

object SimplePlayerMaker extends PlayerMaker:
  def makePlayer(champions: Array[Champion]) = new SimplePlayer(champions)
