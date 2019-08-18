package cn.bromine0x23.plantuml.services

import net.sourceforge.plantuml.code.Transcoder
import net.sourceforge.plantuml.code.TranscoderUtil
import org.springframework.stereotype.Service
import java.io.IOException

@Service
class UmlService: ApplicationService() {

	private val transcoder: Transcoder = TranscoderUtil.getDefaultTranscoder()

	fun encode(source: String): String = transcoder.encode(source)

	fun decode(encodedSource: String): String {
		val text = try {
			transcoder.decode(encodedSource)
		} catch (exception: IOException) {
			logger.debug("Exception when decoding: ", exception)
			"' unable to decode string"
		}
		return if (text.startsWith("@start")) {
			text
		} else {
			"""
			@startuml
			$text
			@enduml
			""".trimIndent()
		}
	}
}