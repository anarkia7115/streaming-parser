package com.gcbi.credential.obs

import org.pmw.tinylog.Logger

object Logging extends App {
  println("start")  
  Logger.trace(s"trace")
  Logger.debug(s"debug")
  Logger.info(s"info")
  Logger.warn(s"warn")
  Logger.error(s"error")
  println("end")
}