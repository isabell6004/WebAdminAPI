package net.fashiongo.webadmin.data.model.franchise;

import lombok.Getter;

@Getter
public class GetFranchiseMasterSubAccountParameter {
	private Integer masterAccountId;
	private Integer searchTypeId;
	private Integer countryCode;
	private String searchTxt;
	private String state;
	private String fromDate;
	private String toDate;
	private Integer pn;
	private Integer ps;
	private String orderBy;
}
