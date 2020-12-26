package cn.bromine0x23.plantuml.controllers.render;

import net.sourceforge.plantuml.FileFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 渲染PlantUML为Base64格式
 *
 * @author <a href="mailto:bromine0x23@163.com">Bromine0x23</a>
 */
@RestController
@RequestMapping({"/base64"})
public class Base64Controller extends AbstractRenderController {

	@Override
	protected FileFormat getFileFormat() {
		return FileFormat.BASE64;
	}

	@Override
	protected MediaType getContentType() {
		return MediaType.TEXT_PLAIN;
	}
}
