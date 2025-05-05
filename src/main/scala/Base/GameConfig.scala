package Base

import scala.io.Source
import java.io.File

class GameConfig(
    val numPlayers: Int,
    val numChamps: Int,
    val teamSize: Int,
    val playerMaker: PlayerMaker,
    val matchMaker: MatchMaker,
    val gameMaker: GameMaker,
    val strengthFunction: Option[Array[Champion] => Map[Champion, Int]] = None
):
  val listOfChamps: Array[Champion] =
    gameMaker.makeChamps(numChamps)
  val meta: Meta =
    strengthFunction match
      case Some(f) => gameMaker.makeMeta(listOfChamps, f)
      case None    => gameMaker.makeMeta(listOfChamps)

  var listOfPlayers: Vector[Player] =
    val listOfFlowers = {
      val src = Source.fromInputStream(
        getClass.getClassLoader.getResourceAsStream("flowersAndRocks.txt")
      )
      val lines = src.getLines.take(numPlayers).toArray
      src.close
      lines
    }
    listOfFlowers.toVector.map(f => playerMaker.makePlayer(f, listOfChamps))

  // def makeMatch(blueTeam: List[Int], redTeam: List[Int]): Match =
  //   matchMaker.makeMatch(blueTeam.map(getPlayer), redTeam.map(getPlayer), meta)

  def makeMatch(startPos: Int): Match =
    val blueTeam =
      for (pos <- startPos until startPos + teamSize) yield getPlayer(pos)
    val redTeam =
      for (pos <- startPos + teamSize until startPos + 2 * teamSize)
        yield getPlayer(pos)
    matchMaker.makeMatch(blueTeam.toList, redTeam.toList, meta)

  def getPlayer(pos: Int): Player = listOfPlayers(pos)
