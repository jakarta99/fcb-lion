package tw.com.fcb.lion.core.ir.repository;

import org.springframework.stereotype.Repository;

import tw.com.fcb.lion.core.ir.repository.entity.IRSwiftMessage;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IRSwiftMessageRepository extends JpaRepository<IRSwiftMessage, Long>{
	Optional<IRSwiftMessage> findBySeqNo(String seqNo);
}
