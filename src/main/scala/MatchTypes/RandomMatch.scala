package MatchTypes

import Base.{
  Match,
  MatchMaker,
  Meta,
  Player,
  SimpleTeamMaker,
  SkillAndVarianceWinner
}

object RandomMatchMaker extends MatchMaker:
  def makeMatch(bluePlayer: Player, redPlayer: Player, meta: Meta) =
    new Match(bluePlayer, redPlayer, meta)
      with SimpleTeamMaker
      with SkillAndVarianceWinner
