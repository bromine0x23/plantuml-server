package cn.bromine0x23.plantuml.services

import net.sourceforge.plantuml.FileFormat
import net.sourceforge.plantuml.FileFormatOption
import net.sourceforge.plantuml.SourceStringReader
import net.sourceforge.plantuml.code.Base64Coder
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service
import java.io.ByteArrayOutputStream

@Service
class RenderService: ApplicationService() {

	fun render(source: String, index: Int, format: FileFormat): Resource {
		val reader = SourceStringReader(source)
		return if (format == FileFormat.BASE64) {
			doRenderBase64(reader, index)
		} else {
			doRender(reader, index, format)
		}
	}

	private fun doRenderBase64(reader: SourceStringReader, index: Int): Resource {
		val os = ByteArrayOutputStream()
		reader.outputImage(os, index, FileFormatOption(FileFormat.PNG))
		val encodedBytes = "data:image/png;base64," + Base64Coder.encodeLines(os.toByteArray()).replace("\\s".toRegex(), "")
		return ByteArrayResource(encodedBytes.toByteArray())
	}

	private fun doRender(reader: SourceStringReader, index: Int, format: FileFormat): Resource {
		val os = ByteArrayOutputStream().apply {
			val diagram = reader.blocks[0].diagram
			diagram.exportDiagram(this, index, FileFormatOption(format))
		}
		return ByteArrayResource(os.toByteArray())
	}
}