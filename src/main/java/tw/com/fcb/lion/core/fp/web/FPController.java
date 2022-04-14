package tw.com.fcb.lion.core.fp.web;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Parameter;
import tw.com.fcb.lion.core.commons.http.Response;
import tw.com.fcb.lion.core.fp.service.FPCService;
import tw.com.fcb.lion.core.fp.service.cmd.FPAccountCreateCmd;
import tw.com.fcb.lion.core.fp.service.vo.FPAccountVo;
import tw.com.fcb.lion.core.fp.web.dto.FPAccountDto;
import tw.com.fcb.lion.core.fp.web.request.FPAccountCreateRequest;
import tw.com.fcb.lion.core.ir.repository.entity.FPCuster;
import tw.com.fcb.lion.core.ir.repository.entity.FPMaster;


public class FPController implements FPAccountApi {

	@Autowired
	FPCService  fPCService;
	
	public Response<FPCuster> getByAccount(@Parameter(description = "帳號", example = "09340123456") @PathVariable("account") String acc) {
		Response<FPCuster> response = new Response<FPCuster>();
		
		try {
			FPCuster fPCusterAcc = fPCService.getByfpcAccount(acc);
			if (fPCusterAcc == null) {
				response.of("D001", "查詢"+acc +"無此帳號，請重新輸入", fPCusterAcc); 
			}else {
				response.of("0000", "交易成功", fPCusterAcc); 
			}
        } 
		catch (Exception e) {
            response.of("9999","交易失敗，請重新輸入", null);
        }
		
		return response;
	}
	

	public Response<BigDecimal> getByfpmCurrencyBal(@Parameter(description = "帳號", example = "09340123456")
													@PathVariable("account") String acc,@PathVariable("crcy")String crcy) {
		
		Response<BigDecimal> response = new Response<BigDecimal>();
		try {
			FPCuster fPCusterAcc = fPCService.getByfpcAccount(acc);
			FPMaster fPMaster    = fPCService.getByfpmCurrencyData(acc,crcy);
			if (fPCusterAcc == null) {
				response.of("D001", "查詢"+acc +"無此帳號，請重新輸入", null); 
			}else {
				BigDecimal fpmBal = fPMaster.getFpmBalance();
				response.of("0000", "交易成功，帳號"+acc+"之幣別"+crcy+"餘額", fpmBal); 
			}
        } 
		catch (Exception e) {
            response.of("9999","交易失敗，請重新輸入", null);
        }
		
		return response;
	}
	

	public Response<FPCuster> updfpmBal(@PathVariable("account") String acc,@PathVariable("crcy") String crcy,
										@RequestParam BigDecimal addAmt,@RequestParam BigDecimal subAmt) {
		
		Response<FPCuster> response = new Response<FPCuster>();
		try {
			FPCuster fPCusterAcc = fPCService.updfpmBal(acc,crcy,addAmt,subAmt);
			response.of("0000", "交易成功", fPCusterAcc); 
			
        } 
		catch (Exception e) {
            response.of("9999","交易失敗，請重新輸入", null);
        }
		
		return response;
	}


	@Override
	public Response<FPAccountDto> create(FPAccountCreateRequest createRequest) {
		Response<FPAccountDto> response = new Response<FPAccountDto>();
		try {
			
			
			// 1. 接值
			
				
			// 2. 驗證
			
			// createRequest 驗證
			
			// 3. 呼叫服務
			FPAccountCreateCmd createCmd = new FPAccountCreateCmd();
			
			createCmd.setAccountNo(createRequest.getAccountNo());
			createCmd.setCustomerIdno(createRequest.getCustomerIdno());
			
			FPAccountVo vo = fPCService.create(createCmd);

			
			// 4. 設值
			FPAccountDto dto = new FPAccountDto();
			dto.setAccountNo(vo.getAccountNo());
			dto.setCustomerIdno(vo.getCustomerIdno());
			dto.setValidCrcyCnt(vo.getValidCrcyCnt());
			
			// 5. 訊息
			response.of("0000", "交易成功", dto); 
			
//			FPCuster fPCusterAcc = fPCService.getByfpcAccount(accData.getFpcAccount());
//			FPMaster fPMaster    = fPCService.getByfpmCurrencyData(accData.getFpcAccount(),crcy);
//			if (fPMaster != null) {
//				response.of("D123", "查詢("+ accData.getFpcAccount()+"," + crcy +"此帳號幣別已存在，請重新輸入", fPCusterAcc); 
//			}else {
//				fPCusterAcc = fPCService.insfpcAccount(accData,crcy);
//				response.of("0000", "交易成功", fPCusterAcc); 
//			}
        } 
		catch (Exception e) {
			System.out.println("err=" + e);
            response.of("9999","交易失敗，請重新輸入", null);
        }
		
		return response;
	}
	

	
	
}
