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
	
	@Column(name = "IR-MARK")
	String irMark;
	
	@Column(name = "PAID-STATS")        
	String paidStats;
	
	@Column(name = "PRINT-ADV-MK")     
	String printAdvMk;
	
	@Column(name = "BE-ADV-BRANCH")   
	String beAdvBranch;
	
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

	@Column(name = "SW20")
	String sw20;

	@Column(name = "SW50-1")           
	String sw501;

	@Column(name = "SW50-2")         
	String sw502;

	@Column(name = "SW50-3")         
	String sw503;

	@Column(name = "SW50-4")         
	String sw504;

	@Column(name = "SW59-AC")         
	String sw59Ac;

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

	@Column(name = "REMIT-BANK")
	String remitBank;

	@Column(name = "REMIT-BK-NAME1")     
	String remitBkName1;

	@Column(name = "REMIT-BK-NAME2") 
	String remitBkName2;

	@Column(name = "DEPOSIT-BANK") 
	String depositBank;

	@Column(name = "VALUE-DATE")   
	String valueDate;

	@Column(name = "ADV-DATE")     
	String advDate;

	@Column(name = "PRINT-ADV-DATE")       
	String printAdvDate;

	@Column(name = "PPOCESS-DATE") 
	String pocessDate;

	@Column(name = "CUST-TEL-NO")   
	String custTelNo;

	@Column(name = "AC-NO")    
	String acNo;

	@Column(name = "EXCHANGE-RATE")          
	String exchangeRate;

	@Column(name = "SW71F-CUR-1")  
	String sw71fCur1;

	@Column(name = "SW71F-1")    
	String sw71f1;

	@Column(name = "SW71F-CUR-2")        
	String sw71fCur2;

	@Column(name = "SW71F-2")    
	String sw71f2;

	@Column(name = "SW71F-CUR-3")        
	String sw71fCur3;

	@Column(name = "SW71F-3")    
	String sw71f3;

	@Column(name = "BENE-KIND")        
	String beneKind;

	@Column(name = "690-SUB-CODE")      
	String SubCode690;

	@Column(name = "CUS-BIRTH-DATE")   
	String cusBirthDate;

	@Column(name = "NOTICE-1") 
	String notice1;

	@Column(name = "NOTICE-2")       
	String notice2;

	@Column(name = "NOTICE-3")       
	String notice3;



}
