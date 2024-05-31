package base

import scala.util.Random

trait Winner {
  def decideWinner(teams: (Champion, Champion)): Side
}

trait PureSkillWinner extends Winner {
  def decideWinner(teams: (Champion, Champion)): Side =
    val (blueChamp, redChamp) = teams
    if blueChamp.skill > redChamp.skill then Side.Blueside else Side.Redside
}

trait SkillAndVarianceWinner extends Winner {
  def decideWinner(teams: (Champion, Champion)): Side =
    val (blueChamp, redChamp) = teams
    if blueChamp.skill > Random.nextInt(blueChamp.skill + redChamp.skill)
    then Side.Blueside
    else Side.Redside
}
