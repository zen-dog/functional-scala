
trait Expr
case class Number(n: Int) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr
case class Prod(e1: Expr, e2: Expr) extends Expr
case class Var(v: String) extends Expr

def show(e: Expr): String = e match {
  case Number(n)    => n.toString
  case Var(v)       => v
  case Prod(e1, e2) => showInParentheses(e1) + " * " + showInParentheses(e2)
  case Sum(e1, e2)  => show(e1) + " + " + show(e2)
}

def showInParentheses(e: Expr): String = e match {
  case Sum(_,_) => "(" + show(e) + ")"
  case _ => show(e)
}

// Tests
assert(show(Sum(Number(1), Number(2))) == "1 + 2")
assert(show(Prod(Number(3), Number(4))) == "3 * 4")
show(Sum(Prod(Number(2), Var("x")), Var("y")))  // 2 * x + y
show(Prod(Sum(Number(2), Var("x")), Var("y")))  // (2 + x) * y
show(Prod(Var("y"), Sum(Number(2), Var("x"))))  // y * (2 + x)