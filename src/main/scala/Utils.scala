object Utils {
  case class Doublet[A, B](var _1: A, var _2: B) {}
  implicit def doublet_to_tuple[A, B](db: Doublet[A, B]): (A, B) =
    (db._1, db._2)
}
