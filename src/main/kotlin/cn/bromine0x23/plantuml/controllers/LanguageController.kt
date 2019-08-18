package cn.bromine0x23.plantuml.controllers

import net.sourceforge.plantuml.syntax.LanguageDescriptor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.ByteArrayOutputStream
import java.io.PrintStream

@RestController
@RequestMapping("/language")
class LanguageController : ApplicationController() {

	@GetMapping(produces = ["text/plain"])
	fun index() = ByteArrayOutputStream().also {
		LanguageDescriptor().print(PrintStream(it))
	}.toString()
}