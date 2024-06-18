package Base

import scala.io.Source
import java.io.File

class GameConfig(
    val numPlayers: Int,
    val numChamps: Int,
    val playerMaker: PlayerMaker,
    val matchMaker: MatchMaker
):
  val listOfChamps: Array[Champion] = {
    val listOfFishies = {
      val src = Source.fromFile(
        new File(getClass.getClassLoader.getResource("fishies.txt").getPath)
      )
      val lines = src.getLines.take(numChamps).toList
      src.close
      lines
    }
    listOfFishies.map(Champion(_)).toArray
  }

  val meta = new Meta(listOfChamps)

  var listOfPlayers: Vector[Player] =
    (1 to numPlayers).toVector.map(i => playerMaker.makePlayer(listOfChamps))

  def makeMatch(bluePlayer: Int, redPlayer: Int): Match =
    matchMaker.makeMatch(getPlayer(bluePlayer), getPlayer(redPlayer), meta)

  def getPlayer(pos: Int): Player = listOfPlayers(
    pos
  )
