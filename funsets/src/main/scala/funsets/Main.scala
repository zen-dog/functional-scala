package funsets

object Main extends App {
  import FunSets._
  println(contains(singletonSet(1), 1))

  val s = union(union(singletonSet(2), singletonSet(3)), singletonSet(4))

  printSet(map(s, e => e * 2))
}
