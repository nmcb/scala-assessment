package assessment.core

abstract class Expr
case class Val(value : Double)                             extends Expr
case class UnOp(operator : String, operand : Expr)         extends Expr
case class BiOp(operator : String, lhs : Expr, rhs : Expr) extends Expr
