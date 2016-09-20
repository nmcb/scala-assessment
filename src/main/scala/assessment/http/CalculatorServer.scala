package assessment.http

import akka.pattern.ask
import akka.util.Timeout
import assessment.core.{BootedCore, CoreActors, Expr}
import com.twitter.finatra.http.routing.HttpRouter
import com.twitter.finatra.http.{Controller, HttpServer}

import scala.concurrent.duration.DurationLong

object CalculatorServer extends HttpServer {
  override protected def configureHttp(router: HttpRouter): Unit = router.add[CalculatorController]
}

class CalculatorController extends Controller with CoreActors with BootedCore {
  import Conversions._
  import assessment.core.CalculatorActor._
  import system.dispatcher

  implicit val timeout = Timeout(5 seconds)

  post("/calculator/evaluate") { calc: Calc => {
    val expr = ExpressionParser.parse(calc.argument)
    val call =  (calculator ? Evaluate(expr)).mapTo[Double]
    scalaToTwitterFuture(call)
  }}
  post("/calculator/simplify") { calc: Calc => {
    val expr = ExpressionParser.parse(calc.argument)
    val call =  (calculator ? Simplify(expr)).mapTo[Expr]
    scalaToTwitterFuture(call)
  }}

}