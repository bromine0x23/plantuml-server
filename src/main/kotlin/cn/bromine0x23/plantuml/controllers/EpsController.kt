package cn.bromine0x23.plantuml.controllers

import cn.bromine0x23.plantuml.services.RenderService
import cn.bromine0x23.plantuml.services.UmlService
import net.sourceforge.plantuml.FileFormat
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/eps")
class EpsController(
	umlService: UmlService,
	renderService: RenderService
): AbstractRenderController(umlService, renderService) {

	companion object {
		@JvmStatic
		private val CONTENT_TYPE = MediaType.parseMediaType("application/postscript")
	}

	override val format: FileFormat
		get() = FileFormat.EPS

	override val contentType: MediaType
		get() = CONTENT_TYPE
}