package assessment.core

package core

import akka.actor.{ActorSystem, Props}

trait Core {
  implicit def system: ActorSystem
}

trait BootedCore extends Core {
  implicit lazy val system = ActorSystem("assessment")
  sys.addShutdownHook(system.terminate())
}

trait CoreActors {
  this: Core =>

  val calculator = system.actorOf(Props[CalculatorActor], "calculator")
}