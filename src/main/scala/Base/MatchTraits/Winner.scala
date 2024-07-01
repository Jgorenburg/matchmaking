package Base

import scala.util.Random

trait Winner {
  def meta: Meta
  def decideWinner(teams: (Champion, Champion)): Side.Value
}

trait PureSkillWinner extends Winner {
  def decideWinner(teams: (Champion, Champion)): Side.Value =
    val (blueStrength, redStrength) = meta.powerComparison(teams)
    if blueStrength > redStrength then Side.Blueside
    else Side.Redside
}

trait SkillAndVarianceWinner extends Winner {
  def decideWinner(teams: (Champion, Champion)): Side.Value =
    val (blueStrength, redStrength) = meta.powerComparison(teams)
    if blueStrength > Random.nextInt(
        blueStrength + redStrength
      )
    then Side.Blueside
    else Side.Redside
}
