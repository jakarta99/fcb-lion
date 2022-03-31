package tw.com.fcb.lion.core.ir.web;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import tw.com.fcb.lion.core.commons.http.Response;
import tw.com.fcb.lion.core.ir.service.IRPaymentService;
import tw.com.fcb.lion.core.ir.service.IRSwiftMessageCheckService;
import tw.com.fcb.lion.core.ir.web.cmd.IRSaveCmd;
import tw.com.fcb.lion.core.ir.web.dto.IR;
import tw.com.fcb.lion.core.ir.web.dto.IRQuery;

@Controller
//@RestController
//@RequestMapping("/demo")
public class WebController {
	
	@Autowired
	IRSwiftMessageCheckService irSwiftMessageCheckservice;
	
	@Autowired
	IRPaymentService irPaymentService;
	
	@RequestMapping("/demo/ir")
	public String testWeb(Map<String, String> map) {
		return "index";
	}
	
//	GET
//	case 1 : 查詢受通知單位案件數
	@GetMapping("/demo/branch-count")
	public String getCount(Model model) {
		Response<Integer> response = getCount("094", "Y");
		model.addAttribute("response", response);
        
		return "response";
	}
	
	@GetMapping("/count/{branch}")
	@Operation(description = "傳入受通知單位查詢案件數", summary="查詢案件數")
	public Response<Integer> getCount(String branch,String printAdvMk) {
		Response<Integer> response = new Response<Integer>();
		
		Integer count = irSwiftMessageCheckservice.getIrCaseCount(branch, printAdvMk);
		response.of("0000", "查詢成功", count); 
		
		return response;
	}
	
//	case 2 : 依ID查詢匯入主檔資料
	@GetMapping("/demo/getbyid")
	public String getById(Model model) {
		Response<IR> response = getById(1L);
		model.addAttribute("response", response);
        
		return "response";
	}
	
	@GetMapping("/{id}")
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
	
//	case 3 : 依匯入編號查詢匯入主檔資料
	@GetMapping("/demo/query-irno")
	public String queryIRmasterData(Model model) {
		Response<IR> response = queryIRmasterData("S1AW000003");
		model.addAttribute("response", response);
        
		return "response";
	}
	
	@GetMapping("/query")
	@Operation(description = "S211端末查詢匯入主檔資料", summary="匯入主檔查詢")
	public Response<IR> queryIRmasterData(String irNo) {
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
	
//	case 4 : 查詢多筆匯入主檔資料(模糊查詢)
	@GetMapping("/demo/query-ir-datalist")
	public String queryIRmasterDataList(Model model) {
		Response<List<IRQuery>> response = queryIRmasterDataList("S1%");
		model.addAttribute("response", response);
        
		return "response";
	}
	
	@GetMapping("/query/list")
	@Operation(description = "S211端末查詢匯入主檔資料", summary="匯入主檔多筆查詢")
	public Response<List<IRQuery>> queryIRmasterDataList(String irNo) {
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
	
	
//	POST
//	case5 : 寫入匯入主檔
	@GetMapping("/demo/irmaster-insert")
	public String irMasterInsert(Model model) {
		Response<IR> response = insert("123456");
		model.addAttribute("response", response);
        
		return "response";
	}
	
	@PostMapping
	@Operation(description = "驗證通過的電文，寫入匯入主檔(IRMaster)", summary="寫入匯入主檔")
	public Response<IR> insert(@RequestParam("seqNo") String seqNo) {
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
	
	
//	PUT
//	case 6 : 匯入解款
	@GetMapping("/demo/settle")
	public String irMasterSettle(Model model) {
		IRSaveCmd irSaveCmd = new IRSaveCmd();
		irSaveCmd.setIrNo("S1AW000001");
		irSaveCmd.setBeAdvisingBranch("094");
		irSaveCmd.setCustomerId("05052322");
		irSaveCmd.setCurrency("USD");
		irSaveCmd.setIrAmt(new BigDecimal("100"));
		irSaveCmd.setBeneficiaryAccount("string");
		irSaveCmd.setAccountInstitution("string");
		irSaveCmd.setSenderSwiftCode("1010");
		
		Response<IRSaveCmd> response = settle(irSaveCmd);
		model.addAttribute("response", response);
        
		return "response";
	}
	
	@PutMapping("/settle")
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
	
	
	// case 
	
}
