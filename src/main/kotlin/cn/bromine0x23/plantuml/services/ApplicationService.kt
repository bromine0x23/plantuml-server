package cn.bromine0x23.plantuml.services

import org.slf4j.Logger
import org.slf4j.LoggerFactory

abstract class ApplicationService {

	protected val logger: Logger = LoggerFactory.getLogger(this::class.java)
}