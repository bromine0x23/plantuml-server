package cn.bromine0x23.plantuml.controllers.render;

import net.sourceforge.plantuml.FileFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 渲染PlantUML为PNG格式
 *
 * @author <a href="mailto:bromine0x23@163.com">Bromine0x23</a>
 */
@RestController
@RequestMapping({"/png", "/img"})
public class PngController extends AbstractRenderController {

	@Override
	protected FileFormat getFileFormat() {
		return FileFormat.PNG;
	}

	@Override
	protected MediaType getContentType() {
		return MediaType.IMAGE_PNG;
	}
}
