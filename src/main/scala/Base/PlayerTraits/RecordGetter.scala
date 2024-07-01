package Base

import scala.collection.mutable.HashMap
import scala.util.Random

trait RecordGetter:
  type RecordType <: Record
  def memory: HashMap[Champion, RecordType]

  def getWinPercent(champ: Champion): Float =
    return memory(champ).getWinPercent

  def betterRecord(
      curBest: Vector[Champion],
      newChamp: Champion
  ): Vector[Champion] =
    if curBest.isEmpty then Vector(newChamp)
    else if getWinPercent(newChamp) > getWinPercent(curBest(0)) then
      Vector(newChamp)
    else if getWinPercent(newChamp) < getWinPercent(curBest(0)) then curBest
    else curBest :+ newChamp

  def getBestRecord(
      listOfChampions: Array[Champion],
      disallowed: Option[Array[Champion]]
  ): Champion =
    def champFilter(curChamp: Champion): Boolean =
      disallowed match
        case Some(champs) =>
          !champs.contains(curChamp)
        case _ => true
    val pickableChamps = listOfChampions.filter(champFilter)

    assert(!pickableChamps.isEmpty)
    val bestChamps: Vector[Champion] =
      (Vector(pickableChamps.head) /: pickableChamps.tail)(betterRecord)

    bestChamps(Random.nextInt(bestChamps.size))

trait MatchupBlindRecordGetter extends RecordGetter:
  type RecordType = Record

trait MatchupAwareRecordGetter extends RecordGetter:
  type RecordType = MatchupAwareRecords

  def getWinPercent(champ: Champion, oppChamp: Champion): Float =
    return memory(champ).getWinPercent(oppChamp)

  def betterRecord(oppChamp: Option[Champion])(
      curBest: Vector[Champion],
      newChamp: Champion
  ): Vector[Champion] =
    oppChamp match
      case None => betterRecord(curBest, newChamp)
      case Some(champ) =>
        if curBest.isEmpty then Vector(newChamp)
        else if getWinPercent(newChamp, champ) > getWinPercent(
            curBest(0),
            champ
          )
        then Vector(newChamp)
        else if getWinPercent(newChamp, champ) < getWinPercent(
            curBest(0),
            champ
          )
        then curBest
        else curBest :+ newChamp

  def getBestRecord(
      listOfChampions: Array[Champion],
      disallowed: Option[Array[Champion]],
      oppChamp: Option[Champion]
  ): Champion =
    def champFilter(curChamp: Champion): Boolean =
      disallowed match
        case Some(champs) =>
          !champs.contains(curChamp)
        case _ => true
    val pickableChamps = listOfChampions.filter(champFilter)

    assert(!pickableChamps.isEmpty)
    val bestChamps: Vector[Champion] =
      (Vector(pickableChamps.head) /: pickableChamps.tail)(
        betterRecord(oppChamp)
      )

    bestChamps(Random.nextInt(bestChamps.size))
