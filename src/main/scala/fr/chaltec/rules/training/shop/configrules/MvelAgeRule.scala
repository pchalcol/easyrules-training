package fr.chaltec.rules.training.shop.configrules

import io.advantageous.boon.Maps
import org.easyrules.annotation.{Action, Condition, Priority, Rule}
import org.mvel2.MVEL
import Config.configuration

/**
  * Created by pchalcol on 30/10/16.
  */
object MvelAgeRule {
  val ADULT_AGE = configuration.getInt("shop.rules.age.constants.majority")
  val rule = configuration.getString("shop.rules.age.expression")
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
    "majority", Int.box(MvelAgeRule.ADULT_AGE))

    MVEL.executeExpression(MvelAgeRule.expression, data).asInstanceOf[Boolean]
  }

  @Action
  def execute() = {
    person.adult = true
    println(s"Person ${person.name} has been marked as adult")
  }
}