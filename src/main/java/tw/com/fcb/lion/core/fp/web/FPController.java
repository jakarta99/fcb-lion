package tw.com.fcb.lion.core.fp.web;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Parameter;
import tw.com.fcb.lion.core.commons.http.Response;
import tw.com.fcb.lion.core.fp.common.enums.BookType;
import tw.com.fcb.lion.core.fp.service.FPCService;
import tw.com.fcb.lion.core.fp.service.vo.FPAccountVo;
import tw.com.fcb.lion.core.fp.web.dto.FPAccountDto;
import tw.com.fcb.lion.core.fp.web.mapper.FpAccountDtoMapper;
import tw.com.fcb.lion.core.fp.web.request.FPAccountCreateRequest;
import tw.com.fcb.lion.core.ir.repository.entity.FPCuster;
import tw.com.fcb.lion.core.ir.repository.entity.FPMaster;

@RestController
@RequestMapping("/fp/accounts")
public class FPController implements FPAccountApi {

	@Autowired
	FPCService fpcService;

	@Autowired
	FpAccountDtoMapper mapper;

	Logger log = LoggerFactory.getLogger(getClass());

	public Response<FPCuster> getByAccount(
			@Parameter(description = "帳號", example = "09340123456") @PathVariable("account") String acc) {
		Response<FPCuster> response = new Response<FPCuster>();

		try {
			FPCuster fPCusterAcc = fpcService.getByfpcAccount(acc);
			if (fPCusterAcc == null) {
				response.of("D001", "查詢" + acc + "無此帳號，請重新輸入", fPCusterAcc);
			} else {
				response.of("0000", "交易成功", fPCusterAcc);
			}
		} catch (Exception e) {
			response.of("9999", "交易失敗，請重新輸入", null);
		}

		return response;
	}

	public Response<BigDecimal> getByfpmCurrencyBal(
			@Parameter(description = "帳號", example = "09340123456") @PathVariable("account") String acc,
			@PathVariable("crcy") String crcy) {

		Response<BigDecimal> response = new Response<BigDecimal>();
		try {
			FPCuster fPCusterAcc = fpcService.getByfpcAccount(acc);
			FPMaster fPMaster = fpcService.getByfpmCurrencyData(acc, crcy);
			if (fPCusterAcc == null) {
				response.of("D001", "查詢" + acc + "無此帳號，請重新輸入", null);
			} else {
				BigDecimal fpmBal = fPMaster.getFpmBalance();
				response.of("0000", "交易成功，帳號" + acc + "之幣別" + crcy + "餘額", fpmBal);
			}
		} catch (Exception e) {
			response.of("9999", "交易失敗，請重新輸入", null);
		}

		return response;
	}

	public Response<FPCuster> updfpmBal(@PathVariable("account") String acc, @PathVariable("crcy") String crcy,
			@RequestParam BigDecimal addAmt, @RequestParam BigDecimal subAmt) {

		Response<FPCuster> response = new Response<FPCuster>();
		try {
			FPCuster fPCusterAcc = fpcService.updfpmBal(acc, crcy, addAmt, subAmt);
			response.of("0000", "交易成功", fPCusterAcc);

		} catch (Exception e) {
			response.of("9999", "交易失敗，請重新輸入", null);
		}

		return response;
	}

	@Override
	public Response<FPAccountDto> create(FPAccountCreateRequest createRequest) {
		Response<FPAccountDto> response = new Response<FPAccountDto>();
		try {

			log.info("createRequest:{}", createRequest);
			// 1. 接值

			// 2. 驗證 Request
			String accountNoStr = createRequest.getAccountNo();
			try {
				Double.parseDouble(accountNoStr);
			} catch (NumberFormatException e) {
				response.of("M501", "帳號=" + accountNoStr + "非數值請重新輸入", null);
				return response;
			}
			FPCuster fpCusterAcc = fpcService.getByfpcAccount(createRequest.getAccountNo());
			if (fpCusterAcc != null) {
				response.of("D123", "查詢("+ createRequest.getAccountNo() +"此帳號已存在，請重新輸入", null); 
				return response;
			}

			// 3. 呼叫服務
//********* status 、validCrcyCnt、crcyCode、bookType未傳入 QQ 
			System.out.println("@@@@!!! test = " + createRequest);
			FPAccountVo accountVo = fpcService.createFpc(mapper.toCreateCmd(createRequest));

//			多本存摺(單幣別)須新增幣別資訊
			if (createRequest.getBookType() == BookType.MULTI) {
				FPAccountVo crcyVo = fpcService.createFpm(createRequest);
				log.info("crcyVo: {}" , crcyVo);
			}
			System.out.println("@@@  vo=" + accountVo);
//			FPAccountCreateCmd createCmd = new FPAccountCreateCmd();
//			createCmd.setAccountNo(createRequest.getAccountNo());
//			createCmd.setCustomerIdno(createRequest.getCustomerIdno());
//			FPAccountVo vo = fPCService.create(createCmd);

			// 4. 設值
			FPAccountDto dto = new FPAccountDto();
			dto.setId(accountVo.getId());
			dto.setAccountNo(accountVo.getAccountNo());
			dto.setCustomerIdno(accountVo.getCustomerIdno());
			dto.setStatus(String.valueOf(accountVo.getStatus()));
			dto.setValidCrcyCnt(accountVo.getValidCrcyCnt());
			// 5. 訊息
//			response.of("0000", "交易成功", mapper.fromVo(vo));
			response.of("0000", "交易成功", dto);


		} catch (Exception e) {
			System.out.println("err=" + e);
			response.of("9999", "交易失敗，請重新輸入", null);
		}

		return response;
	}

}
