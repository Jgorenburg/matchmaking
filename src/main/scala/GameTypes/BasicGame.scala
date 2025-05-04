package GameTypes

import Base.{Champion, Default, GameMaker, Meta, MetaMaker, Playstyle}
import scala.io.Source
import java.io.File
import MetaTypes.BasicMetaMaker

object BasicGame extends GameMaker {
  val playstyles = Array(Playstyle.Default)

  def makeChamps(numChamps: Int): Array[Champion] = {
    val listOfFishies = {
      val src = Source.fromInputStream(
        getClass.getClassLoader.getResourceAsStream("fishies.txt")
      )
      val lines = src.getLines.take(numChamps).toList
      src.close
      lines
    }
    listOfFishies.map(Default(_)).toArray
  }

  def metaMaker: MetaMaker = new BasicMetaMaker
  def makeMeta(
      listOfChamps: Array[Champion]
  ): Meta = metaMaker.makeMeta(listOfChamps)
}
