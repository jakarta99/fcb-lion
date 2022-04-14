package tw.com.fcb.lion.core.ir.web.cmd;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import tw.com.fcb.lion.core.ir.ChargeType;

@Data
@Builder
@Schema(description = "匯入 SWIFT 資料")
public class SwiftMessageSaveCmd {

	Long id;
	
	@Schema(description = "匯入 swift 序號" )
	String seqNo;
	
	@Schema(description = "處理狀態" )
	String stats;
	
	@Schema(description = "處理時間" )
	String txTime;
	
	@Schema(description = "幣別" )
	String currency;
	
	@Schema(description = "匯入金額" )
	BigDecimal irAmt;
	
	@Schema(description = "匯款行匯出編號(匯票NO) 20欄位" )
	String referenceNo;
	
	@Schema(description = "原始匯款行 SWIFT-TID" )
	String originRemitSwiftCode;
	
	@Schema(description = "原始匯款行一" )
	String originRemitBankInfo1;
	
	@Schema(description = "原始匯款行二" )
	String originRemitBankInfo2;
	
	@Schema(description = "原始匯款行三" )
	String originRemitBankInfo3;
	
	@Schema(description = "原始匯款行四" )
	String originRemitBankInfo4;
	
	@Schema(description = "受款人設帳銀行 SWIFT-TID" )
	String accountSwiftCode;
	
	@Schema(description = "設帳行一" )
	String accountBankInfo1;
	
	@Schema(description = "設帳行二" )
	String accountBankInfo2;
	
	@Schema(description = "設帳行三" )
	String accountBankInfo3;
	
	@Schema(description = "設帳行四" )
	String accountBankInfo4;
		
	@Schema(description = "受款人帳號" )
	String beneficiaryAccount;
	
	@Schema(description = "受款人一" )
	String beneficiaryInfo1;
	
	@Schema(description = "受款人二" )
	String beneficiaryInfo2;
	
	@Schema(description = "受款人三" )
	String beneficiaryInfo3;
	
	@Schema(description = "受款人四" )
	String beneficiaryInfo4;
	
	@Schema(description = "匯款人一" )
	String remitterInfo1;

	@Schema(description = "匯款人二" )
	String remitterInfo2;

	@Schema(description = "匯款人三" )
	String remitterInfo3;

	@Schema(description = "匯款人四" )
	String remitterInfo4;
	
	@Schema(description = "手續費負擔類型", example = "SHA, BEN, OUR")
	ChargeType chargeType;
	
	@Schema(description = "中間行費用_幣別1" )
	String senderChargeCurrency1;

	@Schema(description = "中間行費用_金額1" )
	BigDecimal senderCharge1;

	@Schema(description = "中間行費用_幣別2" )
	String senderChargeCurrency2;

	@Schema(description = "中間行費用_金額2" )
	BigDecimal senderCharge2;

	@Schema(description = "中間行費用_幣別3" )
	String senderChargeCurrency3;

	@Schema(description = "中間行費用_金額3" )
	BigDecimal senderCharge3;

	@Schema(description = "分行注意事項一" )
	String notice1;

	@Schema(description = "分行注意事項二" )
	String notice2;

	@Schema(description = "分行注意事項三" )
	String notice3;
	
	@Schema(description = "匯款行 SWIFT-TID" )
	String remitSwiftCode;

	@Schema(description = "匯款行一" )
	String remitBankInfo1;

	@Schema(description = "匯款行二" )
	String remitBankInfo2;

	@Schema(description = "匯款行三" )
	String remitBankInfo3;

	@Schema(description = "匯款行四" )
	String remitBankInfo4;

	@Schema(description = "存匯行 SWIFT-TID" )
	String depositSwiftCode;
	
	@Schema(description = "有效日" )
	LocalDate valueDate;
	
	@Schema(description = "收件日做自動/單筆查詢印表時之日期" )
	LocalDate receiveDate;
	
	@Schema(description = "同存記號" )
	String nstVstMk;
	
}
