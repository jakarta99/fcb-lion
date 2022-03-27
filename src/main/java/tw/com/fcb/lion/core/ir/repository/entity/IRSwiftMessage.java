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

//	匯入 swift 序號
	@Column(name = "SEQ_NO")
	String seqNo;

//	處理狀態
	@Column(name = "STATS")
	String stats;

//	處理時間
	@Column(name = "TX_TIME")
	String txTime;

//	幣別
	@Column(name = "CURRENCY")
	String currency;
	
//	匯入金額
	@Column(name = "IR_AMT")
	BigDecimal irAmt;

//	匯款行匯出編號(匯票NO) 20欄位
	@Column(name = "REFERENCE_NO")
	String referenceNo;

//	原始匯款行 SWIFT-TID
	@Column(name = "ORIGIN_REMIT_SWIFT_TID")
	String originRemitSwiftCode;
	
//	原始匯款行一
	@Column(name = "ORIGIN_REMIT_BANK_INFO_1")
	String originRemitBankInfo1;
	
//	原始匯款行二
	@Column(name = "ORIGIN_REMIT_BANK_INFO_2")
	String originRemitBankInfo2;
	
//	原始匯款行三
	@Column(name = "ORIGIN_REMIT_BANK_INFO_3")
	String originRemitBankInfo3;
	
//	原始匯款行四
	@Column(name = "ORIGIN_REMIT_BANK_INFO_4")
	String originRemitBankInfo4;
	
//	受款人設帳銀行 SWIFT-TID
	@Column(name = "ACCOUNT_SWIFT_TID")
	String accountSwiftCode;

//	設帳行一
	@Column(name = "ACCOUNT_BANK_INFO_1")
	String accountBankInfo1;

//	設帳行二
	@Column(name = "ACCOUNT_BANK_INFO_2")
	String accountBankInfo2;

//	設帳行三
	@Column(name = "ACCOUNT_BANK_INFO_3")
	String accountBankInfo3;

//	設帳行四
	@Column(name = "ACCOUNT_BANK_INFO_4")
	String accountBankInfo4;

//	受款人帳號
	@Column(name = "BENEFICIARY_AC")
	String beneficiaryAccount;

//	受款人一
	@Column(name = "BENEFICIARY_INFO_1")
	String beneficiaryInfo1;

//	受款人二
	@Column(name = "BENEFICIARY_INFO_2")
	String beneficiaryInfo2;

//	受款人三
	@Column(name = "BENEFICIARY_INFO_3")
	String beneficiaryInfo3;

//	受款人四
	@Column(name = "BENEFICIARY_INFO_4")
	String beneficiaryInfo4;

//	匯款人一
	@Column(name = "REMITTER_INFO_1")
	String remitterInfo1;

//	匯款人二
	@Column(name = "REMITTER_INFO_2")
	String remitterInfo2;

//	匯款人三
	@Column(name = "REMITTER_INFO_3")
	String remitterInfo3;

//	匯款人四
	@Column(name = "REMITTER_INFO_4")
	String remitterInfo4;

//	手續費負擔類型
	@Column(name = "CHARGE_TYPE")
	String chargeType;

//	中間行費用_幣別1
	@Column(name = "SENDER_CHARGE_CURRENCY_1")
	String senderChargeCurrency1;

//	中間行費用_金額1
	@Column(name = "SENDER_CHARGE_1")
	BigDecimal senderCharge1;

//	中間行費用_幣別2
	@Column(name = "SENDER_CHARGE_CURRENCY_2")
	String senderChargeCurrency2;

//	中間行費用_金額2
	@Column(name = "SENDER_CHARGE_2")
	BigDecimal senderCharge2;

//	中間行費用_幣別3
	@Column(name = "SENDER_CHARGE_CURRENCY_3")
	String senderChargeCurrency3;

//	中間行費用_金額3
	@Column(name = "SENDER_CHARGE_3")
	BigDecimal senderCharge3;

//	分行注意事項一
	@Column(name = "NOTICE_1")
	String notice1;

//	分行注意事項二
	@Column(name = "NOTICE_2")
	String notice2;

//	分行注意事項三
	@Column(name = "NOTICE_3")
	String notice3;

//	匯款行 SWIFT-TID
	@Column(name = "REMIT_SWIFT_TID")
	String remitSwiftCode;

//	匯款行一
	@Column(name = "REMIT_BANK_INFO_1")
	String remitBankInfo1;

//	匯款行二
	@Column(name = "REMIT_BANK_INFO_2")
	String remitBankInfo2;

//	匯款行三
	@Column(name = "REMIT_BANK_INFO_3")
	String remitBankInfo3;

//	匯款行四
	@Column(name = "REMIT_BANK_INFO_4")
	String remitBankInfo4;

//	存匯行 SWIFT-TID
	@Column(name = "DEPOSIT_SWIFT_TID")
	String depositSwiftCode;

//	有效日
	@Column(name = "VALUE_DATE")
	@DateTimeFormat(pattern = "yyyyMMdd")
	LocalDate valueDate;

//	收件日做自動/單筆查詢印表時之日期
	@Column(name = "RECEIVE_DATE")
	@DateTimeFormat(pattern = "yyyyMMdd")
	LocalDate receiveDate;

//	同存記號  V:同存帳務(VOSTRO)	F:海外聯行同存帳務	N:存同帳務(NOSTRO)
	@Column(name = "NST_VST_MK",length = 1)
	String nstVstMk;

}
