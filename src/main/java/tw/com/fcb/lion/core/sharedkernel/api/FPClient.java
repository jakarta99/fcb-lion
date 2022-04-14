package tw.com.fcb.lion.core.sharedkernel.api;

import org.springframework.cloud.openfeign.FeignClient;

import tw.com.fcb.lion.core.fp.web.FPApi;

@FeignClient(name = "updfpc", url = "http://localhost:8080", path = "/fpc")
public interface FPClient extends FPApi {

}
