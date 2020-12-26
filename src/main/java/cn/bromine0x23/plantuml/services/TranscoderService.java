package cn.bromine0x23.plantuml.services;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.plantuml.code.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 传输编解码服务
 *
 * @author <a href="mailto:bromine0x23@163.com">Bromine0x23</a>
 */
@Slf4j
@Service
public class TranscoderService {

	private static final Transcoder transcoder = TranscoderUtil.getDefaultTranscoderProtected();

	@SneakyThrows(IOException.class)
	public String encode(String data) {
		return transcoder.encode(data);
	}

	@SneakyThrows(IOException.class)
	public String decode(String data) {
		return transcoder.decode(data);
	}

}
