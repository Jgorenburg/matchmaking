package Base

trait Player extends MemoryHandling with RecordGetter with RecordSetter:
  type RecordType <: Record

  override def toString(): String = super.toString().split("@")(1)

  def chooseChampion(
      bans: Option[List[Champion]],
      oppChoice: Option[Champion]
  ): Champion

  def chooseBlueChampion(
      bans: Option[List[Champion]] = None
  ): Champion =
    chooseChampion(bans, None)

  def chooseRedChampion(
      champ: Champion,
      bans: Option[List[Champion]] = None
  ): Champion = chooseChampion(
    bans,
    Some { champ }
  )
  // ban the counter to your best choice
  def blueBan(priorBans: Option[List[Champion]]): Champion =
    val wouldPick = chooseBlueChampion(priorBans)
    chooseRedChampion(wouldPick, priorBans)

  // ban what you view as the best champion
  def redBan(priorBans: Option[List[Champion]]): Champion =
    chooseBlueChampion(priorBans)

trait PlayerMaker:
  def makePlayer(champions: Array[Champion]): Player

trait MatchupBlind
    extends MatchupBlindRecordGetter
    with MatchupBlindRecordSetter
trait MatchupAware
    extends MatchupAwareRecordGetter
    with MatchupAwareRecordSetter
