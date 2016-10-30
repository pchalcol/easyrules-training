package fr.chaltec.rules.training.shop.simple

import org.easyrules.annotation.{Action, Condition, Priority, Rule}

/**
  * Created by pchalcol on 30/10/16.
  */
object AlcoholRule {
  def apply(person: Person) = new AlcoholRule(person)
}

@Rule(name = "AlcoholRule", description = "Children are not allowed to buy alcohol")
class AlcoholRule(person: Person) {

  @Priority
  def priority = 2

  @Condition
  def evaluate(): Boolean = !person.adult

  @Action
  def execute() = {
    println(
      s"""
        |Shop:
        |Sorry dude ${person.name}, you are not allowed to buy alcohol
      """.stripMargin)
  }
}
