package tw.com.fcb.lion.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients(basePackages = {"tw.com.fcb.fp.core.fp"})
//@ComponentScan({"tw.com.fcb.fp.core.fp.web.FPClient"})
public class FcbCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(FcbCoreApplication.class, args);
	}

}
