package cn.bromine0x23.plantuml.controllers;

import net.sourceforge.plantuml.SkinParam;
import net.sourceforge.plantuml.syntax.LanguageDescriptor;
import net.sourceforge.plantuml.ugraphic.color.HColorSet;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Set;

/**
 * @author <a href="mailto:bromine0x23@163.com">Bromine0x23</a>
 */
@RestController
@RequestMapping("/language")
public class LanguageController {

	private static final LanguageDescriptor languageDescriptor = new LanguageDescriptor();

	@GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
	public void index(ServletResponse response) throws IOException {
		languageDescriptor.print(new PrintStream(response.getOutputStream()));
	}

	@GetMapping("types")
	public Set<String> indexType() {
		return languageDescriptor.getType();
	}

	@GetMapping("keywords")
	public Set<String> indexKeywords() {
		return languageDescriptor.getKeyword();
	}

	@GetMapping("preprocessors")
	public Set<String> indexPreprocessor() {
		return languageDescriptor.getPreproc();
	}

	@GetMapping("skin-parameters")
	public Collection<String> indexSkinParameter() {
		return SkinParam.getPossibleValues();
	}

	@GetMapping("colors")
	public Collection<String> indexColors() {
		return HColorSet.instance().names();
	}

}


