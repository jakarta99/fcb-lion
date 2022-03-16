package tw.com.fcb.lion.core.ir.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.fcb.lion.core.ir.repository.FXRateRepository;
import tw.com.fcb.lion.core.ir.repository.IRMasterRepository;
import tw.com.fcb.lion.core.ir.repository.IRSwiftMessageRepository;
import tw.com.fcb.lion.core.ir.repository.entity.FXRateVo;
import tw.com.fcb.lion.core.ir.repository.entity.IRMaster;
import tw.com.fcb.lion.core.ir.repository.entity.IRSwiftMessage;
import tw.com.fcb.lion.core.ir.web.cmd.IRSaveCmd;
import tw.com.fcb.lion.core.ir.web.cmd.SwiftMessageSaveCmd;
import tw.com.fcb.lion.core.ir.web.dto.IR;

@Transactional
@Service
public class IRService {

	@Autowired
	IRSwiftMessageRepository repository;

	@Autowired
	IRMasterRepository IRMasterRepository;
	
	@Autowired
	private FXRateRepository fxRateRepository;
	
	public IRService(FXRateRepository fxRateRepository) {
		this.fxRateRepository = fxRateRepository;
		
		this.fxRateRepository.saveAll(List.of(
				new FXRateVo("2022/03/14", "USD", "28.47600", "28.51800", "28.53400", "28.57600"),
				new FXRateVo("2022/03/14", "JPY", "0.24000", "0.24170", "0.24250", "0.24400")
		));
	} 
	
	public void insert(SwiftMessageSaveCmd saveCmd) {
		IRSwiftMessage entity = new IRSwiftMessage();
		BeanUtils.copyProperties(saveCmd, entity);
		repository.save(entity);
	}

	//傳入受通知單位查詢案件數
	public Integer getIrCaseCount(String branch) {
		Integer count = 0;
		count = IRMasterRepository.findByBeAdvisingBranch(branch).size();
		return count;
	}
	
	// 檢核成功，新增資料至IRMASTER
	public void insertIrMaster(IRSaveCmd irSaveCmd) {
		var checkIrMasterMk = checkIrMaster(irSaveCmd);
		
		if(checkIrMasterMk == "Y") {
			IRMaster entityCmd = new IRMaster();
			BeanUtils.copyProperties(irSaveCmd, entityCmd);
			IRMasterRepository.save(entityCmd);
		}
	}
	
	// 檢核主檔資料
	public String checkIrMaster(IRSaveCmd irSaveCmd) {
		var checkIrMasterMk = "Y";
		
		String[] branchs = {"091", "093", "094"};
		String[] currencys = {"USD", "GBP", "JPY"};
		String[] customers = {"86483666", "05052322", "03218306"};
		
		try {
			var adversingBranch = irSaveCmd.getBeAdvisingBranch();
			if(!Arrays.asList(branchs).contains(adversingBranch)) {
				throw new Exception("分行別錯誤");
			}
			
			var currency  = irSaveCmd.getCurrency();
			if(!Arrays.asList(currencys).contains(currency)) {
				throw new Exception("幣別錯誤");
			}
			else {
				FXRateVo fxRateVo = fxRateRepository.findByCurrency(currency);
				irSaveCmd.setExchangeRate(fxRateVo.getCostSpotBoughFxRate());
			}
			
			var customerId = irSaveCmd.getCustomerId();
			if(!Arrays.asList(customers).contains(customerId)) {
				throw new Exception("統編錯誤");
			}
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			checkIrMasterMk = "N";
		}
		finally {
		}
		return checkIrMasterMk;
	}
	
	//傳入ID查詢內容
		public Optional<IRMaster> getById(Long id) {
			Optional<IRMaster> iRMaster = IRMasterRepository.findById(id);
			System.out.println(iRMaster);
			return iRMaster;
		}
	
	//更新印製通知書記號
	public void updatePrintAdviceMark(Long id) {
		 var iRMaster = IRMasterRepository.findById(id).orElseThrow();
		 System.out.println("iRMaster " +iRMaster);
		 iRMaster.setPrintAdvisingMk("Y");
		 IRMasterRepository.save(iRMaster);
	}
	
	//傳入外匯編號，執行匯入解款
	public void settle(String irNo) {
		IRMaster irMaster = new IRMaster();
		irMaster = IRMasterRepository.findByIrNo(irNo);
		irMaster.setPaidStats("2");
		IRMasterRepository.save(irMaster);
	}
}
