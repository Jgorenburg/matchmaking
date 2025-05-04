package GameTypes
import scala.io.Source
import java.io.File
import scala.util.Random
import MetaTypes.{BasicMetaMaker, RandomRelationshipMeta}
import Base.{Champion, Default, GameMaker, MetaMaker, Playstyle, Specialized}
import Base.Meta
import MetaTypes.DefinedMeta

object RockPaperScissors extends GameMaker {
  val playstyles = Playstyle.makeStyles(Array("Rock", "Paper", "Scissors"))

  def makeChamps(numChamps: Int): Array[Champion] = {
    val listOfFishies = {
      val src = Source.fromInputStream(
        getClass.getClassLoader.getResourceAsStream("fishies.txt")
      )
      val lines = src.getLines.take(numChamps).toArray
      src.close
      lines
    }
    listOfFishies.zipWithIndex
      .map((name, style) => Specialized(name, playstyles(style % 3)))

  }

  def metaMaker: MetaMaker = new DefinedMeta(
    Map(
      playstyles(0) -> Map(
        playstyles(0) -> 0,
        playstyles(1) -> 0,
        playstyles(2) -> 2
      ),
      playstyles(1) -> Map(
        playstyles(0) -> 2,
        playstyles(1) -> 0,
        playstyles(2) -> 0
      ),
      playstyles(2) -> Map(
        playstyles(0) -> 0,
        playstyles(1) -> 2,
        playstyles(2) -> 0
      )
    )
  )
  def makeMeta(listOfChamps: Array[Champion]): Meta =
    metaMaker.makeMeta(listOfChamps.map((_, 1)).toMap)
}
