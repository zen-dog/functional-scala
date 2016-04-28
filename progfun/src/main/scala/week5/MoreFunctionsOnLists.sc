
def init[T](xs: List[T]): List[T] = xs match {
  case List() => throw new Error("init of empty list")
  case List(x) => Nil
  case y :: ys => y :: init(ys)
}

def removeAt[T](n: Int, xs: List[T]): List[T] = xs match {
  case List() => Nil
  case y :: ys => if (n == 0) ys else y :: removeAt(n - 1, ys)
}

def flatten(xs: List[Any]): List[Any] = xs match {
  case (y :: ys) :: zs => y :: flatten(ys) ++ flatten(zs)
  case y :: ys => y :: flatten (ys)
  case Nil => xs
}


init(List(1, 2, 3))
init(List(1))

removeAt(1, List(1, 2, 3, 4))

flatten(List(List(1, 1), 2, List(3, List(5, 8))))