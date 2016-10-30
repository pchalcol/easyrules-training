package fr.chaltec.rules.training.shop.simple

import java.util.Scanner

import org.easyrules.core.JmxRulesEngineBuilder._

/**
  * Created by pchalcol on 30/10/16.
  */
object JmxMain extends App {

  //create a person instance
  val tom = Person("Tom", 14)
  println(
    """
      |Tom:
      |Hi! can I have some Vodka please?
    """.stripMargin)

  //create a Jmx rules engine
  val rulesEngine = aNewJmxRulesEngine.named("shop rules engine").build // replace with DefaultJmxRulesEngine

  //register rules
  rulesEngine.registerJmxRule(JmxAgeRule(tom))
  rulesEngine.registerRule(AlcoholRule(tom))

  //fire rules
  rulesEngine.fireRules()

  // Update adult age via a JMX client
  val scanner = new Scanner(System.in)

  println("Change adult age via a JMX client and then press enter")
  scanner.nextLine

  println("Re fire rules after updating adult age...")
  rulesEngine.fireRules()

}
