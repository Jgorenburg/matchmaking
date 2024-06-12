package Base

import scala.collection.mutable.HashMap
import scala.util.Random

trait WinRecord:
  def memory: HashMap[Champion, Record]

  def getWinPercent(champ: Champion): Float =
    return memory(champ).getWinPercent

  def betterRecord(
      newChamp: Champion,
      curBest: Vector[Champion]
  ): Vector[Champion] =
    if curBest.isEmpty then Vector(newChamp)
    else if getWinPercent(newChamp) > getWinPercent(curBest(0)) then
      Vector(newChamp)
    else if getWinPercent(newChamp) < getWinPercent(curBest(0)) then curBest
    else curBest :+ newChamp

  def getBestRecord(
      listOfChampions: Vector[Champion],
      disallowed: Option[Vector[Champion]]
  ): Champion =
    def champFilter(curChamp: Champion): Boolean =
      disallowed match
        case Some(champs) =>
          !champs.contains(curChamp)
        case _ => true
    def pickAtRandom(list: Vector[Champion]) =
      list(Random.nextInt(list.size))
    pickAtRandom(
      listOfChampions
        .filter(champ => champFilter(champ))
        .foldRight(Vector[Champion]())((curChoice, champs) =>
          betterRecord(curChoice, champs)
        )
    )
