package cn.bromine0x23.plantuml.code

import net.sourceforge.plantuml.code.Compression
import org.apache.commons.compress.compressors.CompressorInputStream
import org.apache.commons.compress.compressors.CompressorOutputStream
import org.springframework.util.StreamUtils
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.OutputStream

abstract class CompressionCompressor : Compression {

	override fun compress(bytes: ByteArray): ByteArray {
		val outputStream =  ByteArrayOutputStream()
		val compressorOutputStream = createCompressorOutputStream(outputStream)
		StreamUtils.copy(bytes, compressorOutputStream)
		compressorOutputStream.close()
		return outputStream.toByteArray()
	}

	override fun decompress(bytes: ByteArray): ByteArray {
		val inputStream = ByteArrayInputStream(bytes)
		val compressorInputStream = createCompressorInputStream(inputStream)
		return StreamUtils.copyToByteArray(compressorInputStream)
	}

	protected abstract fun createCompressorOutputStream(outputStream: OutputStream): CompressorOutputStream

	protected abstract fun createCompressorInputStream(inputStream: InputStream): CompressorInputStream
}