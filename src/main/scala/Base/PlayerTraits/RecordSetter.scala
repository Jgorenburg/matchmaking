package Base

import scala.collection.mutable.HashMap

trait RecordSetter:
  type RecordType <: Record
  def memory: HashMap[Champion, RecordType]
  def updateRecord(
      myTeamComp: Composition,
      oppTeamComp: Composition,
      win: Boolean
  ): Unit

trait MatchupBlindRecordSetter extends RecordSetter:
  type RecordType = Record
  def updateRecord(
      myTeamComp: Composition,
      oppTeamComp: Composition,
      win: Boolean
  ) =
    myTeamComp.map(memory(_).updateRecord(win))
    oppTeamComp.map(memory(_).updateRecord(!win))

trait MatchupAwareRecordSetter extends RecordSetter:
  type RecordType = MatchupAwareRecords
  def updateRecord(
      myTeamComp: Composition,
      oppTeamComp: Composition,
      win: Boolean
  ) =
    myTeamComp.map(memory(_).updateRecord(oppTeamComp, win))
    oppTeamComp.map(memory(_).updateRecord(myTeamComp, !win))
