package cn.bromine0x23.plantuml.controllers.render;

import net.sourceforge.plantuml.FileFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 渲染PlantUML为EPS格式
 *
 * @author <a href="mailto:bromine0x23@163.com">Bromine0x23</a>
 */
@RestController
@RequestMapping({"/eps"})
public class EpsController extends AbstractRenderController {

	private static final MediaType CONTENT_TYPE = MediaType.parseMediaType("application/postscript");

	@Override
	protected FileFormat getFileFormat() {
		return FileFormat.EPS;
	}

	@Override
	protected MediaType getContentType() {
		return CONTENT_TYPE;
	}
}
