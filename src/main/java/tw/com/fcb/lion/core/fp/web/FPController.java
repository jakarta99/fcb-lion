package tw.com.fcb.lion.core.fp.web;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Parameter;
import tw.com.fcb.lion.core.commons.http.Response;
import tw.com.fcb.lion.core.ir.repository.entity.FPCuster;
import tw.com.fcb.lion.core.ir.repository.entity.FPMaster;
import tw.com.fcb.lion.core.ir.service.FPCService;


public class FPController implements FPApi {

	@Autowired
	FPCService  fPCService;
	
	public Response<FPCuster> getByfpcAccount(@Parameter(description = "帳號", example = "09340123456") @PathVariable("account") String acc) {
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



	public Response<FPCuster> insfpcAccount(@RequestBody FPCuster accData) {
		
		Response<FPCuster> response = new Response<FPCuster>();
		try {
			FPCuster fPCusterAcc = fPCService.insfpcAccount(accData);
			response.of("0000", "交易成功", fPCusterAcc); 
			
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
