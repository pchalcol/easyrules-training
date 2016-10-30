package fr.chaltec.rules.training.duke.mvel

import java.util.Scanner

import org.easyrules.api.RulesEngine
import org.easyrules.core.RulesEngineBuilder._

/**
  * Created by pchalcol on 29/10/16.
  */
object MvelDukeMain extends App {
  val scanner = new Scanner(System.in)

  println("Are you a friend of duke? [yes/no]:")
  val input = scanner.nextLine

  /**
    * Declare the rule
    */
  val helloWorldRule = ScalaHelloWorldMvelRule(input.trim)

  /**
    * Create a rules engine and register the business rule
    */
  val rulesEngine: RulesEngine = aNewRulesEngine.named("Hello world rules engine").build
  rulesEngine.registerRule(helloWorldRule)

  /**
    * Fire rules
    */
  rulesEngine.fireRules()
}
