package tw.com.fcb.lion.core.ir.web;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.models.media.Content;
import tw.com.fcb.lion.core.ir.repository.IRMasterRepository;
import tw.com.fcb.lion.core.ir.repository.entity.IRMaster;
import tw.com.fcb.lion.core.ir.web.cmd.IRSaveCmd;

@SpringBootTest
@AutoConfigureMockMvc
@EnableFeignClients
class IRControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	IRMasterRepository irMasterRepository;
	
	Logger log = LoggerFactory.getLogger(getClass());
	
//	判斷受通知分行(含是否為本行帳號檢查)
	@Test
	public void testIsBeAdvisingBranch() throws Exception {
		var isBeAdvisingBranch =  mockMvc.perform(get("/common/BeAdvisingBranch")
				.param("beneficiaryAccount", "091"))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		
		System.out.println("isBeAdvisingBranch: " + isBeAdvisingBranch);
	}
	
//	判斷轉匯案件
	@Test
	public void testIsRemittanceTransfer() throws Exception {
		var isRemittanceTransfer =  mockMvc.perform(get("/common/RemittanceTransfer")
				.param("accountInstitution", "string"))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		
		System.out.println("isRemittanceTransfer: " + isRemittanceTransfer);
	}
	
//	判斷所屬存匯行
	@Test
	public void testGetDepositBank() throws Exception {
		var depositBank =  mockMvc.perform(get("/common/DepositBank")
				.param("senderSwiftCode", "1001"))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		
		System.out.println("depositBank: " + depositBank);
	}
	
//	讀取銀行檔
	@Test
	public void testGetBankNameAndAddress() throws Exception {
		var bankNameAndAddress =  mockMvc.perform(get("/common/BankNameAndAddress")
				.param("bankSwiftCode", "1001"))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		
		System.out.println("bankNameAndAddress: " + bankNameAndAddress);
	}
	
//	 case1 : 傳入受通知單位查詢案件數
	@Test
	public void testGetCount() throws Exception {
		var branchCount =  mockMvc.perform(get("/ir/count/branch")
				.param("branch", "094")
				.param("printAdvMk", "Y"))
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn().getResponse().getContentAsString();
		
		System.out.println("branchCount: " + branchCount);
	}
	
//	 case2 : 依ID查詢IRMaster資料
	@Test
	public void testGetById() throws Exception {
		var id = 1;
		var data =  mockMvc.perform(get("/ir/{id}", id))
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn().getResponse().getContentAsString(Charset.defaultCharset());
		
		System.out.println("data: " + data);
	}
	
//	 case 3 : 驗證通過的電文，寫入匯入主檔(IRMaster)
	@Test
	public void testInsert() throws Exception {	
//		mapper = new ObjectMapper();
//		
//		IRSaveCmd cmd = new IRSaveCmd();
//		cmd.setBeAdvisingBranch("094");
//		cmd.setCustomerId("05052322");
//		cmd.setCurrency("USD");
//		cmd.setBeneficiaryAccount("string");
//		cmd.setAccountInstitution("string");
//		cmd.setSenderSwiftCode("1010");
		
	    JSONObject request = new JSONObject()
	            .put("seqNo", 123456);
	    
		var insert = mockMvc.perform(post("/ir")
//				.content(mapper.writeValueAsString(cmd))
				.content(request.toString()))
//				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andDo(print())
				.andReturn().getResponse().getContentAsString();

		System.out.println("insert: " + insert);
	}
	
//	case 4 : 更新印製通知書記號
	@Test
	public void testPrintAdvise() throws Exception {
		mapper = new ObjectMapper();
		
		var data =  mockMvc.perform(put("/ir/printadvise")
				.content(mapper.writeValueAsString("094"))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn().getResponse().getContentAsString();
		
		assertEquals(irMasterRepository.findByIrNo("S1AW000003").getPrintAdvisingMk(),"Y");
		
		System.out.println("data: " + data);
	}
}
