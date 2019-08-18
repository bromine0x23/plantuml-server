package cn.bromine0x23.plantuml.code

import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream
import org.apache.commons.compress.compressors.gzip.GzipParameters
import java.io.InputStream
import java.io.OutputStream
import java.util.zip.Deflater

class CompressionCompressorGzip : CompressionCompressor() {

	override fun createCompressorOutputStream(outputStream: OutputStream) = GzipCompressorOutputStream(
		outputStream,
		GzipParameters().apply {
			compressionLevel = Deflater.BEST_COMPRESSION
		}
	)

	override fun createCompressorInputStream(inputStream: InputStream) = GzipCompressorInputStream(inputStream)
}