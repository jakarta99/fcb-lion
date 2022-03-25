package tw.com.fcb.lion.core.ir.web;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
class IRControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	@Test
	public void testGetCount() throws Exception {
		var branchCount =  mockMvc.perform(get("/ir/count/branch").
				param("branch", "094").
				param("printAdvMk", "Y"))
		.andExpect(status().isOk())
		.andDo(print())
		.andReturn().getResponse().getContentAsString();
		
		System.out.println("branchCount: " + branchCount);
	}
	
	@Test
	public void testGetById() throws Exception {
		var data =  mockMvc.perform(get("/ir/1"))
		.andExpect(status().isOk())
		.andDo(print())
		.andReturn().getResponse().getContentAsString();
		
		System.out.println("data: " + data);
	}
	
	@Test
	public void testInsert() throws Exception {
		JSONObject mockObject = new JSONObject();
		mockObject.put("beAdvisingBranch", "091");
		mockObject.put("customerId", "05052322");
		mockObject.put("currency", "USD");
		mockObject.put("beneficiaryAccount", "STRING");
		mockObject.put("accountInstitution", "string");
		mockObject.put("senderSwiftCode", "1001");
		
		mockMvc.perform(post("/ir")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(String.valueOf(mockObject)))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
	}
	
//	@Test
//	public void testInsert() throws Exception {
//		var mockInsert = IRSaveCmd.builder()
//				.beAdvisingBranch("091")
//				.customerId("86483666")
//				.currency("USD")
//				.beneficiaryAccount("STRING")
//				.accountInstitution("string")
//				.senderSwiftCode("1001")
//				.build();
//		
//		JSONObject mockObject = new JSONObject();
//		mockObject.put("beAdvisingBranch", "091");
//		mockObject.put("customerId", "05052322");
//		mockObject.put("currency", "USD");
//		mockObject.put("beneficiaryAccount", "STRING");
//		mockObject.put("accountInstitution", "string");
//		mockObject.put("senderSwiftCode", "1001");
//		
//		mockMvc.perform(post("/ir")
//				.accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(String.valueOf(mockObject)))
//				.andExpect(status().isOk())
//				.andReturn().getResponse().getContentAsString();
//	}
	
	@Test
	public void testPrintAdvise() throws Exception {
	}
}
