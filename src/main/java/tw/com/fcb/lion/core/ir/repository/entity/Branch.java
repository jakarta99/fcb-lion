package tw.com.fcb.lion.core.ir.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "BRANCH_TABLE")
@Data
public class Branch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "BRANCH_CODE")
	private String branchCode;
	
	@Column(name = "BRANCH_NAME")
	private String branchName;
	
	@Column(name = "BRANCH_TRACK")
	private String branchTrack;
	
	@Column(name = "BRANCH_SERIAL_NO")
	private Integer branchSerialNo;
}
