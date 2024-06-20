package Base

import scala.util.Random

trait Winner {
  def meta: Meta
  def decideWinner(teams: (Champion, Champion)): Side.Value
}

trait PureSkillWinner extends Winner {
  def decideWinner(teams: (Champion, Champion)): Side.Value =
    val (blueChamp, redChamp) = teams
    if meta.champStrength(blueChamp) > meta.champStrength(redChamp) then
      Side.Blueside
    else Side.Redside
}

trait SkillAndVarianceWinner extends Winner {
  def decideWinner(teams: (Champion, Champion)): Side.Value =
    val (blueChamp, redChamp) = teams
    if meta.champStrength(blueChamp) > Random.nextInt(
        meta.champStrength(blueChamp) + meta.champStrength(redChamp)
      )
    then Side.Blueside
    else Side.Redside
}
