package cn.bromine0x23.plantuml.code

import org.apache.commons.compress.compressors.lzma.LZMACompressorInputStream
import org.apache.commons.compress.compressors.lzma.LZMACompressorOutputStream
import java.io.InputStream
import java.io.OutputStream

class CompressionCompressorLzma : CompressionCompressor() {

	override fun createCompressorOutputStream(outputStream: OutputStream) = LZMACompressorOutputStream(outputStream)

	override fun createCompressorInputStream(inputStream: InputStream) = LZMACompressorInputStream(inputStream)
}