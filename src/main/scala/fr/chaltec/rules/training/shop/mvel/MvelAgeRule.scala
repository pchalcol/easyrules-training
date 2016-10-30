package fr.chaltec.rules.training.shop.mvel

import io.advantageous.boon.{IO, Maps}
import org.easyrules.annotation.{Action, Condition, Priority, Rule}
import org.mvel2.MVEL

/**
  * Created by pchalcol on 30/10/16.
  */
object MvelAgeRule {
  val ADULT_AGE = 18
  val rule = IO.readFromClasspath("classpath:shop/age.mvel")
  val expression = MVEL.compileExpression(rule)
}

@Rule(name = "AgeRule", description = "Check if person's age is > 18 and marks the person as adult")
case class MvelAgeRule(person: Person) {

  @Priority
  def priority = 1

  @Condition
  def evaluate(): Boolean = {
    val data = Maps.map[String, Object](
    "age", Int.box(person.age),
    "adult_age", Int.box(MvelAgeRule.ADULT_AGE))
    MVEL.executeExpression(MvelAgeRule.expression, data).asInstanceOf[Boolean]
  }

  @Action
  def execute() = {
    person.adult = true
    println(s"Person ${person.name} has been marked as adult")
  }
}