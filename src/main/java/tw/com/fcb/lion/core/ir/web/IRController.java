package tw.com.fcb.lion.core.ir.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import lombok.RequiredArgsConstructor;
import tw.com.fcb.fp.core.commons.http.Response;
import tw.com.fcb.fp.core.fp.web.FPClient;
import tw.com.fcb.fp.core.fp.web.dto.FPAccountDto;
import tw.com.fcb.lion.core.ir.config.IRConfig;
import tw.com.fcb.lion.core.ir.service.IRPaymentService;
import tw.com.fcb.lion.core.ir.service.IRSwiftMessageCheckService;
import tw.com.fcb.lion.core.ir.web.cmd.IRSaveCmd;
import tw.com.fcb.lion.core.ir.web.cmd.SwiftMessageSaveCmd;
import tw.com.fcb.lion.core.ir.web.dto.IR;
import tw.com.fcb.lion.core.ir.web.dto.IRQuery;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ir")
@OpenAPIDefinition(info = @Info(title = "獅子王's  匯入  API", version = "v1.0.0"))
public class IRController implements IRApi{

	@Autowired
	IRSwiftMessageCheckService irSwiftMessageCheckservice;

	@Autowired
	IRPaymentService irPaymentService;

	@Autowired
	IRConfig irConfig;

	@Value("${tw.com.fcb.lion.core.ir.web.file-path}")
	String filePath;

	final FPClient fPClient;
//	final IRMapper irMapper;

	Logger log = LoggerFactory.getLogger(getClass());

	public Response<List<SwiftMessageSaveCmd>> receiveSwift() {

		Response<List<SwiftMessageSaveCmd>> response = new Response<List<SwiftMessageSaveCmd>>();
		List<SwiftMessageSaveCmd> msg = null;
		log.debug("filePath = {} " , filePath);
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

	
	public Response<IR> recheckSwift(SwiftMessageSaveCmd swiftmessage) {
		
		Response<IR> response = new Response<IR>();
		IR ir = new IR();
		try {
			irSwiftMessageCheckservice.updateSwiftMessage(swiftmessage);
			
			ir = irSwiftMessageCheckservice.insertIRMaster(swiftmessage.getSeqNo());
			
			if(ir.getStatus().equals("2")) {
				response.of("0000", "新增成功", ir);
			}
			else if(ir.getStatus().equals("3")){
				response.of("9998", "驗證電文失敗", ir); 
			}
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

	public Integer getCount(@RequestParam("branchCode") String branch,@RequestParam("printAdvMk") String printAdvMk) {
		return irSwiftMessageCheckservice.getIrCaseCount(branch, printAdvMk);
	}
	
//	KAI - 驗證通過的電文，寫入匯入主檔(IRMaster)
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
	
	public String notify(@PathVariable("seqNo") String seqNo) {
		Response<IR> response = new Response<IR>();
		response = insert(seqNo);
		
		String notifyMessage = "";
		if(response.getCode().equals("0000")) {
			notifyMessage = response.getData().getBeAdvisingBranch() + "分行有待處理案件";
		}
		else {
			notifyMessage = response.getMessage();
		}

		return notifyMessage;
	}
	
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
	
	public void printAdvise(@PathVariable("branchCode") String branch) {
		irPaymentService.updatePrintAdviceMark(branch);
	}
	
	public Response<List<IRQuery>> queryIRmasterDataList(@RequestParam("irNo")String irNo) {
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
	
	public Response<IRSaveCmd> settleOriginalCurrencyFee(@Validated @RequestBody IRSaveCmd irSaveCmd) {
		Response<IRSaveCmd> response = new Response<IRSaveCmd>();
		BigDecimal irFee = irPaymentService.calculateOriginalCurrencyFee(irSaveCmd.getIrAmt(),irSaveCmd.getCurrency());
		try {
				if (irPaymentService.checkBeneficiaryKind(irSaveCmd.getCustomerId(), irSaveCmd.getBeneficiaryKind())){
//					irSaveCmd.setCommCharge(irFee);
					irSaveCmd.setCommCharge(irPaymentService.calculateOriginalCurrencyFee(irSaveCmd.getIrAmt(),irSaveCmd.getCurrency()));
					irPaymentService.settle(irSaveCmd);
					response.of("0000", "交易成功", irSaveCmd);
				}else {
					response.of("ED01", "交易失敗，身份別檢核有誤", irSaveCmd);
				}

			}
			catch (Exception e) {
				response.of("9999", "交易失敗，請重新輸入", irSaveCmd);
			}

		return response;
	}

	public Response<IRSaveCmd> settleTWDFee(@Validated @RequestBody IRSaveCmd irSaveCmd) {
		Response<IRSaveCmd> response = new Response<IRSaveCmd>();
		BigDecimal irFee = irPaymentService.calculateTWDFee(irSaveCmd.getIrAmt(),irSaveCmd.getCurrency());
		try {
			if (irPaymentService.checkBeneficiaryKind(irSaveCmd.getCustomerId(), irSaveCmd.getBeneficiaryKind())) {
//				irSaveCmd.setCommCharge(irFee);
				irSaveCmd.setCommCharge(irPaymentService.calculateTWDFee(irSaveCmd.getIrAmt(),irSaveCmd.getCurrency()));
				irPaymentService.settle(irSaveCmd);
				response.of("0000", "交易成功", irSaveCmd);
			} else {
				response.of("ED01", "交易失敗，身份別檢核有誤", irSaveCmd);
			}
		}
			catch (Exception e){
				response.of("9999", "交易失敗，請重新輸入", irSaveCmd);
		}



		return response;
	}

	public Response<IRSaveCmd> settleChargeOur(@Validated @RequestBody IRSaveCmd irSaveCmd) {
		Response<IRSaveCmd> response = new Response<IRSaveCmd>();
			try {
				if (irPaymentService.checkBeneficiaryKind(irSaveCmd.getCustomerId(), irSaveCmd.getBeneficiaryKind())) {
				irPaymentService.checkBeneficiaryKind(irSaveCmd.getCustomerId(),irSaveCmd.getBeneficiaryKind());
				irPaymentService.settle(irSaveCmd);
				response.of("0000", "交易成功", irSaveCmd);
			} else {
				response.of("ED01", "交易失敗，身份別檢核有誤", irSaveCmd);
				}
			}
			catch (Exception e) {
				response.of("9999", "交易失敗，請重新輸入", irSaveCmd);
			}

		return response;
	}

	//入外存原幣 手續費內扣
	public Response<FPAccountDto> depositOriginalCurrencyFeeOriginalCurrency(@Parameter(example = "S1NHA00113")@PathVariable("irNo") String irNo) {
		Response<FPAccountDto> response = new Response<FPAccountDto>();
		try {
			IR ir = irPaymentService.queryIRmasterData(irNo);
			BigDecimal irFee = irPaymentService.calculateOriginalCurrencyFee(ir.getIrAmt(),ir.getCurrency());
			BigDecimal txnAmt = ir.getIrAmt().subtract(irFee);
//			BigDecimal txnAmt = ir.getIrAmt().subtract(ir.getCommCharge());
			if (ir.getBeneficiaryAccount() ==null || ir.getCurrency() == null) {
				response.of("M5A6", "交易失敗，帳號、幣別不得為空值",null);
			}else {
//				Response<FPAccountDto> fPCusterAccR = fPClient.updfpmBal(ir.getBeneficiaryAccount(),ir.getCurrency(),ir.getIrAmt(),irFee);
				Response<FPAccountDto> fPCusterAccR = fPClient.depositFpm(ir.getBeneficiaryAccount(),ir.getCurrency(),txnAmt,"匯入匯款");
				response.of("0000", "交易成功", fPCusterAccR.getData());
			}			 
        } 
		catch (Exception e) {
			log.debug("e = {}",e);
            response.of("9999", "交易失敗，請重新輸入", null);
        }
		
		return response;
	}

	public Response<FPAccountDto> depositOriginalCurrencyFeeTWD(@Parameter(example = "S1NHA00114")@PathVariable("irNo") String irNo) {
		Response<FPAccountDto> response = new Response<FPAccountDto>();

		try {
			IR ir = irPaymentService.queryIRmasterData(irNo);
			BigDecimal irFee = irPaymentService.calculateTWDFee(ir.getIrAmt(),ir.getCurrency());
			if (ir.getBeneficiaryAccount() ==null || ir.getCurrency() == null) {
				response.of("M5A6", "交易失敗，帳號、幣別不得為空值",null);
			}else {
//				Response<FPAccountDto> fPCusterAccR = fPClient.updfpmBal(ir.getBeneficiaryAccount(),ir.getCurrency(),ir.getIrAmt(),BigDecimal.ZERO);
				Response<FPAccountDto> fPCusterAccR = fPClient.depositFpm(ir.getBeneficiaryAccount(),ir.getCurrency(),ir.getIrAmt(),"匯入匯款");
//				Response<FPAccountDto> fPCusterAccTWDFee = fPClient.updfpmBal(ir.getTWDFeeAccount(),"TWD",BigDecimal.ZERO,irFee);
				Response<FPAccountDto> fPCusterAccTWDFee = fPClient.withdrawFpm(ir.getTWDFeeAccount(),"TWD",irFee,"匯入匯款手續費");
				response.of("0000", "交易成功", fPCusterAccR.getData());
			}
		}
		catch (Exception e) {
			log.debug("e = {}",e);
			response.of("9999", "交易失敗，請重新輸入", null);
		}

		return response;
	}


	public Response<FPAccountDto> depositTWDFeeTWD(@Parameter(example = "S1NHA00117")@PathVariable("irNo") String irNo) {
		Response<FPAccountDto> response = new Response<FPAccountDto>();

		try {
			IR ir = irPaymentService.queryIRmasterData(irNo);
			BigDecimal irFee = irPaymentService.calculateTWDFee(ir.getIrAmt(),ir.getCurrency());
			BigDecimal irTWDAmt = ir.getIrAmt().multiply(ir.getExchangeRate());
			if (ir.getBeneficiaryAccount() ==null || ir.getCurrency() == null) {
				response.of("M5A6", "交易失敗，帳號、幣別不得為空值",null);
			}else {
				Response<FPAccountDto> fPCusterAccR = fPClient.depositFpm(ir.getBeneficiaryAccount(),"TWD",irTWDAmt.subtract(irFee),"匯入匯款");
				response.of("0000", "交易成功", fPCusterAccR.getData());
			}
		}
		catch (Exception e) {
			log.debug("e = {}",e);
			response.of("9999", "交易失敗，請重新輸入", null);
		}

		return response;
	}

	public Response<FPAccountDto> depositChargeOur(@Parameter(example = "S1NHA00115")@PathVariable("irNo") String irNo) {
		Response<FPAccountDto> response = new Response<FPAccountDto>();
		Long txnLogId = null;
		Long rollbackId = System.currentTimeMillis();//TODO maybe has conflict
		try {
			IR ir = irPaymentService.queryIRmasterData(irNo);
			if (ir.getBeneficiaryAccount() ==null || ir.getCurrency() == null) {
				response.of("M5A6", "交易失敗，帳號、幣別不得為空值",null);
			}else {
				
				Response<FPAccountDto> fPCusterAccX = fPClient.depositFpmRollback(ir.getBeneficiaryAccount(),ir.getCurrency(),ir.getIrAmt(),"匯入匯款",rollbackId);
				txnLogId = fPCusterAccX.getData().getTxnLogId();
				log.info("txnLogId = {}",txnLogId);
//				throw new Exception(irNo);		//交易補償測試		
				response.of("0000", "交易成功", fPCusterAccX.getData());
			}
		}
		catch (Exception e) {
//			交易補償
			try {
				log.info("rollbackId = {}",rollbackId);
				fPClient.undoFpm(rollbackId);
			} catch (Exception e2) {
				log.debug("e2 = {}",e2);
				response.of("XXXX", "交易補償失敗，請重新輸入", null);
			}
			
			log.debug("e = {}",e);
			response.of("9999", "交易失敗，請重新輸入", null);
		}

		return response;
	}
	
	public String getSettings() {
		return "FileName: " + filePath + " ENVIRONMENT: " + irConfig.getEnvType();
	}
}