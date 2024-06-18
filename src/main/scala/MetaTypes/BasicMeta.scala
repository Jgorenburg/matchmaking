package MetaTypes

import Base.{Champion, Meta, MetaMaker}
import scala.io.Source
import java.io.File

object BasicMetaMaker extends MetaMaker:
  def makeMeta(seed: Array[Champion]) = new Meta(seed)
  def makeMeta(seed: Map[Champion, Int]) = new Meta(seed)

  def makeChamps(numChamps: Int): Array[Champion] = {
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
