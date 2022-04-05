package tw.com.fcb.lion.core.ir.web;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import tw.com.fcb.lion.core.commons.http.Response;
import tw.com.fcb.lion.core.ir.repository.entity.FPCuster;
import tw.com.fcb.lion.core.ir.repository.entity.FPMaster;
import tw.com.fcb.lion.core.ir.service.FPCService;

@RestController
@RequestMapping("/fpc")
public class FPController {

	@Autowired
	FPCService  fPCService;
	
//	FPC 查詢帳號是否存在
	@GetMapping("/fpc-query/{account}/fpcuster")
	@Operation(description = "依帳號查詢FPCuster資料", summary="查詢FPCuster資料")
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
	
//	FPM BAL 查詢該帳號之幣別餘額
	@GetMapping("/fpc-query/{account}/{crcy}/balance")
	@Operation(description = "依帳號、幣別查詢FPM餘額", summary="查詢FPM餘額")
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
	
//	更新幣別FPM餘額(先加後減)
	@PutMapping("/fpc-upd/{account}/{crcy}/balance")
	@Operation(description = "更新幣別FPM餘額(先加後減)", summary="更新幣別FPM餘額")
	public Response<FPCuster> updfpmBal(@PathVariable("account") String acc,@PathVariable("crcy") String crcy) {
		
		Response<FPCuster> response = new Response<FPCuster>();
		try {
			FPCuster fPCusterAcc = fPCService.updfpmBal(acc,crcy,BigDecimal.valueOf(100),BigDecimal.valueOf(10));
			response.of("0000", "交易成功", fPCusterAcc); 
			
        } 
		catch (Exception e) {
            response.of("9999","交易失敗，請重新輸入", null);
        }
		
		return response;
	}
	
//	新增FPC帳號 幣別FPM - 測試用    ERR = 400
	@PutMapping("/fpc-ins/{account}/{crcy}/creat")
	@Operation(description = "新增帳號、幣別資訊", summary="新增帳號、幣別資訊")
	public Response<FPCuster> insfpcAccount(@PathVariable("account") FPCuster accData) {
		
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
            response.of("9999","交易失敗，請重新輸入", null);
        }
		
		return response;
	}
}
