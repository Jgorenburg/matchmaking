package Base

import scala.collection.mutable.HashMap

trait RecordSetter:
  type RecordType <: Record
  def memory: HashMap[Champion, RecordType]
  def updateRecord(myChamp: Champion, oppChamp: Champion, win: Boolean): Unit

trait MatchupBlindRecordSetter extends RecordSetter:
  type RecordType = Record
  def updateRecord(myChamp: Champion, oppChamp: Champion, win: Boolean) =
    memory(myChamp).updateRecord(win)
    memory(oppChamp).updateRecord(!win)

trait MatchupAwareRecordSetter extends RecordSetter:
  type RecordType = MatchupAwareRecords
  def updateRecord(myChamp: Champion, oppChamp: Champion, win: Boolean) =
    memory(myChamp).updateRecord(oppChamp, win)
    memory(oppChamp).updateRecord(myChamp, !win)
