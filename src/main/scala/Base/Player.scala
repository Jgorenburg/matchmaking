package Base

type Team = List[Player]
implicit def singlePlayerTeam(player: Player): Team = List(player)

trait Player extends MemoryHandling with RecordGetter with RecordSetter:
  type RecordType <: Record

  override def toString(): String = super.toString().split("@")(1)

  def getChampion(
      offLimits: List[Champion],
      oppComp: Composition = List()
  ): Champion

  def chooseChampion(
      comp: Composition,
      oppComp: Composition
  ): Champion = getChampion(comp ::: oppComp, oppComp)

  def chooseChampion(
      comp: Composition,
      oppComp: Composition,
      bans: List[Champion]
  ): Champion =
    getChampion(bans ::: comp ::: oppComp, oppComp)

  // ban the counter to your best choice
  def blueBan(priorBans: List[Champion] = List()): Champion =
    val wouldPick = getChampion(priorBans)
    chooseChampion(List(), wouldPick, priorBans)

  // ban what you view as the best champion
  def redBan(priorBans: List[Champion]): Champion =
    getChampion(priorBans)

trait PlayerMaker:
  def makePlayer(champions: Array[Champion]): Player

trait MatchupBlind
    extends MatchupBlindRecordGetter
    with MatchupBlindRecordSetter
trait MatchupAware
    extends MatchupAwareRecordGetter
    with MatchupAwareRecordSetter
