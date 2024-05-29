@main def hello(): Unit =
  val test = Tournament(10, 10, 5)
  val printer = new Printer
  printer.writeToFile(test, "results/cod.txt")
