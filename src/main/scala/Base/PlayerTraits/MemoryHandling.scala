package Base

import scala.collection.mutable.HashMap

trait MemoryHandling:
  def memory: HashMap[Champion, Record]
  def champions: Vector[Champion]

  def updateRecord(myChamp: Champion, oppChamp: Champion, win: Boolean) =
    memory(myChamp).updateRecord(win)
    memory(oppChamp).updateRecord(!win)

trait SingleChampMemoryHandling extends MemoryHandling:

  final val memory: HashMap[Champion, Record] =
    champions.foldRight(new HashMap())((champ, map) =>
      map += (champ -> Record())
    )
