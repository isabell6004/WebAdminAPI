package net.fashiongo.webadmin.model.pojo;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author Incheol Jung
 */
public class GroupData {
	@ApiModelProperty(required = true, example="0")
	private Integer rId;
	@ApiModelProperty(required = true, example="Administration")
	private String rnm;
	@ApiModelProperty(required = true, example="false")
	private boolean v;
	@ApiModelProperty(required = true, example="false")
	private boolean e;
	@ApiModelProperty(required = true, example="false")
	private boolean d;
	@ApiModelProperty(required = true, example="false")
	private boolean a;
	@ApiModelProperty(required = true, example="114")
	private Integer mId;
	@ApiModelProperty(required = true)
	private List<SubGroupData> sub;
	@ApiModelProperty(required = true, example="false")
	private boolean exp;
	@ApiModelProperty(required = true, example="7")
	private Integer cnt;
	@ApiModelProperty(required = true, example="0")
	private Integer chkcnt_v;
	@ApiModelProperty(required = true, example="0")
	private Integer chkcnt_e;
	@ApiModelProperty(required = true, example="0")
	private Integer chkcnt_d;
	@ApiModelProperty(required = true, example="0")
	private Integer chkcnt_a;
	
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
	public boolean isV() {
		return v;
	}
	public void setV(boolean v) {
		this.v = v;
	}
	public boolean isE() {
		return e;
	}
	public void setE(boolean e) {
		this.e = e;
	}
	public boolean isD() {
		return d;
	}
	public void setD(boolean d) {
		this.d = d;
	}
	public boolean isA() {
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
	public List<SubGroupData> getSub() {
		return sub;
	}
	public void setSub(List<SubGroupData> sub) {
		this.sub = sub;
	}
	public boolean isExp() {
		return exp;
	}
	public void setExp(boolean exp) {
		this.exp = exp;
	}
	public Integer getCnt() {
		return cnt;
	}
	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}
	public Integer getChkcnt_v() {
		return chkcnt_v;
	}
	public void setChkcnt_v(Integer chkcnt_v) {
		this.chkcnt_v = chkcnt_v;
	}
	public Integer getChkcnt_e() {
		return chkcnt_e;
	}
	public void setChkcnt_e(Integer chkcnt_e) {
		this.chkcnt_e = chkcnt_e;
	}
	public Integer getChkcnt_d() {
		return chkcnt_d;
	}
	public void setChkcnt_d(Integer chkcnt_d) {
		this.chkcnt_d = chkcnt_d;
	}
	public Integer getChkcnt_a() {
		return chkcnt_a;
	}
	public void setChkcnt_a(Integer chkcnt_a) {
		this.chkcnt_a = chkcnt_a;
	}
}
