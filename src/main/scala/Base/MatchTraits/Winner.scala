package Base

import scala.util.Random

trait Winner {
  def decideWinner(teams: (Champion, Champion)): Side.Value
}

trait PureSkillWinner extends Winner {
  def decideWinner(teams: (Champion, Champion)): Side.Value =
    val (blueChamp, redChamp) = teams
    if blueChamp.skill > redChamp.skill then Side.Blueside else Side.Redside
}

trait SkillAndVarianceWinner extends Winner {
  def decideWinner(teams: (Champion, Champion)): Side.Value =
    val (blueChamp, redChamp) = teams
    if blueChamp.skill > Random.nextInt(blueChamp.skill + redChamp.skill)
    then Side.Blueside
    else Side.Redside
}
