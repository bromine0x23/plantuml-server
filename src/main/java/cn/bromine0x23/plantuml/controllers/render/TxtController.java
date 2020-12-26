package cn.bromine0x23.plantuml.controllers.render;

import net.sourceforge.plantuml.FileFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 渲染PlantUML为文本格式
 *
 * @author <a href="mailto:bromine0x23@163.com">Bromine0x23</a>
 */
@RestController
@RequestMapping({"/txt"})
public class TxtController extends AbstractRenderController {

	private static final MediaType CONTENT_TYPE = MediaType.parseMediaType(MediaType.TEXT_PLAIN_VALUE + ";charset=UTF-8");

	@Override
	protected FileFormat getFileFormat() {
		return FileFormat.UTXT;
	}

	@Override
	protected MediaType getContentType() {
		return CONTENT_TYPE;
	}
}
