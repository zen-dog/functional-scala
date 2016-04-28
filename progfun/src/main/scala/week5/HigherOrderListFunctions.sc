
def squareList(xs: List[Int]): List[Int] = xs match {
  case Nil => xs
  case y :: ys => y * y :: squareList(ys)
}

def squareList2(xs: List[Int]): List[Int] = xs.map(x => x * x)

squareList(List(1, 2, 4))
squareList2(List(1, 2, 4))

def pack[T](xs: List[T]): List[List[T]] = xs match {
  case Nil      => Nil
  case x :: xs1 =>
    val (first, rest) = xs span (e => e == x)
    first :: pack(rest)
}

val data = List("a", "a", "a", "b", "c", "c", "d")
pack(data)

def encode[T](xs: List[T]): List[(T, Int)] = pack(xs) map (ys => (ys.head, ys.length))

encode(data)