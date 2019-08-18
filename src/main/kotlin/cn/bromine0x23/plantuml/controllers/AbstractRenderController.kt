package cn.bromine0x23.plantuml.controllers

import cn.bromine0x23.plantuml.services.RenderService
import cn.bromine0x23.plantuml.services.UmlService
import net.sourceforge.plantuml.FileFormat
import org.springframework.core.io.Resource
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
abstract class AbstractRenderController(
	private val umlService: UmlService,
	private val renderService: RenderService
): ApplicationController() {

	@GetMapping("{encodedSource:.*}")
	fun show(
		@PathVariable("encodedSource") encodedSource: String
	): ResponseEntity<Resource> = doShow(0, encodedSource)

	@GetMapping("{index:\\d+}/{encodedSource:.*}")
	fun showWithIndex(
		@PathVariable("index") index: Int,
		@PathVariable("encodedSource") encodedSource: String
	): ResponseEntity<Resource> = doShow(index, encodedSource)

	protected abstract val format: FileFormat

	protected abstract val contentType: MediaType

	private fun doShow(index: Int, encodedSource: String): ResponseEntity<Resource> {
		val source = umlService.decode(encodedSource)
		val resource = renderService.render(source, index, format)
		return ResponseEntity
			.status(HttpStatus.OK)
			.contentType(contentType)
			.body(resource)
	}
}