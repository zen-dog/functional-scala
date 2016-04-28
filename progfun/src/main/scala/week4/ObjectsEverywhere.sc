// Peano numbers

abstract class Nat {
  def isZero: Boolean
  def predecessor: Nat
  def successor = new Succ(this)
  def + (that: Nat): Nat
  def - (that: Nat): Nat
}

object Zero extends Nat {
  def isZero: Boolean = true
  def predecessor = throw new Error("Zero.predecessor")
  def + (that: Nat):Nat = that
  def - (that: Nat) = if (that isZero) this else throw new Error("Zero.minus some value")
}

class Succ(n: Nat) extends Nat {
  def isZero: Boolean = false
  def predecessor = n
  def + (that: Nat) = n + new Succ(that)
  def - (that: Nat) = if (that isZero) this else n - that.predecessor
}

new Succ(new Succ(Zero))

