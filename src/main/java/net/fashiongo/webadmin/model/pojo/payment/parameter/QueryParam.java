package net.fashiongo.webadmin.model.pojo.payment.parameter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class QueryParam implements Serializable{
private static final long serialVersionUID = 1L;

	private static final Integer DEFAULT_PAGE_NUM = 1;
	private static final Integer DEFAULT_PAGE_SIZE = 10;

	/* WholeSalerID */
	private Integer wid;
	public Integer getWid() {
		return wid;
	}
	public void setWid(Integer wid) {
		this.wid = wid;
	}

	/* RetailerID */
	private Integer rid;
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}

	/* OrderID */
	private Integer oid;
	public Integer getOid() {
		return oid;
	}
	public void setOid(Integer oid) {
		this.oid = oid;
	}

	/* OrderStatusID */
	private Integer osid;
	public Integer getOsid() {
		return osid;
	}
	public void setOsid(Integer osid) {
		this.osid = osid;
	}

	/* Search Date Target "PhotoshootDate", "OrderDate", "DropOffDate" */
	private String dtype;
	public String getDtype() {
		return dtype;
	}
	public void setDtype(String dtype) {
		this.dtype = dtype;
	}

	/* Date From */
	private Date df;
	public Date getDf() {
		return df;
	}
	public void setDf(Date df) {
		this.df = df;
	}

	/* Date To */
	private Date dt;
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}

	/* Company Name */
	private String cn;
	public String getCn() {
		return cn;
	}
	public void setCn(String cn) {
		this.cn = cn;
	}

	/* PONumber */
	private String po;
	public String getPo() {
		return po;
	}
	public void setPo(String po) {
		this.po = po;
	}

	/* Product Name (StyleNo) */
	private String productName;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/* Order By */
	private String orderBy;
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/* Order By for Order List */
	private String orderBy1;
	public String getOrderBy1() {
		return orderBy1;
	}
	public void setOrderBy1(String orderBy1) {
		this.orderBy1 = orderBy1;
	}

	private String orderBy2;
	public String getOrderBy2() {
		return orderBy2;
	}
	public void setOrderBy2(String orderBy2) {
		this.orderBy2 = orderBy2;
	}

	private String orderBy3;
	public String getOrderBy3() {
		return orderBy3;
	}
	public void setOrderBy3(String orderBy3) {
		this.orderBy3 = orderBy3;
	}

	private String orderBy5;
	public String getOrderBy5() {
		return orderBy5;
	}
	public void setOrderBy5(String orderBy5) {
		this.orderBy5 = orderBy5;
	}

	private String orderBy6;
	public String getOrderBy6() {
		return orderBy6;
	}
	public void setOrderBy6(String orderBy6) {
		this.orderBy6 = orderBy6;
	}

	private String orderBy7;
	public String getOrderBy7() {
		return orderBy7;
	}
	public void setOrderBy7(String orderBy7) {
		this.orderBy7 = orderBy7;
	}

	/* Search Keyword */
	private String q;
	public String getQ() {
		return q;
	}
	public void setQ(String query) {
		q = query;
	}

	/* Search Keyword Target */
	private String qt;
	public String getQt() {
		return qt;
	}
	public void setQt(String qt) {
		this.qt = qt;
	}
	/* userID */
	private String uid;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}

	/* (FashionGo) CategoryID */
	private Integer cid;
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}

	/* Vendor Category ID */
	private Integer vcid;
	public Integer getVcid() {
		return vcid;
	}
	public void setVcid(Integer vcid) {
		this.vcid = vcid;
	}

	/* Price From */
	private BigDecimal pf;
	public BigDecimal getPf() {
		return pf;
	}
	public void setPf(BigDecimal pf) {
		this.pf = pf;
	}

	/* Price To */
	private BigDecimal pt;
	public BigDecimal getPt() {
		return pt;
	}
	public void setPt(BigDecimal pt) {
		this.pt = pt;
	}

	/* Pre Order From */
	private Date pdf;
	public Date getPdf() {
		return pdf;
	}
	public void setPdf(Date pdf) {
		this.pdf = pdf;
	}

	/* Pre Order To */
	private Date pdt;
	public Date getPdt() {
		return pdt;
	}
	public void setPdt(Date pdt) {
		this.pdt = pdt;
	}

	/* sale */
	private Boolean isSale;
	public Boolean getIsSale() {
		return isSale;
	}
	public void setIsSale(Boolean isSale) {
		this.isSale = isSale;
	}

	/* in stock */
	private Boolean inStock;
	public Boolean getInStock() {
		return inStock;
	}
	public void setInStock(Boolean inStock) {
		this.inStock = inStock;
	}

	/* BodySizeID */
	private Integer bsid;
	public Integer getBsid() {
		return bsid;
	}
	public void setBsid(Integer bsid) {
		this.bsid = bsid;
	}

	/* Page Number */
	private Integer pn;
	public Integer getPn() {
		return pn;
	}
	public void setPn(Integer pn) {
		this.pn = pn;
	}

	/* Page Number for Order List */
	private Integer pn1;
	public Integer getPn1() {
		return pn1;
	}
	public void setPn1(Integer pn1) {
		this.pn1 = pn1;
	}

	private Integer pn2;
	public Integer getPn2() {
		return pn2;
	}
	public void setPn2(Integer pn2) {
		this.pn2 = pn2;
	}

	private Integer pn3;
	public Integer getPn3() {
		return pn3;
	}
	public void setPn3(Integer pn3) {
		this.pn3 = pn3;
	}

	private Integer pn5;
	public Integer getPn5() {
		return pn5;
	}
	public void setPn5(Integer pn5) {
		this.pn5 = pn5;
	}

	private Integer pn6;
	public Integer getPn6() {
		return pn6;
	}
	public void setPn6(Integer pn6) {
		this.pn6 = pn6;
	}

	private Integer pn7;
	public Integer getPn7() {
		return pn7;
	}
	public void setPn7(Integer pn7) {
		this.pn7 = pn7;
	}

	private Integer pn999;
	public Integer getPn999() {
		return pn999;
	}
	public void setPn999(Integer pn999) {
		this.pn999 = pn999;
	}

	/* Page Size */
	private Integer ps;
	public Integer getPs() {
		return ps;
	}
	public void setPs(Integer ps) {
		this.ps = ps;
	}

	/* Page Size for Order List */
	private Integer ps1;
	public Integer getPs1() {
		return ps1;
	}
	public void setPs1(Integer ps1) {
		this.ps1 = ps1;
	}

	private Integer ps2;
	public Integer getPs2() {
		return ps2;
	}
	public void setPs2(Integer ps2) {
		this.ps2 = ps2;
	}

	private Integer ps3;
	public Integer getPs3() {
		return ps3;
	}
	public void setPs3(Integer ps3) {
		this.ps3 = ps3;
	}

	private Integer ps5;
	public Integer getPs5() {
		return ps5;
	}
	public void setPs5(Integer ps5) {
		this.ps5 = ps5;
	}

	private Integer ps6;
	public Integer getPs6() {
		return ps6;
	}
	public void setPs6(Integer ps6) {
		this.ps6 = ps6;
	}

	private Integer ps7;
	public Integer getPs7() {
		return ps7;
	}
	public void setPs7(Integer ps7) {
		this.ps7 = ps7;
	}

	private Integer ps999;
	public Integer getPs999() {
		return ps999;
	}
	public void setPs999(Integer ps999) {
		this.ps999 = ps999;
	}

	/* for print */
	private Boolean pr;
	public Boolean getPr() {
		return pr;
	}
	public void setPr(Boolean pr) {
		this.pr = pr;
	}

	/* Ship Track Number */
	private String trackNo;
	public String getTrackNo() {
		return trackNo;
	}
	public void setTrackNo(String trackNo) {
		this.trackNo = trackNo;
	}

	/* Active */
	private Boolean active;
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}

	/* Send email or not */
	private Boolean sendEmail;
	public Boolean getSendEmail() {
		return sendEmail;
	}
	public void setSendEmail(Boolean sendEmail) {
		this.sendEmail = sendEmail;
	}

	/* Used for Retailer Order Stat */
	private Boolean	me;
	public Boolean getMe() {
		return me;
	}
	public void setMe(Boolean me) {
		this.me = me;
	}

	/* Score */
	private Integer score;
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}

	/* Permission */
	private String perm;
	public String getPerm() {
		return perm;
	}
	public void setPerm(String perm) {
		this.perm = perm;
	}

	/* Market ID */
	private Integer market;
	public Integer getMarket() {
		return market;
	}
	public void setMarket(Integer market) {
		this.market = market;
	}

	/* Country ID */
	private Integer loc;
	public Integer getLoc() {
		return loc;
	}
	public void setLoc(Integer loc) {
		this.loc = loc;
	}

	/* Year */
	private Integer year;
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}

	/* Month */
	private Integer month;
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}

	/* AdID */
	private Integer adId;
	public Integer getAdId() {
		return adId;
	}
	public void setAdId(Integer adId) {
		this.adId = adId;
	}

	/* PageID */
	private Integer pageId;
	public Integer getPageId() {
		return pageId;
	}
	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	/* SpotID */
	private Integer spotId;
	public Integer getSpotId() {
		return spotId;
	}
	public void setSpotId(Integer spotId) {
		this.spotId = spotId;
	}

	/* Comma Separated Date String - "20170507,20170508,20170509" */
	private String dts;
	public String getDts() {
		return dts;
	}
	public void setDts(String dts) {
		this.dts = dts;
	}

	/* Comma Separated BidID String - "234,236,237" */
	private String bidIds;
	public String getBidIds() {
		return bidIds;
	}
	public void setBidIds(String bidIds) {
		this.bidIds = bidIds;
	}

	private String reason;
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}

	/* Status - 'A':All, 'B':BuyItNow, 'P':PlaceBid, 'W':BidWinnerOnly */
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	/* Days of Week - 0:All, 1:Sunday ~ 7:Saturday */
	private Integer dow;
	public Integer getDow() {
		return dow;
	}
	public void setDow(Integer dow) {
		this.dow = dow;
	}

	private String companyName;
	public String getCompanyName() {
		// TODO Auto-generated method stub
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	private String invoceNumber;
	public String getInvoceNumber() {
		return invoceNumber;
	}
	public void setInvoceNumber(String invoceNumber) {
		this.invoceNumber = invoceNumber;
	}

	private Integer type;
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

	private Integer typeId;
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	// FastEdit
	private Boolean raw;
	public Boolean getRaw() {
		return raw;
	}
	public void setRaw(Boolean raw) {
		this.raw = raw;
	}

	// Customers and Customer Group
	private String groupName;
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	private Integer groupId;
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	private String firstName;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	private String lastName;
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	// Banners
	private String approval;
	public String getApproval() {
		return approval;
	}
	public void setApproval(String approval) {
		this.approval = approval;
	}

	// item detail: navi
	private String listKey;
	public String getListKey() {
		return listKey;
	}
	public void setListKey(String listKey) {
		this.listKey = listKey;
	}

	public boolean verifyRequestParam() {
		if(this.wid == null) return false;

		if(pn == null || pn <= 0) pn = DEFAULT_PAGE_NUM;
		if(ps == null || ps <= 0) ps = DEFAULT_PAGE_SIZE;

		return true;
		/*
		if(!StringUtils.isEmpty(this.wid)) {
			try {
				this.intWid = Integer.parseInt(this.wid);
			}
			catch(Exception e) {
				return false;
			}
		} else {
			return false;
		}
		if(!StringUtils.isEmpty(this.osid)) {
			try {
				this.intOsid = Integer.parseInt(this.osid);
			}
			catch(Exception e) {
				return false;
			}
		}

		if(!StringUtils.isEmpty(this.pn)) {
			try {
				this.intPn = Integer.parseInt(this.pn);
			}
			catch(Exception e) {
				return false;
			}
		} else {
			this.pn = DEFAULT_PAGE_NUM.toString();
			this.intPn = DEFAULT_PAGE_NUM;
		}

		if(!StringUtils.isEmpty(this.ps)) {
			try {
				this.intPs = Integer.parseInt(this.ps);
			}
			catch(Exception e) {
				return false;
			}
		} else {
			this.ps = DEFAULT_PAGE_SIZE.toString();
			this.intPs = DEFAULT_PAGE_SIZE;
		}

		if(!StringUtils.isEmpty(this.df)) {
			try {
				this.dateDf = Date.valueOf(this.df);
			}
			catch(Exception e) {
				return false;
			}
		}

		if(!StringUtils.isEmpty(this.dt)) {
			try {
				this.dateDt = Date.valueOf(this.dt);
			}
			catch(Exception e) {
				return false;
			}
		}

		return true;
		*/
	}

	//[Angel] 5/4/2017
	//Email Marketing Query Parameters

	//Email Lists
	/* EmailListType - '0':All, '1':Fixed, '2':Dynamic */
	private String listTypeId;
	public String getlistTypeId() {
		return listTypeId;
	}
	public void setlistTypeId(String listTypeId) {
		this.listTypeId = listTypeId;
	}

	private String emailListName;
	public String getEmailListName(){
		return emailListName;
	}
	public void setEmailListName(String emailListName){
		this.emailListName = emailListName;
	}

	private String campaignName;
	public String getCampaignName(){
		return campaignName;
	}
	public void setCampaignName(String campaignName){
		this.campaignName = emailListName;
	}

	/* Campaign Id */
	private Integer campaignId;
	public Integer getCampaignId() {
		return campaignId;
	}
	public void setCampaignId(Integer campaignId) {
		this.campaignId = campaignId;
	}

	/* Email List Id */
	private Integer listId;
	public Integer getListId() {
		return listId;
	}
	public void setListId(Integer listId) {
		this.listId = listId;
	}

	/* Member Option: 1 = All, 2 = Subscribed, 3 = Not Subscribed */
	private Integer memberOpt;
	public Integer getMemberOpt() {
		return memberOpt;
	}
	public void setMemberOpt(Integer memberOpt) {
		this.memberOpt = memberOpt;
	}

	/* Search By Opt: 1 = Company Name, 2 = Email, 3 = First Name, 4 = Last Name, 5 = Phone Number */
	private Integer searchByOpt;
	public Integer getSearchByOpt() {
		return searchByOpt;
	}
	public void setSearchByOpt(Integer searchByOpt) {
		this.searchByOpt = searchByOpt;
	}

	private Integer statusTypeId;
	public Integer getStatusTypeId() {
		return statusTypeId;
	}
	public void setStatusTypeId(Integer statusTypeId) {
		this.statusTypeId = statusTypeId;
	}

	private Integer searchByTypeId;
	public Integer getSearchByTypeId() {
		return searchByTypeId;
	}
	public void setSearchByTypeId(Integer searchByTypeId) {
		this.searchByTypeId = searchByTypeId;
	}

	private String searchBy;
	public String getSearchBy() {
		return searchBy;
	}

	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}

	private String backUrl;
	public String getBackUrl() {
		return backUrl;
	}
	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}
	private String img;
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}

	private Integer shipStatus;
	public Integer getShipStatus() {
		return shipStatus;
	}
	public void setShipStatus(Integer shipStatus) {
		this.shipStatus = shipStatus;
	}

	private Integer subOsid;
	public Integer getSubOsid() {
		return subOsid;
	}
	public void setSubOsid(Integer subOsid) {
		this.subOsid = subOsid;
	}

	private Integer paymentStatus;
	public Integer getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	private Boolean isMerged;
	public Boolean getIsMerged() {
		return isMerged;
	}
	public void setIsMerged(Boolean isMerged) {
		this.isMerged = isMerged;
	}

	@Override
	public String toString() {
		try {
			//return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
			return new ObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return ReflectionToStringBuilder.toString(this);
		}
	}

	/* category id list*/
	private String catids;
	public String getCatids() {
		return catids;
	}
	public void setCatids(String catids) {
		this.catids = catids;
	}

	/* order status id list*/
	private String ostsids;
	public String getOstsids() {
		return ostsids;
	}
	public void setOstsids(String ostsids) {
		this.ostsids = ostsids;
	}

	//End

	//for model search start
	/* Model Name  */
	private String modelName;
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	private Boolean noteLeft;
	public Boolean getNoteLeft() {
		return noteLeft;
	}
	public void setNoteLeft(Boolean noteLeft) {
		this.noteLeft = noteLeft;
	}

	private String modelType;
	public String getModelType() {
		return modelType;
	}
	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	private String size;
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}

	private String hairColor;
	public String getHairColor() {
		return hairColor;
	}
	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}

	private String eyesColor;
	public String getEyesColor() {
		return eyesColor;
	}
	public void setEyesColor(String eyesColor) {
		this.eyesColor = eyesColor;
	}

	private String heightType;
	public String getHeightType() {
		return heightType;
	}
	public void setHeightType(String heightType) {
		this.heightType = heightType;
	}

	private Integer heightFt;
	public Integer getHeightFt() {
		return heightFt;
	}
	public void setHeightFt(Integer heightFt) {
		this.heightFt = heightFt;
	}

	private Integer heightIn;
	public Integer getHeightIn() {
		return heightIn;
	}
	public void setHeightIn(Integer heightIn) {
		this.heightIn = heightIn;
	}

	private Integer weightFrom;
	public Integer getWeightFrom() {
		return weightFrom;
	}
	public void setWeightFrom(Integer weightFrom) {
		this.weightFrom = weightFrom;
	}

	private Integer weightTo;
	public Integer getWeightTo() {
		return weightTo;
	}
	public void setWeightTo(Integer weightTo) {
		this.weightTo = weightTo;
	}

	private Integer waistFrom;
	public Integer getWaistFrom() {
		return waistFrom;
	}
	public void setWaistFrom(Integer waistFrom) {
		this.waistFrom = waistFrom;
	}

	private Integer waistTo;
	public Integer getWaistTo() {
		return waistTo;
	}
	public void setWaistTo(Integer waistTo) {
		this.waistTo = waistTo;
	}

	private Integer hipFrom;
	public Integer getHipFrom() {
		return hipFrom;
	}
	public void setHipFrom(Integer hipFrom) {
		this.hipFrom = hipFrom;
	}

	private Integer hipTo;
	public Integer getHipTo() {
		return hipTo;
	}
	public void setHipTo(Integer hipTo) {
		this.hipTo = hipTo;
	}

	private Integer bustFrom;
	public Integer getBustFrom() {
		return bustFrom;
	}
	public void setBustFrom(Integer bustFrom) {
		this.bustFrom = bustFrom;
	}

	private Integer bustTo;
	public Integer getBustTo() {
		return bustTo;
	}
	public void setBustTo(Integer bustTo) {
		this.bustTo = bustTo;
	}

	private BigDecimal shoeSizeFrom;
	public BigDecimal getShoeSizeFrom() {
		return shoeSizeFrom;
	}
	public void setShoeSizeFrom(BigDecimal shoeSizeFrom) {
		this.shoeSizeFrom = shoeSizeFrom;
	}

	private BigDecimal shoeSizeTo;
	public BigDecimal getShoeSizeTo() {
		return shoeSizeTo;
	}
	public void setShoeSizeTo(BigDecimal shoeSizeTo) {
		this.shoeSizeTo = shoeSizeTo;
	}

	//for model search end

	private Integer onTime;
	public Integer getOnTime() {
		return onTime;
	}
	public void setOnTime(Integer onTime) {
		this.onTime = onTime;
	}

	private Integer isDelayed;
	public Integer getIsDelayed() {
		return isDelayed;
	}
	public void setIsDelayed(Integer isDelayed) {
		this.isDelayed = isDelayed;
	}

	private Integer notCancelled;
	public Integer getNotCancelled() {
		return notCancelled;
	}
	public void setNotCancelled(Integer notCancelled) {
		this.notCancelled = notCancelled;
	}

	private Integer cancelledByFG;
	public Integer getCancelledByFG() {
		return cancelledByFG;
	}
	public void setCancelledByFG(Integer cancelledByFG) {
		this.cancelledByFG = cancelledByFG;
	}

	private Integer cancelledByVendor;
	public Integer getCancelledByVendor() {
		return cancelledByVendor;
	}
	public void setCancelledByVendor(Integer cancelledByVendor) {
		this.cancelledByVendor = cancelledByVendor;
	}

}
