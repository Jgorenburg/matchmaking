package Base

import scala.collection.mutable.HashMap

trait MemoryHandling:
  type RecordType <: Record
  def makeRecord(): RecordType
  def memory: HashMap[Champion, RecordType]
  def champions: Array[Champion]

trait SingleChampMemoryHandling extends MemoryHandling:
  final val memory: HashMap[Champion, RecordType] =
    champions.foldRight(new HashMap())((champ, map) =>
      map += (champ -> makeRecord())
    )
