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

class SimplePlayer(name: String, val champions: Array[Champion])
    extends Player(name)
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
  def makePlayer(name: String, champions: Array[Champion]) =
    new SimplePlayer(name, champions)
