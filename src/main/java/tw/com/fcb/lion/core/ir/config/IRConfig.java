package tw.com.fcb.lion.core.ir.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;

@Configuration
@Getter
@Setter
@PropertySource("classpath:application.properties")
public class IRConfig {
	@Value("${fcb.core.ir.env-type}")
	String envType;
}
