package MatchTypes

import Base.{Match, MatchMaker, Player, SimpleTeamMaker, SkillAndVarianceWinner}

class RandomMatch(
    val bluePlayer: Player,
    val redPlayer: Player
) extends Match
    with SimpleTeamMaker
    with SkillAndVarianceWinner

object RandomMatchMaker extends MatchMaker:
  def makeMatch(bluePlayer: Player, redPlayer: Player) =
    new RandomMatch(bluePlayer, redPlayer)
