package cn.bromine0x23.plantuml.code

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream
import java.io.InputStream
import java.io.OutputStream

class CompressionCompressorBzip2 : CompressionCompressor() {

	override fun createCompressorOutputStream(outputStream: OutputStream) = BZip2CompressorOutputStream(
		outputStream,
		BZip2CompressorOutputStream.MAX_BLOCKSIZE
	)

	override fun createCompressorInputStream(inputStream: InputStream) = BZip2CompressorInputStream(inputStream)
}