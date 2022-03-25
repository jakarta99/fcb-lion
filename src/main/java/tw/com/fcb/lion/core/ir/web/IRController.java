package tw.com.fcb.lion.core.ir.web;

import java.io.IOException;
import java.math.BigDecimal;
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
import lombok.RequiredArgsConstructor;
import tw.com.fcb.lion.core.commons.http.Response;
import tw.com.fcb.lion.core.commons.mock.CommonController;
import tw.com.fcb.lion.core.ir.service.CommonCheckService;
import tw.com.fcb.lion.core.ir.service.IRPaymentService;
import tw.com.fcb.lion.core.ir.service.IRSwiftMessageCheckService;
import tw.com.fcb.lion.core.ir.web.cmd.IRSaveCmd;
import tw.com.fcb.lion.core.ir.web.cmd.SwiftMessageSaveCmd;
import tw.com.fcb.lion.core.ir.web.dto.IR;
import tw.com.fcb.lion.core.ir.web.dto.IRQuery;
import tw.com.fcb.lion.core.sharedkernel.api.MainFrameClient;

@RestController
@RequestMapping("/ir")
@RequiredArgsConstructor
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
	
	final MainFrameClient mainFrameClient;
	
//	final IRMapper irMapper;
	
	@PostMapping("/swift")
	@Operation(description = "接收 swift 電文並存到 SwiftMessage", summary="儲存 swift")
	public void receiveSwift(SwiftMessageSaveCmd message) {

		Response<List<SwiftMessageSaveCmd>> response = new Response<List<SwiftMessageSaveCmd>>();
		List<SwiftMessageSaveCmd> msg = null;
		try{
//			List<SwiftMessageSaveCmd> msg = irSwiftMessageCheckservice.loadFromFile();
			msg = irSwiftMessageCheckservice.loadFromFile();
			System.out.println("test" + msg);
			System.out.println("*****read******");
//			將上述檔案內容寫入IR_SWIFT_MESSAGE
            for(SwiftMessageSaveCmd saveCmd : msg) {
				irSwiftMessageCheckservice.insert(saveCmd);
            }

			response.showMessage(msg, "0000", "交易成功"); 
		} 
		catch (IOException e) {
			response.showMessage(msg, "9999", e.getMessage()); 
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
	public Response<IR> insert(IRSaveCmd irSaveCmd) {
		Response<IR> response = new Response<IR>();
		IR ir = new IR();
		
		try {
			Boolean isBeAdvisingBranch = mainFrameClient.isBeAdvisingBranch(irSaveCmd.getBeneficiaryAccount());
			Boolean isRemittanceTransfer = mainFrameClient.isRemittanceTransfer(irSaveCmd.getAccountInstitution());
			String depositBank = mainFrameClient.getBankNameAndAddress(irSaveCmd.getSenderSwiftCode());
			String bankNameAndAddress = mainFrameClient.getDepositBank(irSaveCmd.getSenderSwiftCode());
			
			if(isBeAdvisingBranch == true && isRemittanceTransfer == false) {
				commonCheckService.checkBranchCode(irSaveCmd.getBeAdvisingBranch());
				commonCheckService.checkCustomerId(irSaveCmd.getCustomerId());
				var fxRate = commonCheckService.checkCurrency(irSaveCmd.getCurrency());
				
				irSaveCmd.setExchangeRate(fxRate.getSpotBoughFxRate());
				irSaveCmd.setDepositBank(depositBank);
				irSaveCmd.setRemitBankInfo1(bankNameAndAddress);
				
				ir = irSwiftMessageCheckservice.insertIrMaster(irSaveCmd);
				response.showMessage(ir, "0000", "新增成功"); 	
			}
		} 
		catch (Exception e) {
			response.showMessage(ir, "9999", e.getMessage()); 
		}
		
		return response;
	}

	@GetMapping("/{id}")
	@Operation(description = "依ID查詢IRMaster資料", summary="依ID查詢IRMaster資料")
	public Response<IR> getById(@Parameter(description = "name of ID", example = "1") @PathVariable Long id) {
		Response<IR> response = new Response<IR>();
		IR ir = new IR();
		
		try {
			ir = irSwiftMessageCheckservice.getById(id);
			response.showMessage(ir, "0000", "交易成功"); 
        } 
		catch (Exception e) {
            response.showMessage(ir, "9999", "交易失敗，請重新輸入");
        }
		
		return response;
	}
	
	@PutMapping("/printadvise")
	@Operation(description = "更新印製通知書記號", summary="更新列印通知書狀態")
	public void printAdvise(String branch) {
		irPaymentService.updatePrintAdviceMark(branch);
	}
	
	@GetMapping("/query/list")
	@Operation(description = "S211端末查詢匯入主檔資料", summary="匯入主檔多筆查詢")
	public Response<List<IRQuery>> queryIRmasterDataList(String irNo) {
		Response<List<IRQuery>> response = new Response<List<IRQuery>>();
		
		try {
			List<IRQuery> irquerylist = irPaymentService.queryIRmasterDataLikeByirNo(irNo);
			response.showMessage(irquerylist, "0000", "交易成功"); 
        } 
		catch (Exception e) {
            response.showMessage(null, "9999", "交易失敗，請重新輸入");
        }
		
		return response;
		
	}
	
	@GetMapping("/query")
	@Operation(description = "S211端末查詢匯入主檔資料", summary="匯入主檔查詢")
	public Response<IR> queryIRmasterData(String irNo) {
		Response<IR> response = new Response<IR>();
		
		try {
			IR ir = irPaymentService.queryIRmasterData(irNo);
			response.showMessage(ir, "0000", "交易成功"); 
        } 
		catch (Exception e) {
            response.showMessage(null, "9999", "交易失敗，請重新輸入");
        }
		
		return response;
		
	}
	
	@PutMapping("/settle")
	@Operation(description = "S211匯入解款", summary="匯入解款")
	public Response<IRSaveCmd> settle(IRSaveCmd ir) {
		Response<IRSaveCmd> response = new Response<IRSaveCmd>();
		BigDecimal irFee = irPaymentService.calculateFee(ir.getIrAmt());
		try {
			ir.setCommCharge(irFee);
			irPaymentService.settle(ir);
			response.showMessage(ir, "0000", "交易成功"); 
        } 
		catch (Exception e) {
            response.showMessage(ir, "9999", "交易失敗，請重新輸入");
        }
		
		return response;
	}
}