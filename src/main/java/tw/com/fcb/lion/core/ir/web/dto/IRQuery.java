package tw.com.fcb.lion.core.ir.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "匯入匯款案件主檔查詢")
@Data
public class IRQuery {
    @Schema(description = "匯入匯款編號")
    String irNo;

    @Schema(description = "付款狀態")
    String paidStats;

    @Schema(description = "印製通知書記號")
    String printAdvisingMk;

    @Schema(description = "受通知單位")
    String beAdvisingBranch;

    @Schema(description = "處理單位")
    String processBranch;

    @Schema(description = "是否本行客戶(空白, Y, N)")
    String ourCustomer;

    @Schema(description = "客戶 ID")
    String customerId;

    @Schema(description = "幣別")
    String currency;

    @Schema(description = "匯入匯款金額")
    BigDecimal irAmt;


}
