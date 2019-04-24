package com.gcbi.misc

import org.scalatest.FlatSpec
import org.slf4j.LoggerFactory
import org.pmw.tinylog.Logger

class LoggingSpec extends FlatSpec {
  
  "Logs" should "be print" in {
    Logger.trace(s"trace")
    Logger.debug(s"debug")
    Logger.info(s"info")
    Logger.warn(s"warn")
    Logger.error(s"error")
  }

}