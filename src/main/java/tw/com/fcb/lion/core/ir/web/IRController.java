package tw.com.fcb.lion.core.ir.web;

import java.io.IOException;
import java.util.List;

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
import tw.com.fcb.lion.core.commons.enums.ResponseStatus;
import tw.com.fcb.lion.core.commons.http.Response;
import tw.com.fcb.lion.core.commons.mock.CommonController;
import tw.com.fcb.lion.core.ir.service.CommonCheckService;
import tw.com.fcb.lion.core.ir.service.IRPaymentService;
import tw.com.fcb.lion.core.ir.service.IRSwiftMessageCheckService;
import tw.com.fcb.lion.core.ir.web.cmd.IRSaveCmd;
import tw.com.fcb.lion.core.ir.web.cmd.SwiftMessageSaveCmd;
import tw.com.fcb.lion.core.ir.web.dto.IR;

@RestController
@RequestMapping("/ir")
//@RequiredArgsConstructor
@OpenAPIDefinition(info = @Info(title = "獅子王's  匯入  API", version = "v1.0.0"))
public class IRController {

	@Autowired
	IRSwiftMessageCheckService irSwiftMessageCheckservice;
	
	@Autowired
	IRPaymentService irPaymentService;
	
	@Autowired
	CommonCheckService commonCheckService;
	
	@Autowired
	CommonController commonController;
	
//	final IRMapper irMapper;
	
	@PostMapping("/swift")
	@Operation(description = "接收 swift 電文並存到 SwiftMessage", summary="儲存 swift")
	public void receiveSwift(SwiftMessageSaveCmd message) {

		Response<List<SwiftMessageSaveCmd>> response = new Response<List<SwiftMessageSaveCmd>>();
		try{
			List<SwiftMessageSaveCmd> msg = irSwiftMessageCheckservice.loadFromFile();
			System.out.println("test" + msg);
			System.out.println("*****read******");
//			將上述檔案內容寫入IR_SWIFT_MESSAGE
            for(SwiftMessageSaveCmd saveCmd : msg) {
				irSwiftMessageCheckservice.insert(saveCmd);
            }
			response.setCode(null);
			response.setStatus(ResponseStatus.SUCCESS);
			response.setMessage("交易成功");
			response.setData(msg);
		} catch (IOException e) {
			// 不明錯誤 : 9999
			response.setStatus(ResponseStatus.ERROR);
			response.setCode("9999");
			response.setMessage("交易失敗，請重新輸入");
			e.printStackTrace();
		}
	}
	
	@GetMapping("/swift/{id}")
	public Boolean getValidateResult(Long id) {
		return true;
	}

	@GetMapping("/count/{branch}")
	@Operation(description = "傳入受通知單位查詢案件數", summary="查詢案件數")
	public Integer getCount(String branch,String printAdvMk) {
		return irSwiftMessageCheckservice.getIrCaseCount(branch, printAdvMk);
	}

//	KAI - 驗證通過的電文，寫入匯入主檔(IRMaster)
	@PostMapping
	@Operation(description = "驗證通過的電文，寫入匯入主檔(IRMaster)", summary="寫入匯入主檔")
	public Response<IRSaveCmd> insert(IRSaveCmd irSaveCmd) {
		Response<IRSaveCmd> response = new Response<IRSaveCmd>();
		
		try {
//			commonController.getBeAdvisingBranch(1L);
//			commonController.getDepositBank(1L);
//			commonController.getBankNameAndAddress(1L);
//			commonController.IsRemittanceTransfer(1L);
			
			commonCheckService.checkBranchCode(irSaveCmd.getBeAdvisingBranch());
			commonCheckService.checkCustomerId(irSaveCmd.getCustomerId());
			var fxRate = commonCheckService.checkCurrency(irSaveCmd.getCurrency());
			irSaveCmd.setExchangeRate(fxRate.getSpotBoughFxRate());
			
			irSwiftMessageCheckservice.insertIrMaster(irSaveCmd);
			
			response = new Response<IRSaveCmd>();
			response.setStatus(ResponseStatus.SUCCESS);
			response.setCode("0000");
			response.setMessage("新增成功");
			response.setData(irSaveCmd);
		} 
		catch (Exception e) {
			response.setStatus(ResponseStatus.ERROR);
			response.setCode("9999");
			response.setMessage(e.getMessage());
		}
		
		return response;
	}

	@GetMapping("/{id}")
	@Operation(description = "依ID查詢IRMaster資料", summary="依ID查詢IRMaster資料")
	public Response<IR> getById(@Parameter(description = "name of ID", example = "1") @PathVariable Long id) {
		Response<IR> response = new Response<IR>();
		try {
			IR ir = irSwiftMessageCheckservice.getById(id);
			response = new Response<IR>();
			response.setCode(null);
			response.setStatus(ResponseStatus.SUCCESS);
			response.setMessage("交易成功");
			response.setData(ir);
        } catch (Exception e) {
			// 不明錯誤 : 9999
			response.setStatus(ResponseStatus.ERROR);
			response.setCode("9999");
			response.setMessage("交易失敗，請重新輸入");
            e.printStackTrace();
        }
		return response;
	}
	
	@PutMapping("/printadvise")
	@Operation(description = "更新印製通知書記號", summary="更新列印通知書狀態")
	public void printAdvise(String branch) {
		irPaymentService.updatePrintAdviceMark(branch);
	}
	
	@PutMapping("/query")
	@Operation(description = "S211端末查詢匯入主檔資料", summary="匯入主檔查詢")
	public IR queryIRmasterData(String irNo) {
		return irPaymentService.queryIRmasterData(irNo);
	}
	
	@PutMapping("/settle")
	@Operation(description = "S211匯入解款", summary="匯入解款")
	public Response<IR> settle(IR ir) {
		Response<IR> response = new Response<IR>();
		try {
			ir.setCommCharge(irPaymentService.calculateFee());
			irPaymentService.settle(ir);
			response.setCode(null);
			response.setStatus(ResponseStatus.SUCCESS);
			response.setMessage("交易成功");
			response.setData(ir);
        } catch (Exception e) {
			// 不明錯誤 : 9999
			response.setStatus(ResponseStatus.ERROR);
			response.setCode("9999");
			response.setMessage("交易失敗，請重新輸入");
            e.printStackTrace();
        }
		return response;
	}
}