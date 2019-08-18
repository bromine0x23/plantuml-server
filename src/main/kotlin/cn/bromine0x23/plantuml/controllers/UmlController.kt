package cn.bromine0x23.plantuml.controllers

import cn.bromine0x23.plantuml.services.UmlService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.net.URL

@Controller
@RequestMapping("uml")
class UmlController(
	private val umlService: UmlService
) : ApplicationController() {

	@GetMapping("{encodedSource:.*}")
	fun show(
		@PathVariable("encodedSource") encodedSource: String,
		model: Model
	): String {
		val source = umlService.decode(encodedSource)
		model.addAttribute("encodedSource", encodedSource)
		model.addAttribute("source", source)
		return "index"
	}

	@PostMapping
	fun create(
		@RequestParam("source") source: String
	): String {
		return "redirect:uml/" + umlService.encode(source)
	}

	@PostMapping("recover")
	fun recover(
		@RequestParam("url") url: String
	): String {
		return "redirect:${extractEncodedSource(url)}"
	}

	private fun extractEncodedSource(url: String): String {
		val path = URL(url).path
		val segments = path.split('/')
		if (segments.size > 2) {
			return segments.last()
		}
		return "SyfFKj2rKt3CoKnELR1Io4ZDoSa70000"
	}

}