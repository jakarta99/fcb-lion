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
	
	@Column(name = "VALUE_DATE")
	LocalDate valueDate;
	
	@Column(name = "AMOUNT")
	BigDecimal amount;
	
	@Column(name = "CURRENCY")
	String currency;
	
	
	
	
	
}
