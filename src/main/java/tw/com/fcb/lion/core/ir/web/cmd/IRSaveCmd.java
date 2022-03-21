package tw.com.fcb.lion.core.ir.web.cmd;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "匯入 SWIFT 主檔資料")
public class IRSaveCmd {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "序號")
	Long id;

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
	
	@Schema(description = "是否為本行客戶")
	String ourCustomer;
	
	@Schema(description = "客戶統編")
	String customerId;
	
	@Schema(description = "幣別")
	String currency;
	
	@Schema(description = "匯入匯款金額")
	BigDecimal irAmt;
	
	@Schema(description = "外匯存款金額")
	BigDecimal fxDeposit;
	
	@Schema(description = "即期結匯金額")
	BigDecimal spotSettledFx;
	
	@Schema(description = "手續費支出(入帳差額為負數時)")
	String commCharge;

	@Schema(description = "折美匯率")
	String toUsFxRate;

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

	@Schema(description = "費用明細")
	String chargeType;

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

	@Schema(description = "處理日")
	@DateTimeFormat(pattern = "yyyyMMdd")
	LocalDate processDate;

	@Schema(description = "客戶電話號碼")
	String customerTelNo;

	@Schema(description = "受款人帳號(SW59-AC)")
	String accountNo;

	@Schema(description = "匯率")
	String exchangeRate;

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
}
