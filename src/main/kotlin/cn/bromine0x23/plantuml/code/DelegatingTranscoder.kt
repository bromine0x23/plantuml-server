package cn.bromine0x23.plantuml.code

import net.sourceforge.plantuml.code.Transcoder
import net.sourceforge.plantuml.code.TranscoderUtil
import org.slf4j.LoggerFactory

class DelegatingTranscoder(
	private val idForEncode: String,
	private val idToTranscoder: Map<String, Transcoder>
): Transcoder {

	companion object {
		private const val PREFIX = "["
		private const val SUFFIX = "]"
	}

	private val transcoderForEncode: Transcoder = idToTranscoder[idForEncode]
		?:throw IllegalArgumentException("idForDefault `$idForEncode` is not found in idToTranscoder `$idToTranscoder`")

	private val transcoderForDecode = TranscoderUtil.getDefaultTranscoder()

	init {
		for (id in idToTranscoder.keys) {
			if (id.contains(PREFIX)) {
				throw IllegalArgumentException("id `$id` cannot contain {")
			}
			if (id.contains(SUFFIX)) {
				throw IllegalArgumentException("id `$id` cannot contain }")
			}
		}
	}

	override fun encode(text: String): String = "$PREFIX$idForEncode$SUFFIX${transcoderForEncode.encode(text)}"

	override fun decode(prefixEncodedCode: String): String {
		val id = extractId(prefixEncodedCode)
		val delegate = idToTranscoder[id]
		return if (delegate != null) {
			val encodedCode = extractEncodedCode(prefixEncodedCode)
			delegate.decode(encodedCode)
		} else {
			transcoderForDecode.decode(prefixEncodedCode)
		}
	}

	private fun extractId(prefixEncodedCode: String): String? {
		val start = prefixEncodedCode.indexOf(PREFIX)
		if (start != 0) {
			return null
		}
		val end = prefixEncodedCode.indexOf(SUFFIX, start)
		return if (end < 0) {
			null
		} else {
			prefixEncodedCode.substring(start + 1, end)
		}
	}

	private fun extractEncodedCode(prefixEncodedCode: String): String {
		val start = prefixEncodedCode.indexOf(SUFFIX)
		return prefixEncodedCode.substring(start + 1)
	}


}