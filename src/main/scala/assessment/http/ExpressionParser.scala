package assessment.http

import assessment.core.{BiOp, Expr, Val}

import scala.util.parsing.combinator.JavaTokenParsers

object ExpressionParser extends JavaTokenParsers
{
  def expr : Parser[Expr] =
    (term ~ "+" ~ term) ^^ { case lhs~plus~rhs  => BiOp("+", lhs, rhs) } |
    (term ~ "-" ~ term) ^^ { case lhs~minus~rhs => BiOp("-", lhs, rhs) } |
    term

  def term : Parser[Expr] =
    (factor ~ "*" ~ factor) ^^ { case lhs~times~rhs => BiOp("*", lhs, rhs) } |
    (factor ~ "/" ~ factor) ^^ { case lhs~div~rhs   => BiOp("/", lhs, rhs) } |
    factor

  def factor : Parser[Expr] =
    "(" ~> expr <~ ")" |
    floatingPointNumber ^^ { x => Val(x.toDouble) }

  def parse(s : String) = parseAll(expr, s).getOrElse(Val(Double.NaN))
}


