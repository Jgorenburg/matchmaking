package MatchTypes

import Base.{
  Match,
  MatchMaker,
  Meta,
  Player,
  SimpleTeamMaker,
  SkillAndVarianceWinner,
  Team
}

object RandomMatchMaker extends MatchMaker:
  val pureSkill: Boolean = false
  def makeMatch(blueTeam: Team, redTeam: Team, meta: Meta) =
    new Match(blueTeam, redTeam, meta)
      with SimpleTeamMaker
      with SkillAndVarianceWinner
