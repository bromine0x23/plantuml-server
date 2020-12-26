package cn.bromine0x23.plantuml.controllers.render;

import cn.bromine0x23.plantuml.services.RenderService;
import cn.bromine0x23.plantuml.services.TranscoderService;
import net.sourceforge.plantuml.FileFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * PlantUML渲染服务
 *
 * @author <a href="mailto:bromine0x23@163.com">Bromine0x23</a>
 */
@RestController
public abstract class AbstractRenderController {

	private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

	@Autowired
	private TranscoderService transcoderService;

	@Autowired
	private RenderService renderService;

	@GetMapping("{encodedSource:.*}")
	public ResponseEntity<Resource> show(
		@PathVariable("encodedSource") String encodedSource
	) throws IOException {
		return doShow(0, encodedSource);
	}

	@GetMapping("{index:\\d+}/{encodedSource:.*}")
	public ResponseEntity<Resource> showWithIndex(
		@PathVariable("index") Integer index,
		@PathVariable("encodedSource") String encodedSource
	) throws IOException {
		return doShow(index, encodedSource);
	}

	protected abstract FileFormat getFileFormat();

	protected abstract MediaType getContentType();

	private ResponseEntity<Resource> doShow(Integer index, String encodedSource) throws IOException {
		var source             = transcoderService.decode(encodedSource);
		var resource           = renderService.render(source, index, getFileFormat());
		var contentDisposition = ContentDisposition.inline()
			.filename("plantuml-" + TIMESTAMP_FORMATTER.format(LocalDateTime.now()) + getFileFormat().getFileSuffix())
			.build();
		return ResponseEntity
			.status(HttpStatus.OK)
			.contentType(getContentType())
			.header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString())
//			.cacheControl(CacheControl.maxAge(Duration.ofHours(1)))
			.body(resource);
	}

}
