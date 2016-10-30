package fr.chaltec.rules.training.shop.configrules

import io.advantageous.boon.Maps
import org.easyrules.annotation.{Action, Condition, Priority, Rule}
import org.mvel2.MVEL
import Config.configuration

/**
  * Created by pchalcol on 30/10/16.
  */
object MvelAlcoholRule {
  val rule = configuration.getString("shop.rules.alcohol.expression")
  val expression = MVEL.compileExpression(rule)
}

@Rule(name = "AlcoholRule", description = "Children are not allowed to buy alcohol")
case class MvelAlcoholRule(person: Person) {

  @Priority
  def priority = configuration.getInt("shop.rules.alcohol.priority")

  @Condition
  def evaluate(): Boolean = {
    val data = Maps.map[String, Object]("isAdult", Boolean.box(person.adult))
    MVEL.executeExpression(MvelAlcoholRule.expression, data).asInstanceOf[Boolean]
  }

  @Action
  def execute() = {
    println(
      s"""
         |Shop:
         |Sorry dude ${person.name}, you are not allowed to buy alcohol
      """.stripMargin)
  }
}
