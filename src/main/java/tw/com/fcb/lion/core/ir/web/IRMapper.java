package tw.com.fcb.lion.core.ir.web;


import org.mapstruct.Mapper;

import tw.com.fcb.lion.core.ir.repository.entity.IRMaster;
import tw.com.fcb.lion.core.ir.web.dto.IR;

@Mapper
public interface IRMapper {

	  IR toIR(IRMaster irMaster);
	
}
