package Base

import scala.util.Random

trait Winner {
  def meta: Meta
  def decideWinner(teamComps: (Composition, Composition)): Side.Value
  def decideWinner(blueComp: Composition, redComp: Composition): Side.Value =
    decideWinner((blueComp, redComp))
}

trait PureSkillWinner extends Winner {
  def decideWinner(teamComps: (Composition, Composition)): Side.Value =
    val (blueStrength, redStrength) = meta.powerComparison(teamComps)
    if blueStrength > redStrength then Side.Blueside
    else Side.Redside
}

trait SkillAndVarianceWinner extends Winner {
  def decideWinner(teamComps: (Composition, Composition)): Side.Value =
    val (blueStrength, redStrength) = meta.powerComparison(teamComps)
    if blueStrength > Random.nextInt(
        blueStrength + redStrength
      )
    then Side.Blueside
    else Side.Redside
}
