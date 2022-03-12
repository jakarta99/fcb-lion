package tw.com.fcb.lion.core.ir.repository.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

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
public class IRSwiftMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	Long id;

	@Column(name = "SEQ_NO")
	String seqNo;

	@Column(name = "STATS")
	String stats;

	@Column(name = "TX-TIME")
	String txTime;

	@Column(name = "CURRENCY")
	String currency;

	@Column(name = "IR-AMT")
	BigDecimal irAmt;

	@Column(name = "SENDER-REFERENCE")
	String senderReference;

	@Column(name = "ORDERING-INSTITUTION")
	String orderingInstitution;

	@Column(name = "ORDERING-INSTITUTION-INFO-1")
	String orderingInstitutionInfo1;

	@Column(name = "ORDERING-INSTITUTION-INFO-2")
	String orderingInstitutionInfo2;

	@Column(name = "ORDERING-INSTITUTION-INFO-3")
	String orderingInstitutionInfo3;

	@Column(name = "ORDERING-INSTITUTION-INFO-4")
	String orderingInstitutionInfo4;

	@Column(name = "ACCOUNT-INSTITUTION")
	String accountInstitution;

	@Column(name = "ACCOUNT-INSTITUTION-AC")
	String accountInstitutionAccount;

	@Column(name = "ACCOUNT-INSTITUTION-INFO-1")
	String accountInstitutionInfo1;

	@Column(name = "ACCOUNT-INSTITUTION-INFO-2")
	String accountInstitutionInfo2;

	@Column(name = "ACCOUNT-INSTITUTION-INFO-3")
	String accountInstitutionInfo3;

	@Column(name = "ACCOUNT-INSTITUTION-INFO-4")
	String accountInstitutionInfo4;

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

	@Column(name = "REMITTER-INFO-1")
	String remitterInfo1;

	@Column(name = "REMITTER-INFO-2")
	String remitterInfo2;

	@Column(name = "REMITTER-INFO-3")
	String remitterInfo3;

	@Column(name = "REMITTER-INFO-4")
	String remitterInfo4;

	@Column(name = "CHARGE-TYPE")
	String chargeType;

	@Column(name = "SENDER-CHARGE-CURRENCY-1")
	String senderChargeCurrency1;

	@Column(name = "SENDER-CHARGE-1")
	BigDecimal senderCharge1;

	@Column(name = "SENDER-CHARGE-CURRENCY-2")
	String senderChargeCurrency2;

	@Column(name = "SENDER-CHARGE-2")
	BigDecimal senderCharge2;

	@Column(name = "SENDER-CHARGE-CURRENCY-3")
	String senderChargeCurrency3;

	@Column(name = "SENDER-CHARGE-3")
	BigDecimal senderCharge3;

	@Column(name = "NOTICE-1")
	String notice1;

	@Column(name = "NOTICE-2")
	String notice2;

	@Column(name = "NOTICE-3")
	String notice3;

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

	@Column(name = "RECEIVE-DATE")
	String receiveDate;

	@Column(name = "NST-VST-MK")
	String nstVstMk;

}
