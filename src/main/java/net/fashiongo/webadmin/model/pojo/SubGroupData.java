package net.fashiongo.webadmin.model.pojo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author Incheol Jung
 */
public class SubGroupData {
	@ApiModelProperty(required = true, example="112")
	private Integer rId;
	@ApiModelProperty(required = true, example="Group Manager")
	private String rnm;
	@ApiModelProperty(required = true, example="false")
	private boolean v;
	@ApiModelProperty(required = true, example="false")
	private boolean e;
	@ApiModelProperty(required = true, example="false")
	private boolean d;
	@ApiModelProperty(required = true, example="false")
	private boolean a;
	@ApiModelProperty(required = true, example="115")
	private Integer mId;
	
	public Integer getrId() {
		return rId;
	}
	public void setrId(Integer rId) {
		this.rId = rId;
	}
	public String getRnm() {
		return rnm;
	}
	public void setRnm(String rnm) {
		this.rnm = rnm;
	}
	public boolean getV() {
		return v;
	}
	public void setV(boolean v) {
		this.v = v;
	}
	public boolean getE() {
		return e;
	}
	public void setE(boolean e) {
		this.e = e;
	}
	public boolean getD() {
		return d;
	}
	public void setD(boolean d) {
		this.d = d;
	}
	public boolean getA() {
		return a;
	}
	public void setA(boolean a) {
		this.a = a;
	}
	public Integer getmId() {
		return mId;
	}
	public void setmId(Integer mId) {
		this.mId = mId;
	}
	
}
