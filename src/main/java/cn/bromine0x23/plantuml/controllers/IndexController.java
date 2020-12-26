package cn.bromine0x23.plantuml.controllers;

import cn.bromine0x23.plantuml.services.TranscoderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author <a href="mailto:bromine0x23@163.com">Bromine0x23</a>
 */
@Controller
@RequestMapping
public class IndexController {

	private static final String DEFAULT_SOURCE = "@startuml\nBob -> Alice : hello\n@enduml";

	private final TranscoderService transcoderService;

	public IndexController(TranscoderService transcoderService) {
		this.transcoderService = transcoderService;
	}

	@GetMapping
	public String index(
		@CookieValue(value = "layout", required = false) String layout,
		Model model
	) {
		model.addAttribute("source", DEFAULT_SOURCE);
		model.addAttribute("encodedSource", transcoderService.encode(DEFAULT_SOURCE));
		if ("left-right".equals(layout)) {
			return "editor/left-right";
		} else {
			return "editor/fluid";
		}
	}

}
