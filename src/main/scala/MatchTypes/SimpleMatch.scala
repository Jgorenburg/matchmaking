package MatchTypes

import Base.{
  Match,
  MatchMaker,
  Meta,
  Player,
  SimpleTeamMaker,
  PureSkillWinner,
  Team
}

object SimpleMatchMaker extends MatchMaker:
  val pureSkill: Boolean = true
  def makeMatch(blueTeam: Team, redTeam: Team, meta: Meta) =
    new Match(blueTeam, redTeam, meta) with SimpleTeamMaker with PureSkillWinner
