package tw.com.fcb.lion.core.fp.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import tw.com.fcb.lion.core.fp.service.cmd.FPAccountCreateCmd;
import tw.com.fcb.lion.core.ir.repository.entity.FPCuster;

@Mapper
public interface FpAccountVoMapper {
	
	@Mapping(target = "fpcAccount" , source = "accountNo")
	@Mapping(target = "fpcCustomerId" , source = "customerIdno")
	@Mapping(target = "fpcValidCrcyCnt", defaultValue = "0")
	FPCuster toEntity(FPAccountCreateCmd cmd); 

}
