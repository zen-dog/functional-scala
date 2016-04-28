package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = singletonSet(1)
    val s5 = singletonSet(4)
    val s12 = union(s1, s2)
    val s13 = union(s1, s3)
    val s23 = union(s2, s3)
    val setPositiveNumbers = union(singletonSet(1), singletonSet(300))
    val setNegativeNumbers = union(singletonSet(-10), singletonSet(-99))
    val setPositiveAndNegativeNumbers = union(setPositiveNumbers, setNegativeNumbers)
    val setEvenNumbers = union(singletonSet(4), singletonSet(6))
    val setOddNumbers = union(singletonSet(3), singletonSet(9))
    val setEvenAndOddNumbers = union(setEvenNumbers, setOddNumbers)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersect contains elements common in both sets") {
    new TestSets {
      assert(contains(intersect(s12, s13), 1), "Intersect 1")
      assert(!contains(intersect(s12, s13), 2), "Intersect 2")
      assert(!contains(intersect(s12, s13), 3), "Intersect 3")
    }
  }

  test("diff contains elements from one set missing in another") {
    new TestSets {
      assert(contains(diff(s12, s13), 2), "Diff 1")
      assert(!contains(diff(s12, s13), 1), "Diff 2")
      assert(!contains(diff(s12, s13), 3), "Diff 3")
    }
  }

  test("filtering set with a predicate") {
    new TestSets {
      val s123 = union(s12, s3)
      assert(contains(filter(s123, p => p == 1), 1), "Filter 1")
      assert(contains(filter(s123, p => p > 0), 1), "Filter 2")
      assert(!contains(filter(s123, p => p < 0), 1), "Filter 3")
      assert(contains(filter(s123, p => p > 2), 3), "Filter 4")
    }
  }

  test("intersect contains elements in both sets") {
    new TestSets {
      val intersection1 = intersect(s1, s2)
      assert(!contains(intersection1, 1), "Intersect 1 between singletonSet(1) and singletonSet(2)")
      val intersection2 = intersect(s1, s4)
      assert(contains(intersection2, 1), "Intersect 1 between singletonSet(1) and singletonSet(4)")
      assert(!contains(intersection2, 2), "Intersect 2 between singletonSet(1) and singletonSet(4)")
    }
  }

  test("diff returns the difference between the two sets") {
    new TestSets {
      val difference1 = diff(s1, s2)
      assert(contains(difference1, 1), "Diff 1 between singletonSet(1) and singletonSet(2)")
      val difference2 = diff(s1, s4)
      assert(!contains(difference2, 1), "Diff 2 between singletonSet(1) and singletonSet(4)")
    }
  }

  test("filter returns the subset of one set for which a parameter function holds") {
    new TestSets {
      val filterSet1 = filter(s1, {elem: Int => elem < 2})
      assert(contains(filterSet1, 1))

      val filterSet2 = filter(s3, {elem: Int => elem > 5})
      assert(!contains(filterSet2, 3))
    }
  }

  test("forall function") {
    new TestSets {
      val s123 = union(s12, s13);
      assert(forall(s123, elem => elem > 0), "forall 1")
      assert(forall(setPositiveNumbers, {elem:Int => elem > 0}), "forall 2")
      assert(forall(setNegativeNumbers, {elem:Int => elem < 0}), "forall 3")
      assert(!forall(setPositiveAndNegativeNumbers, {elem:Int => elem < 0}), "forall 4")
      assert(forall(setEvenNumbers, {elem:Int => (elem % 2) == 0}), "forall 5")
      assert(forall(setOddNumbers, {elem:Int => (elem % 2) != 0}), "forall 6")
      assert(!forall(setEvenAndOddNumbers, {elem:Int => (elem % 2) == 0}), "forall 7")
    }
  }

  test("exists function") {
    new TestSets {
      val s123 = union(s12, s13);
      assert(exists(setPositiveAndNegativeNumbers, {elem:Int => elem > 0}), "exists 1")
      assert(!exists(setPositiveNumbers, {elem:Int => elem < 0}), "exists 1.1")
      assert(exists(setEvenNumbers, {elem:Int => (elem % 2) ==  0}), "exists 2")
      assert(!exists(setEvenNumbers, {elem:Int => (elem % 2) ==  1}), "exists 2.1")
      assert(exists(s123, elem => elem == 1), "exists 3")
      assert(!exists(s123, elem => elem == 4), "exists 4")
    }
  }

  test("map function") {
    new TestSets {
      val mapEvenSetToOdd = map(setEvenNumbers, { elem: Int => elem + 1 })
      printSet(mapEvenSetToOdd)
      assert(contains(mapEvenSetToOdd, 5) && contains(mapEvenSetToOdd, 7), "Map 1")

      val mapOddSetToEven = map(setOddNumbers, { elem: Int => elem * 2 })
      assert(forall(mapOddSetToEven, { elem: Int => (elem % 2) == 0 }), "Map 2")

      val mapTimesTwo = map(s23, elem => elem * 2)
      assert(contains(mapTimesTwo, 4) && contains(mapTimesTwo, 6), "Map 3")
      assert(!contains(mapTimesTwo, 2) && !contains(mapTimesTwo, 3), "Map 4")
    }
  }
}
