package assessment.core

import akka.actor.Actor
import assessment.core.Calculator.{Expr, Val}
import assessment.core.CalculatorActor.{Evaluate, UnsuportedRequest, Simplify}

class CalculatorActor extends Actor{
  def receive: Receive = {
    case Simplify(expr) => sender ! Right(Calculator.simplify(expr))
    case Evaluate(expr) => sender ! Right(Val(Calculator.evaluate(expr)))
    case _              => sender ! UnsuportedRequest
  }

}

object CalculatorActor {
  trait Req
  case class Simplify(expr: Expr) extends Req
  case class Evaluate(expr: Expr) extends Req

  type Res = Either[Throwable,Expr]

  val UnsuportedRequest: Res = Left(new UnsupportedOperationException("Unknown request type"))
}

