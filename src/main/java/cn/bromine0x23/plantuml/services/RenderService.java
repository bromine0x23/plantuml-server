package cn.bromine0x23.plantuml.services;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.OptionFlags;
import net.sourceforge.plantuml.SourceStringReader;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * PlantUML 渲染服务
 *
 * @author <a href="mailto:bromine0x23@163.com">Bromine0x23</a>
 */
@Slf4j
@Service
public class RenderService  {

	static {
		OptionFlags.setAllowIncludeFalse();
	}

	public Resource render(String source, int index, FileFormat format) throws IOException {
		var reader = new SourceStringReader(source);
		if (format == FileFormat.BASE64) {
			return doRenderBase64(reader, index);
		} else {
			return doRender(reader, index, format);
		}
	}

	private Resource doRenderBase64(SourceStringReader reader, int index) throws IOException {
		var os = new ByteArrayOutputStream();
		os.writeBytes("data:image/png;base64,".getBytes(StandardCharsets.UTF_8));
		reader.outputImage(Base64.getUrlEncoder().wrap(os), index, new FileFormatOption(FileFormat.PNG));
		return new ByteArrayResource(os.toByteArray());
	}

	private Resource doRender(SourceStringReader reader, int index, FileFormat format) throws IOException {
		var os = new ByteArrayOutputStream();
		var diagram = reader.getBlocks().get(0).getDiagram();
		diagram.exportDiagram(os, index, new FileFormatOption(format));
		return new ByteArrayResource(os.toByteArray());
	}

}
