package tw.com.fcb.lion.core.ir.web.cmd;

import java.math.BigDecimal;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "匯入 SWIFT 主檔資料")
public class IRSaveCmd {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "ID")
	Long id;

	@Schema(description = "IR_NO")
	String irNo;
	
	@Schema(description = "NATURE_OF_REMITTANCE")
	String natureOfRemittance;

	@Schema(description = "NATURE_OF_REMITTANCE_SUB_CODE")
	String natureOfRemittanceSubCode;

	@Schema(description = "PAID_STATS")
	String paidStats;
	
	@Schema(description = "PRINT_ADVISING_MK")
	String printAdvisingMk;
	
	@Schema(description = "BE_ADVISING_BRANCH")
	String beAdvisingBranch;
	
	@Schema(description = "PROCESS_BRANCH")
	String processBranch;
	
	@Schema(description = "OUR_CUSTOMER")
	String ourCustomer;
	
	@Schema(description = "CUSTOMER_ID")
	String customerId;
	
	@Schema(description = "CURRENCY")
	String currency;
	
	@Schema(description = "IR_AMT")
	BigDecimal irAmt;
	
	@Schema(description = "FX_DEPOSIT")
	BigDecimal fxDeposit;
	
	@Schema(description = "SPOT_SETTLED_FX")
	BigDecimal spotSettledFx;
	
	@Schema(description = "COMM_CHARGE")
	String commCharge;

	@Schema(description = "TO_US_FXRATE")
	String toUsFxRate;

	@Schema(description = "SENDER_REFERENCE")
	String senderReference;

	@Schema(description = "REMITTER_INFO_1")
	String remitterInfo1;

	@Schema(description = "REMITTER_INFO_2")
	String remitterInfo2;

	@Schema(description = "REMITTER_INFO_3")
	String remitterInfo3;

	@Schema(description = "REMITTER_INFO_4")
	String remitterInfo4;

	@Schema(description = "BENEFICIARY_AC")
	String beneficiaryAccount;

	@Schema(description = "BENEFICIARY_INFO_1")
	String beneficiaryInfo1;

	@Schema(description = "BENEFICIARY_INFO_2")
	String beneficiaryInfo2;

	@Schema(description = "BENEFICIARY_INFO_3")
	String beneficiaryInfo3;

	@Schema(description = "BENEFICIARY_INFO_4")
	String beneficiaryInfo4;

	@Schema(description = "CHARGE_TYPE")
	String chargeType;

	@Schema(description = "REMIT_BANK")
	String remitBank;

	@Schema(description = "REMIT_BANK_INFO1")
	String remitBankInfo1;

	@Schema(description = "REMIT_BANK_INFO2")
	String remitBankInfo2;

	@Schema(description = "REMIT_BANK_INFO3")
	String remitBankInfo3;

	@Schema(description = "REMIT_BANK_INFO4")
	String remitBankInfo4;

	@Schema(description = "DEPOSIT_BANK")
	String depositBank;

	@Schema(description = "VALUE_DATE")
	String valueDate;

	@Schema(description = "ADVISING_DATE")
	String advisingDate;

	@Schema(description = "PRINT_ADVISING_DATE")
	String printAdvisingDate;

	@Schema(description = "PROCESS_DATE")
	String processDate;

	@Schema(description = "CUSTOMER_TEL_NO")
	String customerTelNo;

	@Schema(description = "ACCOUNT_NO")
	String accountNo;

	@Schema(description = "EXCHANGE_RATE")
	String exchangeRate;

	@Schema(description = "BENEFICIARY_KIND")
	String beneficiaryKind;

	@Schema(description = "CUSTOMER_BIRTH_DATE")
	String customerBirthDate;

	@Schema(description = "NOTICE_1")
	String notice1;

	@Schema(description = "NOTICE_2")
	String notice2;

	@Schema(description = "NOTICE_3")
	String notice3;
}
