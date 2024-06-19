package MatchTypes

import Base.{Match, MatchMaker, Meta, Player, SimpleTeamMaker, PureSkillWinner}

object SimpleMatchMaker extends MatchMaker:
  def makeMatch(bluePlayer: Player, redPlayer: Player, meta: Meta) =
    new Match(bluePlayer, redPlayer, meta)
      with SimpleTeamMaker
      with PureSkillWinner
