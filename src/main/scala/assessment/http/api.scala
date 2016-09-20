package assessment.http

sealed trait API
case class Calc(argument: String) extends API
case class Resp(result: String) extends API
