package cn.bromine0x23.plantuml.controllers;

import cn.bromine0x23.plantuml.services.TranscoderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author <a href="mailto:bromine0x23@163.com">Bromine0x23</a>
 */
@Controller
@RequestMapping("uml")
public class UmlController {

	private final TranscoderService transcoderService;

	public UmlController(TranscoderService transcoderService) {
		this.transcoderService = transcoderService;
	}

	@GetMapping("{encodedSource:.*}")
	public String show(
		@PathVariable("encodedSource") String encodedSource,
		@CookieValue(value = "layout", required = false) String layout,
		Model model
	) {
		var source = transcoderService.decode(encodedSource);
		model.addAttribute("encodedSource", encodedSource);
		model.addAttribute("source", source);
		if ("left-right".equals(layout)) {
			return "editor/left-right";
		} else {
			return "editor/fluid";
		}
	}

	@PostMapping
	public String create(@RequestParam("source") String source) {
		return "redirect:uml/" + transcoderService.encode(source);
	}

	@PostMapping("recover")
	public String recover(@RequestParam("url") String url) throws IOException {
		return "redirect:" + extractEncodedSource(url);
	}

	private String extractEncodedSource(String url) throws MalformedURLException {
		var path = new URL(url).getPath();
		var segments = path.split("/");
		if (segments.length > 2) {
			return segments[segments.length - 1];
		}
		return "SyfFKj2rKt3CoKnELR1Io4ZDoSa70000";
	}

}
