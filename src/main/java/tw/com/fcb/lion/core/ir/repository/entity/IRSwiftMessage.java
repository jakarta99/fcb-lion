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

	@Column(name = "TX_TIME")
	String txTime;

	@Column(name = "CURRENCY")
	String currency;

	@Column(name = "IR_AMT")
	BigDecimal irAmt;

	@Column(name = "SENDER_REFERENCE")
	String senderReference;

	@Column(name = "ORDERING_INSTITUTION")
	String orderingInstitution;

	@Column(name = "ORDERING_INSTITUTION_INFO_1")
	String orderingInstitutionInfo1;

	@Column(name = "ORDERING_INSTITUTION_INFO_2")
	String orderingInstitutionInfo2;

	@Column(name = "ORDERING_INSTITUTION_INFO_3")
	String orderingInstitutionInfo3;

	@Column(name = "ORDERING_INSTITUTION_INFO_4")
	String orderingInstitutionInfo4;

	@Column(name = "ACCOUNT_INSTITUTION")
	String accountInstitution;

	@Column(name = "ACCOUNT_INSTITUTION_AC")
	String accountInstitutionAccount;

	@Column(name = "ACCOUNT_INSTITUTION_INFO_1")
	String accountInstitutionInfo1;

	@Column(name = "ACCOUNT_INSTITUTION_INFO_2")
	String accountInstitutionInfo2;

	@Column(name = "ACCOUNT_INSTITUTION_INFO_3")
	String accountInstitutionInfo3;

	@Column(name = "ACCOUNT_INSTITUTION_INFO_4")
	String accountInstitutionInfo4;

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

	@Column(name = "REMITTER_INFO_1")
	String remitterInfo1;

	@Column(name = "REMITTER_INFO_2")
	String remitterInfo2;

	@Column(name = "REMITTER_INFO_3")
	String remitterInfo3;

	@Column(name = "REMITTER_INFO_4")
	String remitterInfo4;

	@Column(name = "CHARGE_TYPE")
	String chargeType;

	@Column(name = "SENDER_CHARGE_CURRENCY_1")
	String senderChargeCurrency1;

	@Column(name = "SENDER_CHARGE_1")
	BigDecimal senderCharge1;

	@Column(name = "SENDER_CHARGE_CURRENCY_2")
	String senderChargeCurrency2;

	@Column(name = "SENDER_CHARGE_2")
	BigDecimal senderCharge2;

	@Column(name = "SENDER_CHARGE_CURRENCY_3")
	String senderChargeCurrency3;

	@Column(name = "SENDER_CHARGE_3")
	BigDecimal senderCharge3;

	@Column(name = "NOTICE_1")
	String notice1;

	@Column(name = "NOTICE_2")
	String notice2;

	@Column(name = "NOTICE_3")
	String notice3;

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

	@Column(name = "RECEIVE_DATE")
	@DateTimeFormat(pattern = "yyyyMMdd")
	LocalDate receiveDate;

	@Column(name = "NST_VST_MK")
	String nstVstMk;

}
