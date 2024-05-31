package SimpleTournament

import base.{Match, SimpleTeamMaker, PureSkillWinner}

class SimpleMatch(bluePlayer: Int, redPlayer: Int, config: SimpleGameConfig)
    extends Match(bluePlayer, redPlayer, config),
      SimpleTeamMaker,
      PureSkillWinner
