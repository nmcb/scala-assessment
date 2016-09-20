package assessment.core

import scala.util.parsing.combinator.JavaTokenParsers

object Calculator {

  def simplify(expr: Expr) : Expr = {
    def collect(e : Expr) = e match {
      case UnOp("-", UnOp("-", x))        => x
      case UnOp("+", x)                   => x
      case BiOp("*", x, Val(1))           => x
      case BiOp("*", Val(1), x)           => x
      case BiOp("*", x, Val(0))           => Val(0)
      case BiOp("*", Val(0), x)           => Val(0)
      case BiOp("/", x, Val(1))           => x
      case BiOp("/", x1, x2) if x1 == x2  => Val(1)
      case BiOp("+", x, Val(0))           => x
      case BiOp("+", Val(0), x)           => x
      case _                              => e
    }
    val subs = expr match {
      case BiOp(op, lhs, rhs)   => BiOp(op, simplify(lhs), simplify(rhs))
      case UnOp(op, operand)    => UnOp(op, simplify(operand))
      case _                    => expr
    }
    collect(subs)
  }

  def evaluate(e : Expr) : Double = {
    e match {
      case Val(x)           => x
      case UnOp("-", x)     => -evaluate(x)
      case BiOp("+", l, r)  => evaluate(l) + evaluate(r)
      case BiOp("-", l, r)  => evaluate(l) - evaluate(r)
      case BiOp("*", l, r)  => evaluate(l) * evaluate(r)
      case BiOp("/", l, r)  => evaluate(l) / evaluate(r)
    }
  }
}