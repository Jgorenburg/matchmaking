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

object RandomWithBansMatchmaker extends MatchMaker:
  val pureSkill: Boolean = false

  def makeMatch(blueTeam: Team, redTeam: Team, meta: Meta) =
    new Match(blueTeam, redTeam, meta)
      with BansAndSinglePlayer
      with SkillAndVarianceWinner
