package Base

trait RulesOfPlay extends TeamMaker, Winner {
  val history: MatchHistory
  def updateRecords(teams: (Champion, Champion), winner: Side): Unit
}

// trait SimpleGame extends RulesOfPlay {
//   def runMatch(): MatchHistory =
//     val blueChamp: Champion =
//       config.getPlayer(bluePlayer).chooseBlueChampion()
//     val redChamp: Champion =
//       config.getPlayer(redPlayer).chooseRedChampion(blueChamp)
//     val winner: Side = decideWinner(blueChamp, redChamp)
//     updateRecords(blueChamp, redChamp, winner)
//     return MatchHistory(
//       (config.getPlayer(bluePlayer), blueChamp),
//       (config.getPlayer(redPlayer), redChamp),
//       winner
//     )
// }
