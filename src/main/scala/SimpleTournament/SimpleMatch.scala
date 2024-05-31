package SimpleTournament

import Base.{Match, SimpleTeamMaker, PureSkillWinner}

class SimpleMatch(bluePlayer: Int, redPlayer: Int, config: SimpleGameConfig)
    extends Match(bluePlayer, redPlayer, config),
      SimpleTeamMaker,
      PureSkillWinner
