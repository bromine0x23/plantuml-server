package cn.bromine0x23.plantuml.code

import org.apache.commons.compress.compressors.xz.XZCompressorInputStream
import org.apache.commons.compress.compressors.xz.XZCompressorOutputStream
import org.tukaani.xz.LZMA2Options
import java.io.InputStream
import java.io.OutputStream

class CompressionCompressorXz : CompressionCompressor() {

	override fun createCompressorOutputStream(outputStream: OutputStream) = XZCompressorOutputStream(
		outputStream,
		LZMA2Options.PRESET_MAX
	)

	override fun createCompressorInputStream(inputStream: InputStream) = XZCompressorInputStream(inputStream)
}