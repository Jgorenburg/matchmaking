import scala.io.Source

class GameConfig(numChamps:Int): 
    val ListOfChamps: Vector[Champion] = GameConfig.makeListOfChamps(numChamps)


object GameConfig:
    val filename = "../../Resources/Fishies.txt"
    val bufferedSource = Source.fromFile(filename)

    private def makeListOfChamps(numChamps:Int) = 
       Vector(Champion(20),Champion(2))





