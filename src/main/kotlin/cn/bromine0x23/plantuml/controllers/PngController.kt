package cn.bromine0x23.plantuml.controllers

import cn.bromine0x23.plantuml.services.RenderService
import cn.bromine0x23.plantuml.services.UmlService
import net.sourceforge.plantuml.FileFormat
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/png", "/img")
class PngController(
	umlService: UmlService,
	renderService: RenderService
): AbstractRenderController(umlService, renderService) {

	override val format: FileFormat
		get() = FileFormat.PNG

	override val contentType: MediaType
		get() = MediaType.IMAGE_PNG
}