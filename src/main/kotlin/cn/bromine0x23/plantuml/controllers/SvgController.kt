package cn.bromine0x23.plantuml.controllers

import cn.bromine0x23.plantuml.services.RenderService
import cn.bromine0x23.plantuml.services.UmlService
import net.sourceforge.plantuml.FileFormat
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/svg")
class SvgController(
	umlService: UmlService,
	renderService: RenderService
): AbstractRenderController(umlService, renderService) {

	companion object {
		@JvmStatic
		private val CONTENT_TYPE = MediaType.parseMediaType("image/svg+xml")
	}

	override val format: FileFormat
		get() = FileFormat.SVG

	override val contentType: MediaType
		get() = CONTENT_TYPE
}