package net.fashiongo.webadmin.model.pojo.parameter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author Incheol Jung
 */
public class SetFGCatalogParameter implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(required = false, example="title")
	private String subject;
	@ApiModelProperty(required = false, example="<html></html>")
	private String contents;
	@ApiModelProperty(required = false, example="Myesper Apparel,Nicole Lee USA,Cutie Patootie Clothing")
	private String includedvendors;
	@ApiModelProperty(required = false, example="10493,10492,10491")
	private String vendorcode;
	@ApiModelProperty(required = false, example="")
	private String catalogsendqueueid;
	
	public String getSubject() {
		return StringUtils.isEmpty(this.subject) ? "Title" : this.subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContents() {
		return StringUtils.isEmpty(this.contents) ? null : this.contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getIncludedvendors() {
		return StringUtils.isEmpty(this.includedvendors) ? null : this.includedvendors;
	}
	public void setIncludedvendors(String includedvendors) {
		this.includedvendors = includedvendors;
	}
	public List<Integer> getVendorcode() {
		return StringUtils.isEmpty(this.vendorcode) ? null : Arrays.stream(this.vendorcode.split(",")).map(Integer::parseInt).collect(Collectors.toList());
	}
	public void setVendorcode(String vendorcode) {
		this.vendorcode = vendorcode;
	}
	public Integer getCatalogsendqueueid() {
		return StringUtils.isEmpty(this.catalogsendqueueid) ? 0 : Integer.parseInt(this.catalogsendqueueid);
	}
	public void setCatalogsendqueueid(String catalogsendqueueid) {
		this.catalogsendqueueid = catalogsendqueueid;
	}
}