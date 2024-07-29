package MatchTypes

import Base.{
  DraftWithBans,
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
      with DraftWithBans
      with SkillAndVarianceWinner
