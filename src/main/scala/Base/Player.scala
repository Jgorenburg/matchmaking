package Base

import scala.collection.mutable.HashMap
import scala.collection.mutable.Queue
import scala.util.Random
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable

trait Player extends MemoryHandling:

  val memory: HashMap[Champion, Record]
  def chooseChampion(oppChoice: Option[Vector[Champion]]): Champion
  def chooseBlueChampion(): Champion
  def chooseRedChampion(champ: Champion): Champion

  override def toString(): String = super.toString().split("@")(1)

trait WinRecord:
  val memory: HashMap[Champion, Record]

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

trait MemoryHandling:
  val memory: HashMap[Champion, Record]
  val champions: Vector[Champion]

  def updateRecord(myChamp: Champion, oppChamp: Champion, win: Boolean) =
    memory(myChamp).updateRecord(win)
    memory(oppChamp).updateRecord(!win)

trait SingleChampMemoryHandling extends MemoryHandling:

  val memory: HashMap[Champion, Record] =
    champions.foldRight(new HashMap())((champ, map) =>
      map += (champ -> Record())
    )
