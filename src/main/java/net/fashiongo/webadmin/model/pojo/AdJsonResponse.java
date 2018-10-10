package net.fashiongo.webadmin.model.pojo;

/**
 * @author Nayeon Kim
 */
public class AdJsonResponse {
	private Integer code;
	private String message;
	
	public AdJsonResponse() {
		this.code = -1;
		this.message = "";
	}
	
	public AdJsonResponse(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
