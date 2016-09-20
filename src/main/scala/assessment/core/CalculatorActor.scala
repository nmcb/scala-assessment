package assessment.core

import akka.actor.Actor
import assessment.core.CalculatorActor.{Evaluate, Simplify}

class CalculatorActor extends Actor{
  def receive: Receive = {
    case Simplify(expr) => sender ! Calculator.simplify(expr)
    case Evaluate(expr) => sender ! Calculator.evaluate(expr)
  }
}

object CalculatorActor {
  sealed trait Request
  case class Simplify(expr: Expr) extends Request
  case class Evaluate(expr: Expr) extends Request
}

