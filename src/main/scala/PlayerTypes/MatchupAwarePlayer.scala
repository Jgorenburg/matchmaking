package PlayerTypes

import Base.{
  Champion,
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
  def chooseChampion(
      notPlayable: Option[Array[Champion]],
      oppChoice: Option[Champion]
  ): Champion =
    getBestRecord(champions, notPlayable, oppChoice)

object MatchupAwarePlayerMaker extends PlayerMaker:
  def makePlayer(champions: Array[Champion]) = new MatchupAwarePlayer(champions)
