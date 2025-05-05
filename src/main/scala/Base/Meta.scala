package Base

class Meta(
    definedMeta: Map[Champion, Int],
    relationshipChart: Map[Playstyle.Value, Map[Playstyle.Value, Int]]
):

  val champStrength: Map[Champion, Int] = definedMeta
  def getStrength(champ: Champion) = champStrength(champ)
  def getStrengths(teams: (Champion, Champion)): (Int, Int) =
    (getStrength(teams._1), getStrength(teams._2))

  def styleComparison(champ: Champion, oppChamp: Champion): Int =
    relationshipChart(champ.playstyle)(oppChamp.playstyle)

  def powerComparison(teamComps: (Composition, Composition)): (Int, Int) =
    def addChampsStrength(
        oppTeam: Composition
    )(champ: Champion, curTeamStrength: Int): Int =
      oppTeam.foldRight(getStrength(champ))((oppChamp: Champion, sum: Int) =>
        sum + styleComparison(champ, oppChamp)
      ) + curTeamStrength
    (
      teamComps._1.foldRight(0)(addChampsStrength(teamComps._2)),
      teamComps._2.foldRight(0)(addChampsStrength(teamComps._1))
    )

trait MetaMaker:
  // default strengths from 0-X
  def makeMeta(
      champseed: Array[Champion]
  ): Meta = makeMeta(champseed.zipWithIndex.toMap)
  // pre-formed champ strength
  def makeMeta(
      champseed: Map[Champion, Int]
  ): Meta

  def makeMeta(
      champseed: Array[Champion],
      stregnthFunct: Array[Champion] => Map[Champion, Int]
  ): Meta = makeMeta(stregnthFunct(champseed))
