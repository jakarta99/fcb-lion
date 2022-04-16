package tw.com.fcb.lion.core.fp.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import tw.com.fcb.lion.core.fp.service.cmd.FPAccountCreateCmd;
import tw.com.fcb.lion.core.fp.service.vo.FPAccountVo;
import tw.com.fcb.lion.core.ir.repository.entity.FPCuster;

@Mapper
public interface FpAccountVoMapper {

	@Mapping(target = "fpcAccount" , source = "accountNo")
	@Mapping(target = "fpcCustomerId" , source = "customerIdno")
	@Mapping(target = "fpcStatus" , source = "status", defaultValue = "00")
	@Mapping(target = "fpcValidCrcyCnt", source = "validCrcyCnt", defaultValue = "0")
	FPCuster toEntity(FPAccountCreateCmd cmd);
	
	@Mapping(target = "accountNo" , source = "fpcAccount")
	@Mapping(target = "customerIdno" , source = "fpcCustomerId")
	@Mapping(target = "status" , source = "fpcStatus")
	@Mapping(target = "validCrcyCnt", source = "fpcValidCrcyCnt")
	FPAccountVo toVo (FPCuster fpCuster);
	

}
