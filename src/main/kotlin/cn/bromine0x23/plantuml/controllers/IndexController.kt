package cn.bromine0x23.plantuml.controllers

import cn.bromine0x23.plantuml.services.UmlService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping
class IndexController(
	private val umlService: UmlService
) : ApplicationController() {

	companion object {
		private val DEFAULT_SOURCE = """
			@startuml
			Bob -> Alice : hello
			@enduml
		""".trimIndent()
	}

	@GetMapping
	fun index(
		model: Model
	): String {
		model.addAttribute("source", DEFAULT_SOURCE)
		model.addAttribute("encodedSource", umlService.encode(DEFAULT_SOURCE))
		return "index"
	}
}