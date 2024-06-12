package SimpleRandomTournament

import Base.{Match, SimpleTeamMaker, SkillAndVarianceWinner}

class RandomMatch(
    val bluePlayer: Int,
    val redPlayer: Int,
    val config: RandomGameConfig
) extends Match
    with SimpleTeamMaker
    with SkillAndVarianceWinner
