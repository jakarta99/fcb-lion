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

	@Column(name = "SW20")
	String sw20;

	@Column(name = "SW50-1-1-1")
	String sw50111;

	@Column(name = "SW50-1-1-2")
	String sw50112;

	@Column(name = "SW50-2")
	String sw502;

	@Column(name = "SW50-3")
	String sw503;

	@Column(name = "SW50-4")
	String sw504;

	@Column(name = "TAG-SW52")
	String tagSw52;

	@Column(name = "SW52-AC")
	String sw52ac;

	@Column(name = "SW52D-1")
	String sw52d1;

	@Column(name = "SW52D-2")
	String sw52d2;

	@Column(name = "SW52D-3")
	String sw52d3;

	@Column(name = "SW52D-4")
	String sw52d4;

	@Column(name = "TAG-SW57")
	String tagsw57;

	@Column(name = "SW57-AC")
	String sw57ac;

	@Column(name = "SW57D-1")
	String sw57d1;

	@Column(name = "SW57D-2")
	String sw57d2;

	@Column(name = "SW57D-3")
	String sw57d3;

	@Column(name = "SW57D-4")
	String sw57d4;

	@Column(name = "SW59-AC-1")
	String sw59ac1;

	@Column(name = "SW59-AC-2")
	String sw59ac2;

	@Column(name = "SW59-1")
	String sw591;

	@Column(name = "SW59-2")
	String sw592;

	@Column(name = "SW59-3")
	String sw593;

	@Column(name = "SW59-4")
	String sw594;

	@Column(name = "SW71A")
	String sw71a;

	@Column(name = "SW72-1")
	String sw721;

	@Column(name = "SW72-2")
	String sw722;

	@Column(name = "SW72-3")
	String sw723;

	@Column(name = "NOTICE-1")
	String notice1;

	@Column(name = "NOTICE-2")
	String notice2;

	@Column(name = "NOTICE-3")
	String notice3;

	@Column(name = "REMIT-BANK")
	String remitBank;

	@Column(name = "REMIT-BK-INFO1")
	String remitBkInfo1;

	@Column(name = "REMIT-BK-INFO2")
	String remitBkInfo2;

	@Column(name = "REMIT-BK-INFO3")
	String remitBkInfo3;

	@Column(name = "REMIT-BK-INFO4")
	String remitBkInfo4;

	@Column(name = "DEPOSIT-BANK-HEAD")
	String depositBankHead;

	@Column(name = "DEPOSIT-BANK-BRAN")
	String depositBankBran;

	@Column(name = "VALUE-DATE")
	String valueDate;

	@Column(name = "RECV-DATE")
	String recvDate;

	@Column(name = "NST-VST-MK")
	String nstVstMk;

}
