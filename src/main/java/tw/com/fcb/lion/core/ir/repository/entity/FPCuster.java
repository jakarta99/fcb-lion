package tw.com.fcb.lion.core.ir.repository.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * 
 * FPC 帳號層資訊
 *
 */
@Entity
@Table(name = "FPCUSTOMER")
@Data
public class FPCuster {
	
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Id
	@Column(name = "FPC_ACCOUNT")
	private String fpcAccount;
	
	@Column(name = "FPC_CUSTOMER_ID")
	private String fpcCustomerId;
	
	@Column(name = "FPC_STATUS")
	private String fpcStatus;

	@Column(name = "FPC_VALID_CRCY_CNT")
	private String fpcValidCrcyCnt;
	
	@OneToMany(fetch = FetchType.LAZY)
	//@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "FPM_ACCOUNT",nullable = false)
	List<FPMaster> fpmasters;
	


}
