package Base

import scala.collection.mutable.HashMap
import scala.util.Random

trait WinRecord:
  def memory: HashMap[Champion, Record]

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
