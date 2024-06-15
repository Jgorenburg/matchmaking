package MatchTypes

import Base.{Match, MatchMaker, Player, SimpleTeamMaker, PureSkillWinner}

class SimpleMatch(
    val bluePlayer: Player,
    val redPlayer: Player
) extends Match
    with SimpleTeamMaker
    with PureSkillWinner

object SimpleMatchMaker extends MatchMaker:
  def makeMatch(bluePlayer: Player, redPlayer: Player) =
    new SimpleMatch(bluePlayer, redPlayer)
