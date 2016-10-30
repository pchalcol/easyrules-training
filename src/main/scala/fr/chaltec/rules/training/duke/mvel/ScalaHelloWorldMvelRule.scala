package fr.chaltec.rules.training.duke.mvel

import io.advantageous.boon.{IO, Maps}
import org.easyrules.annotation.{Action, Condition, Rule}
import org.mvel2.MVEL

/**
  * Created by pchalcol on 29/10/16.
  */
object ScalaHelloWorldMvelRule {
  val rule = IO.readFromClasspath("classpath:duke/duke.mvel")
  val expression = MVEL.compileExpression(rule)
}

@Rule(name = "Hello World rule", description = "Say Hello to only duke's friends")
case class ScalaHelloWorldMvelRule(input: String) {

  private[this] val data = Maps.map[String, Object]("input", input)

  @Condition
  def checkInput() = MVEL.executeExpression(ScalaHelloWorldMvelRule.expression, data).asInstanceOf[Boolean]

  @Action
  def sayHelloToDukeFriend() = println("Hello duke's friend!")
}
