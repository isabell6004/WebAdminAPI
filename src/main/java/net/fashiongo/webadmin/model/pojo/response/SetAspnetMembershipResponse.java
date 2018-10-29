package net.fashiongo.webadmin.model.pojo.response;

public class SetAspnetMembershipResponse {
	private Boolean success;
	private Integer resultCode;
	private String resultMsg;
	private Integer userID;
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public Integer getResultCode() {
		return resultCode;
	}
	public void setResultCode(Integer resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	
}
