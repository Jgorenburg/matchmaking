import scala.collection.mutable.HashMap
import scala.collection.mutable.Queue
class Player(champions: Vector[Champion]):
  val matchHistory: Queue[Match]
  val memory: HashMap[Champion, Record] =
    champions.foldRight(new HashMap())((champ, map) =>
      map += (champ -> Record())
    )
  val champChoice: Champion = chooseChampion()

  def getWinPercent(champ: Champion): Float =
    return memory(champ).getWinPercent()

  def recordMatch(game: Match) =
    matchHistory += game
    val blueChampion = game.blueSide(1)
    val redChampion = game.redSide(1)
    if (game.winner == Side.Blueside)
      memory(blueChampion).wonGame()
      memory(redChampion).lostGame()
    else
      memory(redChampion).wonGame()
      memory(blueChampion).lostGame()

  def chooseChampion(oppChoice: Option[Champion]): Champion =
    def champFilter(curChamp: Champion): Boolean =
      if (!memory.contains(curChamp)) return false
      oppChoice match
        case Some(champ) =>
          return champ == curChamp
        case _ => return true
    val b: Champion =
      memory.foldRight(new Champion())((newChoice, curChamp) => newChoice(0))
    return b

class Record:
  var wins = 0
  var games = 0
  def updateRecord(won: Boolean) =
    games += 1
    if (won) wins += 1
  def wonGame() = updateRecord(true)
  def lostGame() = updateRecord(false)
  def getWinPercent(): Float =
    if (games == 0)
      return 0
    return wins / games
