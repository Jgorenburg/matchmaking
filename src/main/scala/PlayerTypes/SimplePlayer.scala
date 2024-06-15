package PlayerTypes

import scala.collection.mutable.HashMap
import scala.collection.mutable.Queue
import scala.util.Random
import scala.collection.mutable.ArrayBuffer
import Base.{Champion, Player, PlayerMaker}
import Base.{SingleChampMemoryHandling, Record, WinRecord}

class SimplePlayer(val champions: Vector[Champion])
    extends Player
    with SingleChampMemoryHandling:

  def chooseChampion(oppChoice: Option[Vector[Champion]]): Champion =
    getBestRecord(champions, oppChoice)
  def chooseBlueChampion(): Champion = chooseChampion(None)
  def chooseRedChampion(champ: Champion): Champion = chooseChampion(
    Some(Vector { champ })
  )

object SimplePlayerMaker extends PlayerMaker:
  def makePlayer(champions: Vector[Champion]) = new SimplePlayer(champions)
