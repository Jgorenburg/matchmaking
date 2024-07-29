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
import Base.PureSkillWinner

object SimpleWithBansMatchmaker extends MatchMaker:
  val pureSkill = true
  def makeMatch(blueTeam: Team, redTeam: Team, meta: Meta) =
    new Match(blueTeam, redTeam, meta) with DraftWithBans with PureSkillWinner
