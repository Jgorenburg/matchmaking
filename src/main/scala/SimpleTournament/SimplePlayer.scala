package SimpleTournament

import scala.collection.mutable.HashMap
import scala.collection.mutable.Queue
import scala.util.Random
import scala.collection.mutable.ArrayBuffer
import base.{Champion, Player, SingleChampMemoryHandling, Record, WinRecord}

class SimplePlayer(val champions: Vector[Champion])
    extends Player,
      SingleChampMemoryHandling,
      WinRecord:

  def chooseChampion(oppChoice: Option[Vector[Champion]]): Champion =
    getBestRecord(champions, oppChoice)
  def chooseBlueChampion(): Champion = chooseChampion(None)
  def chooseRedChampion(champ: Champion): Champion = chooseChampion(
    Some(Vector { champ })
  )
