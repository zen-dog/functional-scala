package week4

/**
 * Created by Alex on 18.10.2015.
 */
trait List[+T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
  def prepend [U >: T] (elem: U): List[U] = new Cons(elem, this)
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  def isEmpty = false
}

object Nil extends List[Nothing] {
  def isEmpty = true
  def head: Nothing = throw new NoSuchElementException("Nil.head")
  def tail: Nothing = throw new NoSuchElementException("Nil.tail11")
}

object List {
  // List(2, 3) = List.apply(1, 2)
  def apply[T](): List[T] = Nil
  def apply[T](el1: T): List[T] = new Cons(el1, Nil);
  def apply[T](el1: T, el2: T): List[T] = new Cons(el1, new Cons(el2, Nil))
}

object test {
  val x: List[String] = Nil
}