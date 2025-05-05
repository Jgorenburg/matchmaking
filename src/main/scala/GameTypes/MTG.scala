package GameTypes
import scala.io.Source
import java.io.File
import scala.util.Random
import MetaTypes.{BasicMetaMaker, DefinedMeta, RandomRelationshipMeta}
import Base.{
  Champion,
  Default,
  GameMaker,
  Meta,
  MetaMaker,
  Playstyle,
  Specialized,
  StrengthFunctions
}

object MTG extends GameMaker {
  val playstyles =
    Playstyle.makeStyles(Array("White", "Blue", "Black", "Red", "Green"))

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
      .map((name, style) =>
        Specialized(name, playstyles(style % playstyles.length))
      )

  }

  def metaMaker: MetaMaker = new DefinedMeta(
    Map(
      playstyles(0) -> Map(
        playstyles(0) -> 0,
        playstyles(1) -> 2,
        playstyles(2) -> 2,
        playstyles(3) -> 0,
        playstyles(4) -> 0
      ),
      playstyles(1) -> Map(
        playstyles(0) -> 0,
        playstyles(1) -> 0,
        playstyles(2) -> 2,
        playstyles(3) -> 2,
        playstyles(4) -> 0
      ),
      playstyles(2) -> Map(
        playstyles(0) -> 0,
        playstyles(1) -> 0,
        playstyles(2) -> 0,
        playstyles(3) -> 2,
        playstyles(4) -> 2
      ),
      playstyles(3) -> Map(
        playstyles(0) -> 2,
        playstyles(1) -> 0,
        playstyles(2) -> 0,
        playstyles(3) -> 0,
        playstyles(4) -> 2
      ),
      playstyles(4) -> Map(
        playstyles(0) -> 2,
        playstyles(1) -> 2,
        playstyles(2) -> 0,
        playstyles(3) -> 0,
        playstyles(4) -> 0
      )
    )
  )
  def makeMeta(listOfChamps: Array[Champion]): Meta =
    metaMaker.makeMeta(listOfChamps, StrengthFunctions.allOne)
}
