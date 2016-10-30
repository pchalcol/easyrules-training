package fr.chaltec.rules.training.shop.mvel

import org.easyrules.core.RulesEngineBuilder._

/**
  * Created by pchalcol on 30/10/16.
  */
object MvelShopMain extends App {

  //create a person instance
  val tom = Person("Tom", 14)
  println(
    """
      |Tom:
      |Hi! can I have some Vodka please?
    """.stripMargin)

  //create a rules engine
  val rulesEngine = aNewRulesEngine.named("shop rules engine").build

  //register rules
  rulesEngine.registerRule(MvelAgeRule(tom))
  rulesEngine.registerRule(MvelAlcoholRule(tom))

  //fire rules
  rulesEngine.fireRules()

}

/**
  *
  * @param name
  * @param age
  * @param adult
  */
case class Person(name: String,
                  age: Int,
                  var adult: Boolean = false)
