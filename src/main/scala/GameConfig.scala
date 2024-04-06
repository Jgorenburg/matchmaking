import scala.io.Source

class GameConfig(numPlayers: Int, numChamps: Int):
  val ListOfChamps: Vector[Champion] = GameConfig.makeListOfChamps(numChamps)
  var ListOfPlayers: Vector[Player] =
    (1 to numPlayers).toVector.map(i => Player(ListOfChamps))

object GameConfig:
  val filename = "../../Resources/Fishies.txt"
  val bufferedSource = Source.fromFile(filename)

  def makeListOfChamps(numChamps: Int) =
    val listOfFishies = {
      val src = Source.fromFile(filename)
      val lines = src.getLines.take(numChamps).toList
      src.close
      lines
    }
    listOfFishies.zipWithIndex.map(tup => Champion(tup._1, tup._2)).toVector
