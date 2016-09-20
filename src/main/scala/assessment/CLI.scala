package assessment

import akka.actor.{Actor, Props}
import akka.pattern.ask
import akka.util.Timeout
import assessment.core.CalculatorActor
import assessment.core.core.{BootedCore, CoreActors}

import scala.concurrent.duration.DurationDouble

object CLI extends CoreActors with BootedCore {

  class Repl extends Actor {
    import system.dispatcher
    implicit val timeout = Timeout(5 seconds)

    def receive: Receive = {
      case cmd: CalculatorActor.Req  => (calculator ? cmd).mapTo[CalculatorActor.Res].foreach(println)
      case _         =>
    }
  }

  val repl = system.actorOf(Props[Repl], "repl")
}
}