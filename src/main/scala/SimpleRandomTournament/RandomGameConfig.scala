package SimpleRandomTournament

import Base.{GameConfig, Champion, Player}
import scala.io.Source
import java.io.File
import SimpleTournament.SimplePlayer

class RandomGameConfig(val numPlayers: Int, val numChamps: Int)
    extends GameConfig:

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
    RandomMatch(bluePlayer, redPlayer, this)
