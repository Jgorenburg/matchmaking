package Base

final case class MatchHistory(
    blueSide: (Team, Composition),
    redSide: (Team, Composition),
    winner: Side.Value
)
