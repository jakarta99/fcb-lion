package tw.com.fcb.lion.core.commons.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import tw.com.fcb.lion.core.commons.http.DateConverter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new DateConverter());
	}
}
