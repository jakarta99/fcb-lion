package tw.com.fcb.lion.core.ir.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import tw.com.fcb.lion.core.ir.service.IRService;
import tw.com.fcb.lion.core.ir.web.cmd.IRCriteriaCmd;
import tw.com.fcb.lion.core.ir.web.cmd.IRSaveCmd;
import tw.com.fcb.lion.core.ir.web.cmd.SwiftMessageSaveCmd;
import tw.com.fcb.lion.core.ir.web.dto.IR;

@RestController
@RequestMapping("/ir")
public class IRController {

	
	@Autowired
	IRService service;
	
	@PostMapping("/swift")
	@Operation(description = "接收 swift 電文並存到 SwiftMessage", summary="儲存 swift")
	public void receiveSwift(SwiftMessageSaveCmd message) {
		service.insert(message);
	}
	
	@GetMapping("/swift/{id}")
	public Boolean getValidateResult(Long id) {
		return true;
	}

	@GetMapping("/count/{branch}")
	@Operation(description = "傳入受通知單位查詢案件數", summary="查詢案件數")
	public Integer getCount(String branch) {
		return service.getIrCaseCount(branch);
	}

	@PostMapping
	public void insert(IRSaveCmd ir) {
		
	}



	@GetMapping("/{id}")
	public IR getById(Long id) {
		return new IR();
	}
	
	@PutMapping("/print")
	public void print(Long id) {
		
	}
	
	@PutMapping("/settle")
	@Operation(description = "修改匯入主檔付款狀態", summary="匯入解款")
	public void settle(String irNo) {
		service.settle(irNo);
	}
}