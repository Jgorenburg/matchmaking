package SimpleTournament

import base.{Champion, Match, MatchHistory, Side}

class SimpleMatch(bluePlayer: Int, redPlayer: Int, config: SimpleGameConfig)
    extends Match(bluePlayer, redPlayer, config):

  def runMatch(): MatchHistory =
    val blueChamp: Champion =
      config.getPlayer(bluePlayer).chooseBlueChampion()
    val redChamp: Champion =
      config.getPlayer(redPlayer).chooseRedChampion(blueChamp)
    val winner: Side = decideWinner(blueChamp, redChamp)
    updateRecords(blueChamp, redChamp, winner)
    return MatchHistory(
      (config.getPlayer(bluePlayer), blueChamp),
      (config.getPlayer(redPlayer), redChamp),
      winner
    )

  def decideWinner(blueChamp: Champion, redChamp: Champion): Side =
    if blueChamp.skill > redChamp.skill then Side.Blueside else Side.Redside
