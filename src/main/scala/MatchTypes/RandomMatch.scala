package MatchTypes

import Base.{
  Match,
  MatchMaker,
  Meta,
  Player,
  SimpleTeamMaker,
  SkillAndVarianceWinner
}

class RandomMatch(
    val bluePlayer: Player,
    val redPlayer: Player,
    val meta: Meta
) extends Match
    with SimpleTeamMaker
    with SkillAndVarianceWinner

object RandomMatchMaker extends MatchMaker:
  def makeMatch(bluePlayer: Player, redPlayer: Player, meta: Meta) =
    new RandomMatch(bluePlayer, redPlayer, meta)
