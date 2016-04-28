
def mapFun[T, U](xs: List[T], f: T => U): List[U] =
  (xs foldRight List[U]())( (a, b) => f(a) :: b )
mapFun(List(1,2,3,4,5), ((x:Int) => x * x))

def lengthFun[T](xs: List[T]): Int =
  (xs foldRight 0)( (_, len) => len + 1 )
lengthFun(List(1,2,3,4,5))

def penultimate(xs: List[Int]): Int =
  (xs foldLeft (xs.head, xs.tail.head))( (acc, c) => (acc._2, c) )._1
penultimate(List(1,2,3,4,5))

def contains[A](xs: List[A], item: A): Boolean =
  (xs foldLeft false)( (r, c) => c == item || r == true)
contains(List(1,2,3,4,5), 1)

def get(xs: List[Int], idx: Int): Int =
  xs.tail.foldLeft(xs.head, 0)( (r, c) => if (r._2 == idx) r else (c, r._2+1))._1
get(List(1,2,3,4,5), 2)

def mimicToString(xs: List[Int]): String = xs match {
  case Nil => "List()"
  case head :: tail => tail.foldLeft("List("+head)( (r, c) => r + ", " + c) + ")"
}
mimicToString(List(1,2,3,4,5))

def reverse(xs: List[Int]): List[Int] =
  xs.foldLeft(List[Int]())( (r, c) => c :: r)
reverse(List(1,2,3,4,5))

def unique(xs: List[Int]): List[Int] =
  xs.foldLeft(List[Int]())( (r, c) => if(r.contains(c)) r else c :: r).reverse;
unique(List(1,2,2,3,3,4,5))

def double[Int](xs: List[Int]): List[Int] =
  xs.foldLeft(List[Int]())( (r, c) => c :: c :: r).reverse
double(List(1,2,3,4,5))