package net.fashiongo.webadmin.data.entity.primary;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "TrendReport_Map_Candidate")
public class TrendReportMapCandidateEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CandidateID")
	private Integer candidateID;

	@Column(name = "TrendReportID")
	private int trendReportID;

	@Column(name = "ProductID")
	private int productID;

	@Column(name = "ListOrder")
	private Integer listOrder;
}
