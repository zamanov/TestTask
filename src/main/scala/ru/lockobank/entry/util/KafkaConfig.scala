package ru.lockobank.entry.util

import com.typesafe.config.{Config, ConfigFactory}

object KafkaConfig {
  private val configPath =  "kafka"
  private val root_config = ConfigFactory.load("resources/application.conf")

  protected val config: Config = root_config.getConfig(configPath)

  def get(string: String): String = config.getString(string)
}
