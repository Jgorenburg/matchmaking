package MatchTypes

import Base.{Match, MatchMaker, Player, SimpleTeamMaker, PureSkillWinner}
import Base.Meta

class SimpleMatch(
    val bluePlayer: Player,
    val redPlayer: Player,
    val meta: Meta
) extends Match
    with SimpleTeamMaker
    with PureSkillWinner

object SimpleMatchMaker extends MatchMaker:
  def makeMatch(bluePlayer: Player, redPlayer: Player, meta: Meta) =
    new SimpleMatch(bluePlayer, redPlayer, meta)
