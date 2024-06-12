package SimpleTournament

import Base.{Match, SimpleTeamMaker, PureSkillWinner}

class SimpleMatch(
    val bluePlayer: Int,
    val redPlayer: Int,
    val config: SimpleGameConfig
) extends Match
    with SimpleTeamMaker
    with PureSkillWinner
