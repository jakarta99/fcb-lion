package tw.com.fcb.lion.core.ir.web.cmd;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tw.com.fcb.lion.core.ir.ChargeType;

@Data
@Schema(description = "匯入案件主檔資料")
public class IRSaveCmd {
	
	@NotBlank(message ="irNo不可空白或Null")
	@Schema(description = "匯入匯款編號")
	String irNo;
	
	@Schema(description = "匯款性質")
	String natureOfRemittance;

	@Schema(description = "匯款性質細項分類")
	String natureOfRemittanceSubCode;

	@Schema(description = "付款狀態")
	String paidStats;
	
	@Schema(description = "印製通知書記號")
	String printAdvisingMk;
	
	@Schema(description = "受通知單位")
	String beAdvisingBranch;
	
	@Schema(description = "處理單位")
	String processBranch;
	
	@Schema(description = "是否本行客戶(空白, Y, N)")
	String ourCustomer;
	
	@Schema(description = "客戶統編")
	String customerId;
	
	@Schema(description = "幣別")
	String currency;
	
	@Positive
	@Schema(description = "匯入匯款金額")
	BigDecimal irAmt;
	
	@Schema(description = "外匯存款金額")
	BigDecimal fxDeposit;
	
	@Schema(description = "即期結匯金額")
	BigDecimal spotSettledFx;
	
	@Schema(description = "手續費支出(入帳差額為負數時)")
	BigDecimal commCharge;

	@Schema(description = "折合美金匯率")
	BigDecimal toUsFxRate;

	@Schema(description = "匯款行匯出編號(匯票號碼)")
	String senderReference;

	@Schema(description = "匯款人資訊一")
	String remitterInfo1;

	@Schema(description = "匯款人資訊二")
	String remitterInfo2;

	@Schema(description = "匯款人資訊三")
	String remitterInfo3;

	@Schema(description = "匯款人資訊四")
	String remitterInfo4;

	@Schema(description = "受款人帳號")
	String beneficiaryAccount;

	@Schema(description = "受款人資訊一")
	String beneficiaryInfo1;

	@Schema(description = "受款人資訊二")
	String beneficiaryInfo2;

	@Schema(description = "受款人資訊三")
	String beneficiaryInfo3;

	@Schema(description = "受款人資訊四")
	String beneficiaryInfo4;

	@Schema(description = "費用明細(SHR,BEN,OUR)")
	ChargeType chargeType;

	@Schema(description = "匯款行SWIFT-TID")
	String remitBank;

	@Schema(description = "匯款行資訊一")
	String remitBankInfo1;

	@Schema(description = "匯款行資訊二")
	String remitBankInfo2;

	@Schema(description = "匯款行資訊三")
	String remitBankInfo3;

	@Schema(description = "匯款行資訊四")
	String remitBankInfo4;

	@Schema(description = "存匯行SWIFT-TID")
	String depositBank;

	@Schema(description = "有效日")
	@DateTimeFormat(pattern = "yyyyMMdd")
	LocalDate valueDate;

	@Schema(description = "通知日")
	@DateTimeFormat(pattern = "yyyyMMdd")
	LocalDate advisingDate;

	@Schema(description = "印製通知書日期")
	@DateTimeFormat(pattern = "yyyyMMdd")
	LocalDate printAdvisingDate;

	@Schema(description = "付款日")
	@DateTimeFormat(pattern = "yyyyMMdd")
	LocalDate processDate;

	@Schema(description = "客戶電話號碼")
	String customerTelNo;

	@Schema(description = "受款人帳號(SW59-AC)")
	String accountNo;

	@Schema(description = "匯率")
	BigDecimal exchangeRate;

	@Schema(description = "受款人身分別")
	String beneficiaryKind;

	@Schema(description = "受款人出生日期")
	@DateTimeFormat(pattern = "yyyyMMdd")
	LocalDate customerBirthDate;

	@Schema(description = "分行注意事項一")
	String notice1;

	@Schema(description = "分行注意事項二")
	String notice2;

	@Schema(description = "分行注意事項三")
	String notice3;
	
	@Schema(description = "設帳行swift code" )
	String accountInstitution;
	
	@Schema(description = "發電行 swift 代號" )
	String senderSwiftCode;
	
	@Schema(description = "SwiftMessage檢核狀態")
	String status;

	@Schema(description = "台幣手續費帳號")
	String TWDFeeAccount;
}
