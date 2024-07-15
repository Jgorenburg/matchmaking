package MatchTypes

  import Base.{BansAndSinglePlayer,Match, MatchMaker, Meta, Player, SkillAndVarianceWinner}


object RandomWithBansMatchmaker extends MatchMaker:
  def makeMatch(bluePlayer: Player, redPlayer: Player, meta: Meta) =
    new Match(bluePlayer, redPlayer, meta)
      with BansAndSinglePlayer
      with SkillAndVarianceWinner

