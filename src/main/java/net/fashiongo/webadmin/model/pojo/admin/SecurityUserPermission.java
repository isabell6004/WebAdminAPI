package net.fashiongo.webadmin.model.pojo.admin;

import java.util.List;

/**
 * 
 * @author Reo
 *
 */
public class SecurityUserPermission {
	private Boolean a;
	private Boolean d;
	private Boolean e;
	private Boolean v;
	private Integer chkcnt_a;
	private Integer chkcnt_d;
	private Integer chkcnt_e;
	private Integer chkcnt_v;
	private Integer cnt;
	private Boolean exp;
	private Integer rId;
	private Integer mId;
	private String rnm;
	private List<SecurityUserPermissionSub> sub;
	
	public Integer getrId() {
		return rId;
	}
	public void setrId(Integer rId) {
		this.rId = rId;
	}
	public Boolean getA() {
		return a;
	}
	public void setA(Boolean a) {
		this.a = a;
	}
	public Boolean getD() {
		return d;
	}
	public void setD(Boolean d) {
		this.d = d;
	}
	public Boolean getE() {
		return e;
	}
	public void setE(Boolean e) {
		this.e = e;
	}
	public Boolean getV() {
		return v;
	}
	public void setV(Boolean v) {
		this.v = v;
	}
	public Integer getChkcnt_a() {
		return chkcnt_a;
	}
	public void setChkcnt_a(Integer chkcnt_a) {
		this.chkcnt_a = chkcnt_a;
	}
	public Integer getChkcnt_d() {
		return chkcnt_d;
	}
	public void setChkcnt_d(Integer chkcnt_d) {
		this.chkcnt_d = chkcnt_d;
	}
	public Integer getChkcnt_e() {
		return chkcnt_e;
	}
	public void setChkcnt_e(Integer chkcnt_e) {
		this.chkcnt_e = chkcnt_e;
	}
	public Integer getChkcnt_v() {
		return chkcnt_v;
	}
	public void setChkcnt_v(Integer chkcnt_v) {
		this.chkcnt_v = chkcnt_v;
	}
	public Integer getCnt() {
		return cnt;
	}
	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}
	public Boolean getExp() {
		return exp;
	}
	public void setExp(Boolean exp) {
		this.exp = exp;
	}
	public Integer getmId() {
		return mId;
	}
	public void setmId(Integer mId) {
		this.mId = mId;
	}
	public String getRnm() {
		return rnm;
	}
	public void setRnm(String rnm) {
		this.rnm = rnm;
	}
	public List<SecurityUserPermissionSub> getSub() {
		return sub;
	}
	public void setSub(List<SecurityUserPermissionSub> sub) {
		this.sub = sub;
	}
	
	
	
}
