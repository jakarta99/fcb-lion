package tw.com.fcb.lion.core.ir.web.cmd;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import tw.com.fcb.lion.core.ir.ChargeType;

@Data
@Schema(description = "匯入 SWIFT 資料")
public class SwiftMessageSaveCmd {

	Long id;
	
	@Schema(description = "swift 序號" )
	String seqNo;
	
	@Schema(description = "發電行 swift 代號" )
	String senderSwiftCode;
	
	@Schema(description = "發電行外匯編號 (20欄位)" )
	String referenceNo;
	
	LocalDate valueDate;
	
	String amount;
	
	String currency;
	
	String senderAccount;
	
	String senderInfo1;
	
	String senderInfo2;
	
	String senderInfo3;
	
	String senderInfo4;
	
	String receiverAccount;
	
	String receiverInfo1;
	
	String receiverInfo2;
	
	String receiverInfo3;
	
	String receiverInfo4;
	
	@Schema(description = "手續費負擔類型", example = "SHA, BEN, OUR")
	ChargeType chargeType;
	
	String chargeFeeAmount1;
	
	String chargeFeeCurrency1;
	
	String chargeFeeAmount2;
	
	String chargeFeeCurrency2;
	
	String chargeFeeAmount3;
	
	String chargeFeeCurrency3;
	
}
