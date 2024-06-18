package Base

import scala.io.Source
import java.io.File

class GameConfig(
    val numPlayers: Int,
    val numChamps: Int,
    val playerMaker: PlayerMaker,
    val matchMaker: MatchMaker
):
  val ListOfChamps: Vector[Champion] = {
    val listOfFishies = {
      val src = Source.fromFile(
        new File(getClass.getClassLoader.getResource("fishies.txt").getPath)
      )
      val lines = src.getLines.take(numChamps).toList
      src.close
      lines
    }
    listOfFishies.zipWithIndex.map(tup => Champion(tup._1, tup._2)).toVector
  }
  var ListOfPlayers: Vector[Player] =
    (1 to numPlayers).toVector.map(i => playerMaker.makePlayer(ListOfChamps))

  def makeMatch(bluePlayer: Int, redPlayer: Int): Match =
    matchMaker.makeMatch(getPlayer(bluePlayer), getPlayer(redPlayer))

  def getPlayer(pos: Int): Player = ListOfPlayers(
    pos
  )
