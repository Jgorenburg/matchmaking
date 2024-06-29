package Base

class Meta(
    definedMeta: Map[Champion, Int],
    relationshipChart: Map[Playstyle.Value, Map[Playstyle.Value, Int]]
):

  val champStrength: Map[Champion, Int] = definedMeta
  def getStrength(champ: Champion) = champStrength(champ)
  def powerComparison(teams: (Champion, Champion)): (Int, Int) =
    (getStrength(teams._1), getStrength(teams._2))

  def styleComparison(champ: Champion, oppChamp: Champion): Int =
    relationshipChart(champ.playstyle)(oppChamp.playstyle)

trait MetaMaker:
  // default strengths from 0-X
  def makeMeta(
      seed: Array[Champion],
      playstyles: Array[Playstyle.Value]
  ): Meta = makeMeta(seed.zipWithIndex.toMap, playstyles)
  def makeMeta(
      seed: Map[Champion, Int],
      playstles: Array[Playstyle.Value]
  ): Meta
