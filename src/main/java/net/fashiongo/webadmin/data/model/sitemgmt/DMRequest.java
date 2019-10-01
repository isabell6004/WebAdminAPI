package net.fashiongo.webadmin.data.model.sitemgmt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Getter
public class DMRequest {

	@JsonProperty("row")
	private BigInteger row;
	@JsonProperty("CatalogSendRequestID")
	private Integer catalogSendRequestID;
	@JsonProperty("CatalogSendQueueID")
	private Integer catalogSendQueueID;
	@JsonProperty("FGCatalogID")
	private Integer fGCatalogID;
	@JsonProperty("CatalogSortNo")
	private Integer catalogSortNo;
	@JsonProperty("CreatedOn")
	private LocalDateTime createdOn;
	@JsonProperty("ModifiedOn")
	private LocalDateTime modifiedOn;
	@JsonProperty("Active")
	private Boolean active;
	@JsonProperty("WholeSalerID")
	private Integer wholeSalerID;
	@JsonProperty("CompanyTypeCD")
	private String companyTypeCD;
	@JsonProperty("CompanyName")
	private String companyName;
	@JsonProperty("SentOn")
	private LocalDateTime sentOn;
	@JsonProperty("CatalogID")
	private Integer catalogID;
	@JsonProperty("UrlPath")
	private String urlPath;
	@JsonProperty("DirName")
	private String dirName;
	@JsonProperty("C1")
	private String c1;
	@JsonProperty("C2")
	private String c2;
	@JsonProperty("C3")
	private String c3;
	@JsonProperty("C4")
	private String c4;
	@JsonProperty("TotalCount")
	private Integer totalCount;

	public DMRequest(Long row, Integer catalogSendRequestID, Integer catalogSendQueueID, Integer fGCatalogID, Integer catalogSortNo, Timestamp createdOn, Timestamp modifiedOn, Boolean active, Integer wholeSalerID, String companyTypeCD, String companyName, Timestamp sentOn, Integer catalogID, String urlPath, String dirName, String c1, String c2, String c3, String c4, Long totalCount) {
		this.row = Optional.ofNullable(row).map(value -> new BigInteger(value.toString())).orElse(null);
		this.catalogSendRequestID = catalogSendRequestID;
		this.catalogSendQueueID = catalogSendQueueID;
		this.fGCatalogID = fGCatalogID;
		this.catalogSortNo = catalogSortNo;
		this.createdOn = Optional.ofNullable(createdOn).map(Timestamp::toLocalDateTime).orElse(null);
		this.modifiedOn = Optional.ofNullable(modifiedOn).map(Timestamp::toLocalDateTime).orElse(null);;
		this.active = active;
		this.wholeSalerID = wholeSalerID;
		this.companyTypeCD = companyTypeCD;
		this.companyName = companyName;
		this.sentOn = Optional.ofNullable(sentOn).map(Timestamp::toLocalDateTime).orElse(null);
		this.catalogID = catalogID;
		this.urlPath = urlPath;
		this.dirName = dirName;
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.c4 = c4;
		this.totalCount = Optional.ofNullable(totalCount).map(Long::intValue).orElse(null);
	}
}
