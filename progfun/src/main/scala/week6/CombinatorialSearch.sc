
val n = 7

def isPrime(n: Int): Boolean = (2 to n-1).forall(x => n%x > 0)

(1 until n) flatMap (i =>
  (1 until i) map (j => (i, j))) filter {
    case (i, j) => isPrime(i + j) } map {
      case (i, j) => println (i + ", " + j)}

for {
  i <- 1 until n
  j <- 1 until n
  if isPrime(i + j)
} yield (i, j)

def scalarProduct(xs: Vector[Int], ys: Vector[Int]) =
  (for ((x, y) <- xs zip ys) yield x * y).sum

scalarProduct((1 to 3).toVector, (4 to 6).toVector)