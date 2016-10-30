package fr.chaltec.rules.training.duke

import org.easyrules.annotation.{Action, Condition, Rule}

/**
  * Created by pchalcol on 29/10/16.
  */
@Rule(name = "Hello World rule", description = "Say Hello to only duke's friends")
case class ScalaHelloWorldRule(input: String) {

  @Condition
  def checkInput() = input.equalsIgnoreCase("yes")

  @Action
  def sayHelloToDukeFriend() = println("Hello duke's friend!")
}
