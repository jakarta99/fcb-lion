package tw.com.fcb.lion.core.ir.repository.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "IR_SWIFT_MESSAGE")
@Data
public class IRMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	Long id;

	@Column(name = "IR-NO")
	String irNo;
	
	@Column(name = "NATURE-OF-REMITTANCE")
	String natureOfRemittance;

	@Column(name = "NATURE-OF-REMITTANCE-SUB-CODE")
	String natureOfRemittanceSubCode;

	@Column(name = "PAID-STATS")        
	String paidStats;
	
	@Column(name = "PRINT-ADV-MK")     
	String printAdvMk;
	
	@Column(name = "BE-ADVISING-BRANCH")
	String beAdvisingBranch;
	
	@Column(name = "PROCESS-BRANCH")  
	String processBranch;
	
	@Column(name = "OUR-CUST") 
	String ourCust;
	
	@Column(name = "CUSTOMER-ID")       
	String customerId;
	
	@Column(name = "CURRENCY")
	String currency;
	
	@Column(name = "IR-AMT")        
	BigDecimal irAmt;
	
	@Column(name = "FX-DEPOSIT")         
	BigDecimal fxDeposit;
	
	@Column(name = "SPOT-SETTLED-FX")     
	BigDecimal spotSettledFx;
	
	@Column(name = "COMM-CHARGE")
	String commCharge;

	@Column(name = "TO-US-FXRATE")    
	String toUsFxrate;

	@Column(name = "senderReference")
	String senderReference;

	@Column(name = "REMITTER-INFO-1")
	String remitterInfo1;

	@Column(name = "REMITTER-INFO-2")
	String remitterInfo2;

	@Column(name = "REMITTER-INFO-3")
	String remitterInfo3;

	@Column(name = "REMITTER-INFO-4")
	String remitterInfo4;

	@Column(name = "BENEFICIARY-AC")
	String beneficiaryAccount;

	@Column(name = "BENEFICIARY-INFO-1")
	String beneficiaryInfo1;

	@Column(name = "BENEFICIARY-INFO-2")
	String beneficiaryInfo2;

	@Column(name = "BENEFICIARY-INFO-3")
	String beneficiaryInfo3;

	@Column(name = "BENEFICIARY-INFO-4")
	String beneficiaryInfo4;

	@Column(name = "CHARGE-TYPE(SHR,OUR,BEN)")
	String chargeType;

	@Column(name = "REMIT-BANK")
	String remitBank;

	@Column(name = "REMIT-BANK-INFO1")
	String remitBankInfo1;

	@Column(name = "REMIT-BANK-INFO2")
	String remitBankInfo2;

	@Column(name = "REMIT-BANK-INFO3")
	String remitBankInfo3;

	@Column(name = "REMIT-BANK-INFO4")
	String remitBankInfo4;

	@Column(name = "DEPOSIT-BANK") 
	String depositBank;

	@Column(name = "VALUE-DATE")   
	String valueDate;

	@Column(name = "ADVISING-DATE")
	String advisingDate;

	@Column(name = "PRINT-ADVISING-DATE")
	String printAdvisingDate;

	@Column(name = "PROCESS-DATE")
	String processDate;

	@Column(name = "CUSTOMER-TEL-NO")
	String customerTelNo;

	@Column(name = "ACCOUNT-NO")
	String accountNo;

	@Column(name = "EXCHANGE-RATE")          
	String exchangeRate;

	@Column(name = "BENEFICIARY-KIND")
	String beneficiaryKind;

	@Column(name = "CUSTOMER-BIRTH-DATE")
	String customerBirthDate;

	@Column(name = "NOTICE-1") 
	String notice1;

	@Column(name = "NOTICE-2")       
	String notice2;

	@Column(name = "NOTICE-3")       
	String notice3;



}
