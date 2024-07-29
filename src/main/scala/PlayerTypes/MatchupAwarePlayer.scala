package PlayerTypes

import Base.{
  Champion,
  Composition,
  MatchupAware,
  MatchupAwareRecords,
  Player,
  PlayerMaker,
  SingleChampMemoryHandling
}
import scala.collection.mutable

class MatchupAwarePlayer(val champions: Array[Champion])
    extends Player
    with SingleChampMemoryHandling
    with MatchupAware:

  type RecordType = MatchupAwareRecords
  def makeRecord() = new MatchupAwareRecords(champions)
  def getChampion(
      offLimits: List[Champion],
      oppComp: Composition
  ): Champion =
    getBestRecord(champions, offLimits, oppComp)

object MatchupAwarePlayerMaker extends PlayerMaker:
  def makePlayer(champions: Array[Champion]) = new MatchupAwarePlayer(champions)
