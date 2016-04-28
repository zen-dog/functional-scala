import scala.annotation.tailrec

println("Welcome to the Scala worksheet for week1")


def or(x: Boolean, y: => Boolean): Boolean = if (x) true else y

def and(x: Boolean, y: => Boolean): Boolean = if (x) y else false

def abs(x: Double): Double = if (x >= 0) x else -x


def sqrt(x: Double) = {

  def sqrtIter(guess: Double): Double =
    if (isGoodEnough(guess)) guess
    else sqrtIter(improve(guess))

  def isGoodEnough(guess: Double): Boolean =
    abs(guess * guess - x) / x < 0.001

  def improve(guess: Double): Double =
    (guess + x / guess) / 2

  sqrtIter(1.0)
}
and(true, false)
or(false, true)

sqrt(0.00001)
sqrt(4)
sqrt(1e60)

// Tail recursive factorial version
def factorial(n: Int): Int = {
  @tailrec
  def factorialTailRec(x: Int, acc: Int): Int =
    if (x == 0) acc else factorialTailRec(x - 1, acc * x)

  factorialTailRec(n, 1)
}

factorial(5)
