package SimpleRandomTournament

import base.{Match, SimpleTeamMaker, SkillAndVarianceWinner}

class RandomMatch(bluePlayer: Int, redPlayer: Int, config: RandomGameConfig)
    extends Match(bluePlayer, redPlayer, config),
      SimpleTeamMaker,
      SkillAndVarianceWinner
