package cn.bromine0x23.plantuml.controllers

import org.slf4j.Logger
import org.slf4j.LoggerFactory

abstract class ApplicationController {

	protected val logger: Logger = LoggerFactory.getLogger(this::class.java)
}