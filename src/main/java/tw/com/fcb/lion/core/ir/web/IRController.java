package tw.com.fcb.lion.core.ir.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import tw.com.fcb.lion.core.commons.http.Response;
import tw.com.fcb.lion.core.ir.service.IRPaymentService;
import tw.com.fcb.lion.core.ir.service.IRSwiftMessageCheckService;
import tw.com.fcb.lion.core.ir.web.cmd.IRSaveCmd;
import tw.com.fcb.lion.core.ir.web.cmd.SwiftMessageSaveCmd;
import tw.com.fcb.lion.core.ir.web.dto.IR;
import tw.com.fcb.lion.core.ir.web.dto.IRQuery;

@RestController
@RequestMapping("/ir")
@OpenAPIDefinition(info = @Info(title = "獅子王's  匯入  API", version = "v1.0.0"))
public class IRController {

	@Autowired
	IRSwiftMessageCheckService irSwiftMessageCheckservice;
	
	@Autowired
	IRPaymentService irPaymentService;
	
	
//	final IRMapper irMapper;
	
	@PostMapping("/swift")
	@Operation(description = "接收 swift 電文並存到 SwiftMessage", summary="CASE 1：接收 swift 電文儲存 swift")
	public Response<List<SwiftMessageSaveCmd>> receiveSwift() {

		Response<List<SwiftMessageSaveCmd>> response = new Response<List<SwiftMessageSaveCmd>>();
		List<SwiftMessageSaveCmd> msg = null;
		try{
//			List<SwiftMessageSaveCmd> msg = irSwiftMessageCheckservice.loadFromFile();
			msg = irSwiftMessageCheckservice.loadFromFile();
			
//			將上述檔案內容寫入IR_SWIFT_MESSAGE
            for(SwiftMessageSaveCmd saveCmd : msg) {
				irSwiftMessageCheckservice.insert(saveCmd);
            }
			response.of("0000", "交易成功，寫入" + msg.size() + "筆資料", msg); 
		} 
		catch (IOException e) {
			response.of("9999", e.getMessage(), msg); 
		}
		return response;
	}
	
	@PostMapping("/swift/query/{seqNo}")
	@Operation(description = "查詢 SwiftMessage", summary="查詢 swift")
	public Response<SwiftMessageSaveCmd> querySwift(@PathVariable("seqNo") String seqNo) {
		Response<SwiftMessageSaveCmd> response = new Response<SwiftMessageSaveCmd>();
		
		try {
			SwiftMessageSaveCmd swiftmessage = irSwiftMessageCheckservice.getBySwiftMessageSeqNo(seqNo);
			response.of("0000", "交易成功", swiftmessage); 
        } 
		catch (Exception e) {
            response.of("9999", "交易失敗，請重新輸入", null);
        }
		
		return response;
	}

//	@GetMapping("/swift/{id}")
//	public Boolean getValidateResult(Long id) {
//		return true;
//	}

	@GetMapping("/branches/count")
	@Operation(description = "傳入受通知單位查詢案件數", summary="查詢案件數")
	public Integer getCount(@RequestParam("branchCode") String branch,@RequestParam("printAdvMk") String printAdvMk) {
		return irSwiftMessageCheckservice.getIrCaseCount(branch, printAdvMk);
	}
	
//	KAI - 驗證通過的電文，寫入匯入主檔(IRMaster)
	@PostMapping("/ir-masters/{seqNo}")
	@Operation(description = "驗證通過的電文，寫入匯入主檔(IRMaster)", summary="寫入匯入主檔")
	public Response<IR> insert(@PathVariable("seqNo") String seqNo) {
		Response<IR> response = new Response<IR>();
		IR ir = new IR();
		
		try {
			ir = irSwiftMessageCheckservice.insertIRMaster(seqNo);
			
			if(ir.getStatus().equals("2")) {
				response.of("0000", "新增成功", ir);
			}
			else if(ir.getStatus().equals("3")){
				response.of("9998", "驗證電文失敗", ir); 
			}
		} 
		catch (Exception e) {
			response.of("9999", e.getMessage(), ir);
		}

		return response;
	}
	
	@GetMapping("/ir-masters/{id}")
	@Operation(description = "依ID查詢IRMaster資料", summary="依ID查詢IRMaster資料")
	public Response<IR> getById(@Parameter(description = "name of ID", example = "1") @PathVariable Long id) {
		Response<IR> response = new Response<IR>();
		IR ir = new IR();
		
		try {
			ir = irSwiftMessageCheckservice.getById(id);
			response.of("0000", "交易成功", ir); 
        } 
		catch (Exception e) {
            response.of("9999", "交易失敗，請重新輸入", ir);
        }
		
		return response;
	}
	
	@PutMapping("/branches/{branchCode}/print-advise")
	@Operation(description = "更新印製通知書記號", summary="更新列印通知書狀態")
	public void printAdvise(@PathVariable("branchCode") String branch) {
		irPaymentService.updatePrintAdviceMark(branch);
	}
	
	@GetMapping("/ir-masters/list/fuzzy-search")
	@Operation(description = "S211端末查詢匯入主檔資料", summary="匯入主檔多筆模糊查詢")
	public Response<List<IRQuery>> queryIRmasterDataList(@RequestParam("irNoirNo")String irNo) {
		Response<List<IRQuery>> response = new Response<List<IRQuery>>();
		
		try {
			List<IRQuery> irquerylist = irPaymentService.queryIRmasterDataLikeByirNo(irNo);
			response.of("0000", "交易成功", irquerylist); 
        } 
		catch (Exception e) {
            response.of("9999", "交易失敗，請重新輸入", null);
        }
		
		return response;
	}
	
	@GetMapping("/ir-masters/ir-No/{irNo}")
	@Operation(description = "S211端末查詢匯入主檔資料", summary="匯入主檔查詢")
	public Response<IR> queryIRmasterData(@PathVariable("irNo") String irNo) {
		Response<IR> response = new Response<IR>();
		
		try {
			IR ir = irPaymentService.queryIRmasterData(irNo);
			response.of("0000", "交易成功", ir); 
        } 
		catch (Exception e) {
            response.of("9999", "交易失敗，請重新輸入", null);
        }
		
		return response;
	}
	
	@PutMapping("/ir-masters/{irNo}/settle")
	@Operation(description = "S211匯入解款", summary="匯入解款")
	public Response<IRSaveCmd> settle(@Validated @RequestBody IRSaveCmd irSaveCmd) {
		Response<IRSaveCmd> response = new Response<IRSaveCmd>();
		BigDecimal irFee = irPaymentService.calculateFee(irSaveCmd.getIrAmt());
		try {
			irSaveCmd.setCommCharge(irFee);
			irPaymentService.settle(irSaveCmd);
			response.of("0000", "交易成功", irSaveCmd); 
        } 
		catch (Exception e) {
            response.of("9999", "交易失敗，請重新輸入", irSaveCmd);
        }
		
		return response;
	}
		
}