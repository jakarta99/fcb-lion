package tw.com.fcb.lion.core.ir.repository.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Table(name = "IR_MASTER")
@Data
public class IRMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	Long id;

	@Column(name = "IR_NO")
	String irNo;
	
	@Column(name = "NATURE_OF_REMITTANCE")
	String natureOfRemittance;

	@Column(name = "NATURE_OF_REMITTANCE_SUB_CODE")
	String natureOfRemittanceSubCode;

	@Column(name = "PAID_STATS")
	String paidStats;
	
	@Column(name = "PRINT_ADVISING_MK")
	String printAdvisingMk;
	
	@Column(name = "BE_ADVISING_BRANCH")
	String beAdvisingBranch;
	
	@Column(name = "PROCESS_BRANCH")
	String processBranch;
	
	@Column(name = "OUR_CUSTOMER")
	String ourCustomer;
	
	@Column(name = "CUSTOMER_ID")
	String customerId;
	
	@Column(name = "CURRENCY")
	String currency;
	
	@Column(name = "IR_AMT")
	BigDecimal irAmt;
	
	@Column(name = "FX_DEPOSIT")
	BigDecimal fxDeposit;
	
	@Column(name = "SPOT_SETTLED_FX")
	BigDecimal spotSettledFx;
	
	@Column(name = "COMM_CHARGE")
	String commCharge;

	@Column(name = "TO_US_FXRATE")
	String toUsFxRate;

	@Column(name = "SENDER_REFERENCE")
	String senderReference;

	@Column(name = "REMITTER_INFO_1")
	String remitterInfo1;

	@Column(name = "REMITTER_INFO_2")
	String remitterInfo2;

	@Column(name = "REMITTER_INFO_3")
	String remitterInfo3;

	@Column(name = "REMITTER_INFO_4")
	String remitterInfo4;

	@Column(name = "BENEFICIARY_AC")
	String beneficiaryAccount;

	@Column(name = "BENEFICIARY_INFO_1")
	String beneficiaryInfo1;

	@Column(name = "BENEFICIARY_INFO_2")
	String beneficiaryInfo2;

	@Column(name = "BENEFICIARY_INFO_3")
	String beneficiaryInfo3;

	@Column(name = "BENEFICIARY_INFO_4")
	String beneficiaryInfo4;

	@Column(name = "CHARGE_TYPE")
	String chargeType;

	@Column(name = "REMIT_BANK")
	String remitBank;

	@Column(name = "REMIT_BANK_INFO1")
	String remitBankInfo1;

	@Column(name = "REMIT_BANK_INFO2")
	String remitBankInfo2;

	@Column(name = "REMIT_BANK_INFO3")
	String remitBankInfo3;

	@Column(name = "REMIT_BANK_INFO4")
	String remitBankInfo4;

	@Column(name = "DEPOSIT_BANK")
	String depositBank;

	@Column(name = "VALUE_DATE")
	@DateTimeFormat(pattern = "yyyyMMdd")
	LocalDate valueDate;

	@Column(name = "ADVISING_DATE")
	@DateTimeFormat(pattern = "yyyyMMdd")
	LocalDate advisingDate;

	@Column(name = "PRINT_ADVISING_DATE")
	@DateTimeFormat(pattern = "yyyyMMdd")
	LocalDate printAdvisingDate;

	@Column(name = "PROCESS_DATE")
	@DateTimeFormat(pattern = "yyyyMMdd")
	LocalDate processDate;

	@Column(name = "CUSTOMER_TEL_NO")
	String customerTelNo;

	@Column(name = "ACCOUNT_NO")
	String accountNo;

	@Column(name = "EXCHANGE_RATE")
	String exchangeRate;

	@Column(name = "BENEFICIARY_KIND")
	String beneficiaryKind;

	@Column(name = "CUSTOMER_BIRTH_DATE")
	@DateTimeFormat(pattern = "yyyyMMdd")
	LocalDate customerBirthDate;

	@Column(name = "NOTICE_1")
	String notice1;

	@Column(name = "NOTICE_2")
	String notice2;

	@Column(name = "NOTICE_3")
	String notice3;



}
