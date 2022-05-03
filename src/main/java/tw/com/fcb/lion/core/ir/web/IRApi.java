package tw.com.fcb.lion.core.ir.web;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import tw.com.fcb.fp.core.commons.http.Response;
import tw.com.fcb.fp.core.fp.web.dto.FPAccountDto;
import tw.com.fcb.lion.core.ir.web.cmd.IRSaveCmd;
import tw.com.fcb.lion.core.ir.web.cmd.SwiftMessageSaveCmd;
import tw.com.fcb.lion.core.ir.web.dto.IR;
import tw.com.fcb.lion.core.ir.web.dto.IRQuery;

public interface IRApi {

	@PostMapping("/swift")
	@Operation(description = "接收 swift 電文並存到 SwiftMessage", summary="CASE 1：接收 swift 電文儲存 swift")
	public Response<List<SwiftMessageSaveCmd>> receiveSwift();
	
	@PostMapping("/swift/query/{seqNo}")
	@Operation(description = "查詢 SwiftMessage", summary="查詢 swift")
	public Response<SwiftMessageSaveCmd> querySwift(@PathVariable("seqNo") String seqNo);
	
	@PutMapping("/swift/recheck")
	@Operation(description = "更新 SwiftMessage，並驗證電文，執行寫入匯入主檔", summary="修改 swift")
	public Response<IR> recheckSwift(SwiftMessageSaveCmd swiftmessage);
	

	@GetMapping("/branches/count")
	@Operation(description = "傳入受通知單位查詢案件數", summary="查詢案件數")
	public Integer getCount(@RequestParam("branchCode") String branch,@RequestParam("printAdvMk") String printAdvMk);
	
	@PostMapping("/ir-masters/{seqNo}")
	@Operation(description = "驗證通過的電文，寫入匯入主檔(IRMaster)", summary="寫入匯入主檔")
	public Response<IR> insert(@PathVariable("seqNo") String seqNo);
	
	@PostMapping("/ir-masters/notify/{seqNo}")
	@Operation(description = "通知", summary="通知")
	public String notify(@PathVariable("seqNo") String seqNo);
	
	@GetMapping("/ir-masters/{id}")
	@Operation(description = "依ID查詢IRMaster資料", summary="依ID查詢IRMaster資料")
	public Response<IR> getById(@Parameter(description = "name of ID", example = "1") @PathVariable Long id);
	
	
	@PutMapping("/branches/{branchCode}/print-advise")
	@Operation(description = "更新印製通知書記號", summary="更新列印通知書狀態")
	public void printAdvise(@PathVariable("branchCode") String branch);
	
	@GetMapping("/ir-masters/list/fuzzy-search")
	@Operation(description = "S211端末查詢匯入主檔資料", summary="匯入主檔多筆模糊查詢")
	public Response<List<IRQuery>> queryIRmasterDataList(@RequestParam("irNo")String irNo);
	
	@GetMapping("/ir-masters/ir-No/{irNo}")
	@Operation(description = "S211端末查詢匯入主檔資料", summary="匯入主檔查詢")
	public Response<IR> queryIRmasterData(@PathVariable("irNo") String irNo);
	
	@PutMapping("/ir-masters/settle/original-currency-fee")
	@Operation(description = "S211匯入解款手續費內扣原幣", summary="匯入解款")
	public Response<IRSaveCmd> settleOriginalCurrencyFee(@Validated @RequestBody IRSaveCmd irSaveCmd);
	
	@PutMapping("/ir-masters/settle/TWD-fee")
	@Operation(description = "S211匯入解款手續費外收台幣", summary="匯入解款")
	public Response<IRSaveCmd> settleTWDFee(@Validated @RequestBody IRSaveCmd irSaveCmd);
	
	@PutMapping("/ir-masters/settle/charge-our")
	@Operation(description = "S211匯入解款charge our案件不收手續費", summary="匯入解款")
	public Response<IRSaveCmd> settleChargeOur(@Validated @RequestBody IRSaveCmd irSaveCmd);
	
	@PutMapping("/fpc-masters/updfpm/{irNo}/balance-original-currency-fee")
	@Operation(description = "S211匯入解款內扣原幣手續費", summary="匯入解款FPC")
	public Response<FPAccountDto> depositOriginalCurrencyFeeOriginalCurrency(@Parameter(example = "S1NHA00113")@PathVariable("irNo") String irNo);
	
	@PutMapping("/fpc-masters/updfpm/{irNo}/balance-TWD-fee")
	@Operation(description = "S211匯入解款外收台幣手續費", summary="匯入解款FPC")
	public Response<FPAccountDto> depositOriginalCurrencyFeeTWD(@Parameter(example = "S1NHA00114")@PathVariable("irNo") String irNo);
	
	@PutMapping("/fpc-masters/updfpm/{irNo}/balance-charge-our")
	@Operation(description = "S211匯入解款charge our案件不收手續費", summary="匯入解款FPC")
	public Response<FPAccountDto> depositChargeOur(@Parameter(example = "S1NHA00115")@PathVariable("irNo") String irNo);
	
	@GetMapping
	public String getSettings();
}
