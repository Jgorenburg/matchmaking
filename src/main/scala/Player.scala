import scala.collection.mutable.HashMap
import scala.collection.mutable.Queue
class Player(val champions: Vector[Champion]):
  val memory: HashMap[Champion, Record] =
    champions.foldRight(new HashMap())((champ, map) =>
      map += (champ -> Record())
    )
  def getWinPercent(champ: Champion): Float =
    return memory(champ).getWinPercent()

  def betterRecord(champ1: Champion, champ2: Champion): Champion =
    if getWinPercent(champ1) >= getWinPercent(champ2) then champ1 else champ2

  def chooseChampion(oppChoice: Option[Champion]): Champion =
    def champFilter(curChamp: Champion): Boolean =
      oppChoice match
        case Some(champ) =>
          champ == curChamp
        case _ => true
    champions
      .filter(champ => champFilter(champ))
      .foldRight(champions(1))((curChoice, champ) =>
        betterRecord(curChoice, champ)
      )

  def chooseBlueChampion(): Champion = chooseChampion(None)
  def chooseRedChampion(champ: Champion): Champion = chooseChampion(Some(champ))

  def updateRecord(champ: Champion, win: Boolean) =
    memory(champ).updateRecord(win);
