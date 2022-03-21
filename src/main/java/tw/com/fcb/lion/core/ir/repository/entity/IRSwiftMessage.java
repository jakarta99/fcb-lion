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
	@Column(name = "SEQ_NO",length = 6,nullable = false)
	String seqNo;

//	處理狀態
	@Column(name = "STATS",length = 1,nullable = true)
	String stats;

//	處理時間
	@Column(name = "TX_TIME",length = 7)
	String txTime;

//	幣別
	@Column(name = "CURRENCY",length = 2)
	String currency;
	
//	匯入匯款金額 - 長度待確認NUMERIC(15,2)
	@Column(name = "IR_AMT",length = 17)
	BigDecimal irAmt;

//	匯款行匯出編號(匯票NO) 20欄位
	@Column(name = "REFERENCE_NO",length = 16)
	String referenceNo;

//	匯款人一
	@Column(name = "SENDER_INFO_1",length = 35)
	String senderInfo1;

//	匯款人二
	@Column(name = "SENDER_INFO_2",length = 35)
	String senderInfo2;

//	匯款人三
	@Column(name = "SENDER_INFO_3",length = 35)
	String senderInfo3;

//	匯款人四
	@Column(name = "SENDER_INFO_4",length = 35)
	String senderInfo4;

//	原始匯款行 TAG
	@Column(name = "ACCOUNT_INSTITUTION",length = 1)
	String accountInstitution;

//	原始匯款行帳號
	@Column(name = "ACCOUNT_INSTITUTION_AC",length = 35)
	String accountInstitutionAccount;

//	原始匯款行一
	@Column(name = "ACCOUNT_INSTITUTION_INFO_1",length = 35)
	String accountInstitutionInfo1;

//	原始匯款行二
	@Column(name = "ACCOUNT_INSTITUTION_INFO_2",length = 35)
	String accountInstitutionInfo2;

//	原始匯款行三
	@Column(name = "ACCOUNT_INSTITUTION_INFO_3",length = 35)
	String accountInstitutionInfo3;

//	原始匯款行四
	@Column(name = "ACCOUNT_INSTITUTION_INFO_4",length = 35)
	String accountInstitutionInfo4;

//	受款人往來銀行帳號
	@Column(name = "BENEFICIARY_AC",length = 35)
	String beneficiaryAccount;

//	受款人往來銀行一
	@Column(name = "BENEFICIARY_INFO_1",length = 35)
	String beneficiaryInfo1;

//	受款人往來銀行二
	@Column(name = "BENEFICIARY_INFO_2",length = 35)
	String beneficiaryInfo2;

//	受款人往來銀行三
	@Column(name = "BENEFICIARY_INFO_3",length = 35)
	String beneficiaryInfo3;

//	受款人往來銀行四
	@Column(name = "BENEFICIARY_INFO_4",length = 35)
	String beneficiaryInfo4;

//	受款人一
	@Column(name = "REMITTER_INFO_1",length = 35)
	String remitterInfo1;

//	受款人二
	@Column(name = "REMITTER_INFO_2",length = 35)
	String remitterInfo2;

//	受款人三
	@Column(name = "REMITTER_INFO_3",length = 35)
	String remitterInfo3;

//	受款人四
	@Column(name = "REMITTER_INFO_4",length = 35)
	String remitterInfo4;

//	費用明細
	@Column(name = "CHARGE_TYPE",length = 3)
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
	@Column(name = "NOTICE_1",length = 2)
	String notice1;

//	分行注意事項二
	@Column(name = "NOTICE_2",length = 2)
	String notice2;

//	分行注意事項三
	@Column(name = "NOTICE_3",length = 2)
	String notice3;

//	發電行 swift 代號
	@Column(name = "SENDER_SWIFT_CODE",length = 11)
	String senderSwiftCode;

//	匯款行一
	@Column(name = "REMIT_BANK_INFO1",length = 35)
	String remitBankInfo1;

//	匯款行二
	@Column(name = "REMIT_BANK_INFO2",length = 35)
	String remitBankInfo2;

//	匯款行三
	@Column(name = "REMIT_BANK_INFO3",length = 1)
	String remitBankInfo3;

//	匯款行四
	@Column(name = "REMIT_BANK_INFO4",length = 3)
	String remitBankInfo4;

//	存匯行 SWIFT-TID
	@Column(name = "DEPOSIT_BANK",length = 11)
	String depositBank;

//	有效日
	@Column(name = "VALUE_DATE",length = 8)
	@DateTimeFormat(pattern = "yyyyMMdd")
	LocalDate valueDate;

//	收件日做自動/單筆查詢印表時之日期
	@Column(name = "RECEIVE_DATE",length = 8)
	@DateTimeFormat(pattern = "yyyyMMdd")
	LocalDate receiveDate;

//	同存記號  V:同存帳務(VOSTRO)	F:海外聯行同存帳務	N:存同帳務(NOSTRO)
	@Column(name = "NST_VST_MK",length = 1)
	String nstVstMk;

}
