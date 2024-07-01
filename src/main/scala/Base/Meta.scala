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

  def powerComparison(teams: (Champion, Champion)): (Int, Int) =
    (
      getStrength(teams._1) + styleComparison(teams._1, teams._2),
      getStrength(teams._2) + styleComparison(teams._2, teams._1)
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
