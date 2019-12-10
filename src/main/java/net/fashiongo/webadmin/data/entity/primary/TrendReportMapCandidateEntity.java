package net.fashiongo.webadmin.data.entity.primary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "TrendReport_Map_Candidate")
@Setter
public class TrendReportMapCandidateEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CandidateID")
	@Setter(AccessLevel.NONE)
	private Integer candidateID;

	@Column(name = "TrendReportID")
	private int trendReportID;

	@Column(name = "ProductID")
	private int productID;

	@Column(name = "ListOrder")
	private Integer listOrder;
}
