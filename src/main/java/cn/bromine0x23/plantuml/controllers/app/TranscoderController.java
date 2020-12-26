package cn.bromine0x23.plantuml.controllers.app;

import cn.bromine0x23.plantuml.services.TranscoderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:bromine0x23@163.com">Bromine0x23</a>
 */
@RestController
@RequestMapping("/app/transcoder")
public class TranscoderController {

	private final TranscoderService transcoderService;

	public TranscoderController(TranscoderService transcoderService) {
		this.transcoderService = transcoderService;
	}

	@PostMapping("encode")
	public String encode(@RequestParam("data") String data) {
		return transcoderService.encode(data);
	}

	@PostMapping("decode")
	public String decode(@RequestParam("data") String data) {
		return transcoderService.decode(data);
	}

}
