package tw.com.fcb.lion.core.ir.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import tw.com.fcb.lion.core.ir.repository.entity.IRMaster;
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

//	KAI - 驗證通過的電文，寫入匯入主檔(IRMaster)
	@PostMapping
	@Operation(description = "驗證通過的電文，寫入匯入主檔(IRMaster)", summary="寫入匯入主檔")
	public void insert(IRSaveCmd irSaveCmd) {
		service.insertIrMaster(irSaveCmd);
	}

	@GetMapping("/{id}")
	@Operation(description = "依ID查詢IRMaster資料", summary="依ID查詢IRMaster資料")
	public Optional<IRMaster> getById(@Parameter(description = "name of ID", example = "1") @PathVariable Long id) {
		return service.getById(id);
	}
	
	@PutMapping("/print")
	@Operation(description = "更新印製通知書記號", summary="更新列印通知書狀態")
	public void print(Long id) {
		service.updatePrintAdviceMark(id);
	}
	
	@PutMapping("/settle")
	@Operation(description = "修改匯入主檔付款狀態", summary="匯入解款")
	public void settle(String irNo) {
		service.settle(irNo);
	}
}