package net.fashiongo.webadmin.model.pojo.response;

/**
 * @author JungHwan
 */
public class SetResultResponse {
	private Boolean success;
	private Integer resultCode;
	private String resultMsg;

	public SetResultResponse() {
	}

	public SetResultResponse(Boolean success, Integer resultCode, String resultMsg) {
		this.success = success;
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}

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

}
