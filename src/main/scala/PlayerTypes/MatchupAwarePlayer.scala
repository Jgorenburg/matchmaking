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
      bans: Option[List[Champion]],
      oppChoice: Option[Champion]
  ): Champion =
    val unplayable = (bans, oppChoice) match
      case (Some(arr), Some(champ)) => Some(arr :+ champ)
      case (None, Some(champ))      => Some(List(champ))
      case (_, None)                => bans
    getBestRecord(champions, unplayable, oppChoice)

object MatchupAwarePlayerMaker extends PlayerMaker:
  def makePlayer(champions: Array[Champion]) = new MatchupAwarePlayer(champions)
