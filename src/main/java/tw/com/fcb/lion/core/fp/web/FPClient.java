package tw.com.fcb.lion.core.fp.web;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "updfpc", url = "http://localhost:8080", path = "/fp")
public interface FPClient extends FPAccountApi {

}
