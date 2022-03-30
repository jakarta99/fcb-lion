package tw.com.fcb.lion.core.ir.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import tw.com.fcb.lion.core.commons.http.Response;
import tw.com.fcb.lion.core.ir.service.IRPaymentService;
import tw.com.fcb.lion.core.ir.service.IRSwiftMessageCheckService;
import tw.com.fcb.lion.core.ir.web.dto.IR;

@Controller
public class WebController {
	
	@Autowired
	IRSwiftMessageCheckService irSwiftMessageCheckservice;
	
	@Autowired
	IRPaymentService irPaymentService;
	
	@RequestMapping("ir")
	public String testWeb(Map<String, String> map) {
		return "index";
	}
	
	// case 1 : 依ID查詢IRMaster資料
	@GetMapping("/getbyid")
	public String getById(Model model) {
		Response<IR> response = getById(1L);
		model.addAttribute("response", response);
        
		return "response_get";
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
	
	// case 2
	@GetMapping("/queryirno")
	public String queryIRmasterData(Model model) {
		Response<IR> response = queryIRmasterData("S1AW000003");
		model.addAttribute("response", response);
        
		return "response_get";
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
	
	// case 3
	
	
	// case 4
	
	
	// case 5
}
