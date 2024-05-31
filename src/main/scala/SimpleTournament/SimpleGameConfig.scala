package SimpleTournament

import base.{GameConfig, Champion, Player}
import SimpleTournament.SimplePlayer
import scala.io.Source
import java.io.File

class SimpleGameConfig(numPlayers: Int, numChamps: Int)
    extends GameConfig(numPlayers, numChamps):

  type TypeOfPlayer = SimplePlayer
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

  var ListOfPlayers: Vector[TypeOfPlayer] =
    (1 to numPlayers).toVector.map(i => SimplePlayer { ListOfChamps })

  def makeMatch(bluePlayer: Int, redPlayer: Int) =
    SimpleMatch(bluePlayer, redPlayer, this)
