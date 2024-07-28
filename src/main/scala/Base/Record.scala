package Base

class Record:
  var wins = 0.0f
  var games = 0.0f
  def updateRecord(won: Boolean): Unit =
    games += 1
    if (won) wins += 1
  def getWinPercent: Float =
    if (games == 0)
      return 0.5
    return wins / games

// keeps a general tally of wins and games, as well as a record for each opposing champion
class MatchupAwareRecords(listOfChamps: Array[Champion]) extends Record:
  val records: Map[Champion, Record] = listOfChamps.map((_, new Record)).toMap
  def updateRecord(oppComp: Composition, won: Boolean): Unit =
    oppComp.map(records(_).updateRecord(won))
    updateRecord(won)
  def getWinPercent(oppChamp: Champion) = records(oppChamp).getWinPercent
