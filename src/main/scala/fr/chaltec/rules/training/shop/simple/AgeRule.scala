package fr.chaltec.rules.training.shop.simple

import org.easyrules.annotation.{Action, Condition, Priority, Rule}
import org.easyrules.api.JmxRule
import org.easyrules.core.BasicRule

/**
  * Created by pchalcol on 29/10/16.
  */
object AgeRule {
  val ADULT_AGE = 18
}

/**
  * Annotated case class.
  * @param person
  */
@Rule(name = "AgeRule", description = "Check if person's age is > 18 and marks the person as adult")
case class AgeRule(person: Person) {

  @Priority
  def priority = 1

  @Condition
  def evaluate(): Boolean = person.age > AgeRule.ADULT_AGE

  @Action
  def execute() = {
    person.adult = true
    println(s"Person ${person.name} has been marked as adult")
  }
}

// =====================================================================================================================

/**
  * Created by pchalcol on 29/10/16.
  */
object BasicAgeRule {
  def apply(person: Person) = new BasicAgeRule(person,
    name = "BasicAgeRule",
    description = "Check if person's age is > 18 and marks the person as adult",
    priority = 1)
}

/**
  * BasicRule inherited class.
  *
  * @param person
  * @param name
  * @param description
  * @param priority
  */
class BasicAgeRule(person: Person,
                   name: String,
                   description: String,
                   priority: Int) extends BasicRule(name, description, priority) {

  override def evaluate() = person.age > AgeRule.ADULT_AGE

  override def execute(): Unit = {
    person.adult= true
    println(s"Person ${person.name} has been marked as adult")
  }
}

// =====================================================================================================================

/**
  * MBean declaration.
  */
@javax.management.MXBean
trait JmxAge extends JmxRule {
  def getAdultAge: Int
  def setAdultAge(adultAge: Int): Unit
}

/**
  * JmxAgeRule Companion object.
  */
object JmxAgeRule {
  def apply(person: Person) = new JmxAgeRule(person,
    name = "JmxAgeRule",
    description = "Check if person's age is > 18 and marks the person as adult",
    priority = 1)
}

/**
  *
  * @param person
  * @param adultAge
  * @param name
  * @param description
  * @param priority
  */
class JmxAgeRule(person: Person,
                 var adultAge: Int = 18,
                 name: String,
                 description: String,
                 priority: Int)
  extends BasicRule(name, description, priority)
  with JmxAge {

  override def getAdultAge: Int = adultAge

  override def setAdultAge(adultAge: Int): Unit = this.adultAge = adultAge

  override def evaluate() = person.age > adultAge

  override def execute(): Unit = {
    person.adult= true
    println(s"Person ${person.name} has been marked as adult")
  }
}
