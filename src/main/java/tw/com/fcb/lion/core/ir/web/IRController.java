package tw.com.fcb.lion.core.ir.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import tw.com.fcb.lion.core.ir.web.cmd.IRCriteriaCmd;
import tw.com.fcb.lion.core.ir.web.cmd.IRSaveCmd;
import tw.com.fcb.lion.core.ir.web.cmd.SwiftMessageSaveCmd;
import tw.com.fcb.lion.core.ir.web.dto.IR;

@RestController
@RequestMapping("/ir")
public class IRController {

	@PostMapping("/swift")
	@Operation(description = "接收 swift 電文並存到 SwiftMessage", summary="儲存 swift")
	public void receiveSwift(SwiftMessageSaveCmd message) {
		
	}
	
	@GetMapping("/swift/{id}")
	public Boolean getValidateResult(Long id) {
		return true;
	}
	
	@PostMapping
	public void insert(IRSaveCmd ir) {
		
	}
	
	@GetMapping("/count")
	public Integer getCount(IRCriteriaCmd criteria) {
		return 0;
	}
	
	@GetMapping("/{id}")
	public IR getById(Long id) {
		return new IR();
	}
	
	@PutMapping("/print")
	public void print(Long id) {
		
	}
	
	@PutMapping("/settle")
	public void settle(Long id) {
		
	}
}