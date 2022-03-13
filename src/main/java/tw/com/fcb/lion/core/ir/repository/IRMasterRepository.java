package tw.com.fcb.lion.core.ir.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tw.com.fcb.lion.core.ir.repository.entity.IRMaster;

import java.util.List;

@Repository
public interface IRMasterRepository extends JpaRepository <IRMaster,Long> {

    List<IRMaster> findByBeAdvisingBranch(String beAdvisingBranch);
    
	//以外匯編號查詢匯入主檔
    @Query(name="findByirNo",nativeQuery = true,value = "select * from IR_MASTER where IR_NO = :irNo")
	IRMaster findByirNo(String irNo);
}
