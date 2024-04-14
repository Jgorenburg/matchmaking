import scala.collection.mutable.HashMap
import scala.collection.mutable.Queue
import scala.util.Random
import scala.collection.mutable.ArrayBuffer
class Player(val champions: Vector[Champion]):
  val memory: HashMap[Champion, Record] =
    champions.foldRight(new HashMap())((champ, map) =>
      map += (champ -> Record())
    )
  def getWinPercent(champ: Champion): Float =
    return memory(champ).getWinPercent()

  def betterRecord(
      newChamp: Champion,
      curBest: Vector[Champion]
  ): Vector[Champion] =
    if curBest.isEmpty then Vector(newChamp)
    else if getWinPercent(newChamp) > getWinPercent(curBest(0)) then
      Vector(newChamp)
    else if getWinPercent(newChamp) < getWinPercent(curBest(0)) then curBest
    else curBest :+ newChamp

  def chooseChampion(oppChoice: Option[Champion]): Champion =
    def champFilter(curChamp: Champion): Boolean =
      oppChoice match
        case Some(champ) =>
          champ != curChamp
        case _ => true
    def pickAtRandom(list: Vector[Champion]) =
      list(Random.nextInt(list.size))
    pickAtRandom(
      champions
        .filter(champ => champFilter(champ))
        .foldRight(Vector[Champion]())((curChoice, champs) =>
          betterRecord(curChoice, champs)
        )
    )

  def chooseBlueChampion(): Champion = chooseChampion(None)
  def chooseRedChampion(champ: Champion): Champion = chooseChampion(Some(champ))

  def updateRecord(myChamp: Champion, oppChamp: Champion, win: Boolean) =
    memory(myChamp).updateRecord(win)
    memory(oppChamp).updateRecord(!win)
