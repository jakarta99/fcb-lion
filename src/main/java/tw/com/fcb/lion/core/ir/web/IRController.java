package tw.com.fcb.lion.core.ir.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import tw.com.fcb.lion.core.ir.repository.entity.IRMaster;
import tw.com.fcb.lion.core.ir.service.IRService;
import tw.com.fcb.lion.core.ir.web.cmd.IRSaveCmd;
import tw.com.fcb.lion.core.ir.web.cmd.SwiftMessageSaveCmd;
import tw.com.fcb.lion.core.ir.web.dto.IR;

@RestController
@RequestMapping("/ir")
@RequiredArgsConstructor
@OpenAPIDefinition(info = @Info(title = "獅子王's  匯入  API", version = "v1.0.0"))
public class IRController {

	@Autowired
	IRService service;
	
	final IRMapper irMapper;
	
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
	public Integer getCount(String branch,String printAdvMk) {
		return service.getIrCaseCount(branch, printAdvMk);
	}

//	KAI - 驗證通過的電文，寫入匯入主檔(IRMaster)
	@PostMapping
	@Operation(description = "驗證通過的電文，寫入匯入主檔(IRMaster)", summary="寫入匯入主檔")
	public void insert(IRSaveCmd irSaveCmd) {
		service.insertIrMaster(irSaveCmd);
	}

	@GetMapping("/{id}")
	@Operation(description = "依ID查詢IRMaster資料", summary="依ID查詢IRMaster資料")
	public IR getById(@Parameter(description = "name of ID", example = "1") @PathVariable Long id) {
		IR ir = null;
		try {
			ir = service.getById(id);
        } catch (Exception e) {
//        	求救~這裡資料怎麼丟到swagger上呈現
        	System.out.println("請重新輸入");
            e.printStackTrace();
        }
		return ir;
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