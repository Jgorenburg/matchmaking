package MatchTypes

import Base.{
  BansAndSinglePlayer,
  Match,
  MatchMaker,
  Meta,
  Player,
  SkillAndVarianceWinner,
  Team
}
import Base.PureSkillWinner

object SimpleWithBansMatchmaker extends MatchMaker:
  def makeMatch(blueTeam: Team, redTeam: Team, meta: Meta) =
    new Match(blueTeam, redTeam, meta)
      with BansAndSinglePlayer
      with PureSkillWinner
