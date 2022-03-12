package tw.com.fcb.lion.core.ir.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import tw.com.fcb.lion.core.ir.ChargeType;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "匯入匯款案件主檔")
@Data
public class IR {
    @Schema(description = "匯入匯款編號")
    String irNo;

    @Schema(description = "匯款性質")
    Integer natureOfRemittance;

    @Schema(description = "匯款細分類")
    Integer natureOfRemittanceSubCode;

    @Schema(description = "付款狀態")
    Integer paidStats;

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

    @Schema(description = "外匯存款金額")
    BigDecimal fxDeposit;

    @Schema(description = "即期結匯金額")
    BigDecimal spotSettledFx;

    @Schema(description = "手續費支出")
    BigDecimal commCharge;

    @Schema(description = "折合美金匯率")
    BigDecimal toUsFxrate;

    @Schema(description = "匯款行匯出編號(匯票NO)")
    String senderReference;

    @Schema(description = "匯款人一")
    String remitterInfo1;

    @Schema(description = "匯款人二")
    String remitterInfo2;

    @Schema(description = "匯款人三")
    String remitterInfo3;

    @Schema(description = "匯款人四")
    String remitterInfo4;

    @Schema(description = "受款人帳號")
    String beneficiaryAccount;

    @Schema(description = "受款人一")
    String beneficiaryInfo1;

    @Schema(description = "受款人二")
    String beneficiaryInfo2;

    @Schema(description = "受款人三")
    String beneficiaryInfo3;

    @Schema(description = "受款人四")
    String beneficiaryInfo4;

    @Schema(description = "費用明細(SHR,BEN,OUR)")
    ChargeType chargeType;

    @Schema(description = "匯款行 SWIFT_TID")
    String remitBank;

    @Schema(description = "匯款行名稱一")
    String remitBankInfo1;

    @Schema(description = "匯款行名稱二")
    String remitBankInfo2;

    @Schema(description = "匯款行名稱一")
    String remitBankInfo3;

    @Schema(description = "匯款行名稱二")
    String remitBankInfo4;

    @Schema(description = "存匯行 SWIFT_TID")
    String depositBank;

    @Schema(description = "有效日")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate valueDate;

    @Schema(description = "通知日")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate advisingDate;

    @Schema(description = "印製通知書日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate printAdvisingDate;

    @Schema(description = "付款日")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate processDate;

    @Schema(description = "客戶電話號碼")
    String customerTelNo;

    @Schema(description = "SW59_AC受款人帳號")
    String accountNo;

    @Schema(description = "匯率")
    BigDecimal exchangeRate;

    @Schema(description = "受款人身份別")
    String beneficiaryKind;

    @Schema(description = "受款人出生日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate customerBirthDate;

    @Schema(description = "分行注意事項一")
    String notice1;

    @Schema(description = "分行注意事項二")
    String notice2;

    @Schema(description = "分行注意事項三")
    String notice3;
}
