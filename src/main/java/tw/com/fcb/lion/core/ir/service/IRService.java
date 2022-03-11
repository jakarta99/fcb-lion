package tw.com.fcb.lion.core.ir.service;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.fcb.lion.core.ir.repository.IRSwiftMessageRepository;
import tw.com.fcb.lion.core.ir.repository.entity.IRSwiftMessage;
import tw.com.fcb.lion.core.ir.web.cmd.SwiftMessageSaveCmd;

@Transactional
@Service
public class IRService {

	@Autowired
	IRSwiftMessageRepository repository;
	
	public void insert(SwiftMessageSaveCmd saveCmd) {
		
		IRSwiftMessage entity = new IRSwiftMessage();
		
		BeanUtils.copyProperties(saveCmd, entity);
		
		repository.save(entity);
		
		
	}
	

}
