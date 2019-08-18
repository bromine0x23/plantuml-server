package cn.bromine0x23.plantuml.code

import net.sourceforge.plantuml.code.ArobaseStringCompressor
import net.sourceforge.plantuml.code.CompressionNone
import net.sourceforge.plantuml.code.Transcoder
import net.sourceforge.plantuml.code.TranscoderImpl
import net.sourceforge.plantuml.code.TranscoderUtil
import net.sourceforge.plantuml.code.URLEncoder
import java.util.*

object TranscoderFactories {

	private object BaseEncoder: URLEncoder {

		override fun encode(src: ByteArray): String {
			return Base64.getUrlEncoder().encodeToString(src)
		}

		override fun decode(src: String): ByteArray {
			return Base64.getUrlDecoder().decode(src)
		}

	}

	fun createDelegatingTranscoder(idForEncode: String = "lzma"): Transcoder {
		val idToTranscoder = mutableMapOf(
			"default" to TranscoderUtil.getDefaultTranscoder(),
			"none" to TranscoderImpl(BaseEncoder, ArobaseStringCompressor(), CompressionNone()),
			"bzip2" to TranscoderImpl(BaseEncoder, ArobaseStringCompressor(), CompressionCompressorBzip2()),
			"deflate" to TranscoderImpl(BaseEncoder, ArobaseStringCompressor(), CompressionCompressorDeflate()),
			"gzip" to TranscoderImpl(BaseEncoder, ArobaseStringCompressor(), CompressionCompressorGzip()),
			"lzma" to TranscoderImpl(BaseEncoder, ArobaseStringCompressor(), CompressionCompressorLzma()),
			"xz" to TranscoderImpl(BaseEncoder, ArobaseStringCompressor(), CompressionCompressorXz())
		)
		return DelegatingTranscoder(idForEncode, idToTranscoder)
	}
}