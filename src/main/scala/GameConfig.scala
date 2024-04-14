import scala.io.Source
import java.io.File

class GameConfig(numPlayers: Int, numChamps: Int):
  val ListOfChamps: Vector[Champion] = makeListOfChamps(numChamps)
  var ListOfPlayers: Vector[Player] =
    (1 to numPlayers).toVector.map(i => Player(ListOfChamps))

  def makeListOfChamps(numChamps: Int) =
    val listOfFishies = {
      val src = Source.fromFile(
        new File(getClass.getClassLoader.getResource("fishies.txt").getPath)
      )
      val lines = src.getLines.take(numChamps).toList
      src.close
      lines
    }
    listOfFishies.zipWithIndex.map(tup => Champion(tup._1, tup._2)).toVector
