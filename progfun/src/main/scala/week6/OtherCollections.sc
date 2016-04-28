val xs = Array(1,2,3,44)
xs map (x => x * 2)

val s = "Hello, World"
s.foldLeft("")( (r, c) => r + "," + c)

s.exists(c => c.isUpper)
s.forall(c => c.isUpper)

s.zip(xs).unzip

s.flatMap( c => "." + c)

xs.min
xs.sum
xs.max

val M = 10
val N = 5

(1 to M).flatMap(x => (1 to N).map(y => (x, y)))

def scalarProduct(xs: Vector[Int], ys: Vector[Int]) =
  (xs zip ys).map{ case (x, y) => x * y}.sum

scalarProduct((1 to 3).toVector, (4 to 6).toVector)

def isPrime(n: Int): Boolean = (2 to n-1).forall(x => n%x > 0)

(1 to 20).map(x => println(x + (if (isPrime(x)) " is " else " is NOT ") + "prime number" ))