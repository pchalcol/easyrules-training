package fr.chaltec.rules.training.shop.configrules

import com.typesafe.config.ConfigFactory

/**
  * Created by pchalcol on 30/10/16.
  */
object Config {
  implicit val configuration = ConfigFactory.load()
}
