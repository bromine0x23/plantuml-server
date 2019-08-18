package cn.bromine0x23.plantuml.code

import org.apache.commons.compress.compressors.deflate.DeflateCompressorInputStream
import org.apache.commons.compress.compressors.deflate.DeflateCompressorOutputStream
import org.apache.commons.compress.compressors.deflate.DeflateParameters
import java.io.InputStream
import java.io.OutputStream
import java.util.zip.Deflater

class CompressionCompressorDeflate : CompressionCompressor() {

	override fun createCompressorOutputStream(outputStream: OutputStream) = DeflateCompressorOutputStream(
		outputStream,
		DeflateParameters().apply {
			compressionLevel = Deflater.BEST_COMPRESSION
			setWithZlibHeader(false)
		}
	)

	override fun createCompressorInputStream(inputStream: InputStream) = DeflateCompressorInputStream(inputStream)
}