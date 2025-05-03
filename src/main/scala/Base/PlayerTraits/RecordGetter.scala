package Base

import scala.collection.mutable.HashMap
import scala.util.Random

trait RecordGetter:
  type RecordType <: Record
  def memory: HashMap[Champion, RecordType]

  def getTotalWins(): Int =
    return memory.values.map(record => record.wins).sum.toInt

  def getTotalGames(): Int =
    return memory.values.map(record => record.games).sum.toInt

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
      disallowed: List[Champion]
  ): Champion =
    val pickableChamps = listOfChampions.filter(!disallowed.contains(_))
    assert(!pickableChamps.isEmpty)
    val bestChamps: Vector[Champion] =
      (Vector(pickableChamps.head) /: pickableChamps.tail)(betterRecord)

    bestChamps(Random.nextInt(bestChamps.size))

trait MatchupBlindRecordGetter extends RecordGetter:
  type RecordType = Record

trait MatchupAwareRecordGetter extends RecordGetter:
  type RecordType = MatchupAwareRecords

  def getWinPercent(champ: Champion, oppComp: Composition): Float =
    return oppComp.foldRight(0.0f)((oppChamp, soFar) =>
      getWinPercent(champ, oppChamp)
    )
      / oppComp.length
  def getWinPercent(champ: Champion, oppChamp: Champion): Float =
    return memory(champ).getWinPercent(oppChamp)

  def betterRecord(oppComp: Composition)(
      curBest: Vector[Champion],
      newChamp: Champion
  ): Vector[Champion] =
    oppComp match
      case List() => betterRecord(curBest, newChamp)
      case _ =>
        if curBest.isEmpty then Vector(newChamp)
        else if getWinPercent(newChamp, oppComp) > getWinPercent(
            curBest(0),
            oppComp
          )
        then Vector(newChamp)
        else if getWinPercent(newChamp, oppComp) < getWinPercent(
            curBest(0),
            oppComp
          )
        then curBest
        else curBest :+ newChamp

  def getBestRecord(
      listOfChampions: Array[Champion],
      disallowed: List[Champion],
      oppComp: Composition
  ): Champion =
    val pickableChamps = listOfChampions.filter(!disallowed.contains(_))
    assert(!pickableChamps.isEmpty)
    val bestChamps: Vector[Champion] =
      (Vector(pickableChamps.head) /: pickableChamps.tail)(
        betterRecord(oppComp)
      )

    bestChamps(Random.nextInt(bestChamps.size))
