package tw.com.fcb.lion.core.ir.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "FXRATE_TABLE")
@Data
public class FXRateVo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "DATE")
	private String date;
	
	@Column(name = "CURRENCY")
	private String currency;
	
	@Column(name = "SPOT_BOUGH_FXRATE")
	private String spotBoughFxRate;
	
	@Column(name = "COST_SPOT_BOUGH_FXRATE")
	private String costSpotBoughFxRate;
	
	@Column(name = "COST_SPOT_SOLD_FXRATE")
	private String costSpotSoldFxRate;
	
	@Column(name = "SPOT_SOLD_FXRATE")
	private String spotSoldFxRate;
}
