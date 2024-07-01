package Base

trait Player extends MemoryHandling with RecordGetter with RecordSetter:
  type RecordType <: Record

  def chooseChampion(
      notPlayable: Option[Array[Champion]],
      oppChoice: Option[Champion]
  ): Champion
  def chooseBlueChampion(): Champion = chooseChampion(None, None)
  def chooseRedChampion(champ: Champion): Champion = chooseChampion(
    Some(Array { champ }),
    Some { champ }
  )

  override def toString(): String = super.toString().split("@")(1)

trait PlayerMaker:
  def makePlayer(champions: Array[Champion]): Player

trait MatchupBlind
    extends MatchupBlindRecordGetter
    with MatchupBlindRecordSetter
trait MatchupAware
    extends MatchupAwareRecordGetter
    with MatchupAwareRecordSetter
