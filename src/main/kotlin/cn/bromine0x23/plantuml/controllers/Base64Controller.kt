package cn.bromine0x23.plantuml.controllers

import cn.bromine0x23.plantuml.services.RenderService
import cn.bromine0x23.plantuml.services.UmlService
import net.sourceforge.plantuml.FileFormat
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/base64")
class Base64Controller(
	umlService: UmlService,
	renderService: RenderService
): AbstractRenderController(umlService, renderService) {

	override val format: FileFormat
		get() = FileFormat.BASE64

	override val contentType: MediaType
		get() = MediaType.TEXT_PLAIN
}