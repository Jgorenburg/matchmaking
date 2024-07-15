package MatchTypes

import Base.{
  BansAndSinglePlayer,
  Match,
  MatchMaker,
  Meta,
  Player,
  SkillAndVarianceWinner
}
import Base.PureSkillWinner

object SimpleWithBansMatchmaker extends MatchMaker:
  def makeMatch(bluePlayer: Player, redPlayer: Player, meta: Meta) =
    new Match(bluePlayer, redPlayer, meta)
      with BansAndSinglePlayer
      with PureSkillWinner
