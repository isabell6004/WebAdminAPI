package net.fashiongo.webadmin.model.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/*
 * @author sanghyup
 */
@JsonSerialize
public class ResultResponse<T> {
	private Boolean success;
	private T data;
	private Integer code;
	private Integer pk;
	private String message;

	public ResultResponse() {
		this.success = false;
		this.code = 0;
		this.pk = 0;
		this.message = "";
		this.data = null;
	}

	public ResultResponse(Boolean success, Integer code, Integer pk, String message, T data) {
		this.success = success;
		this.code = code;
		this.pk = pk;
		this.message = message;
		this.data = data;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getPk() {
		return pk;
	}

	public void setPk(Integer pk) {
		this.pk = pk;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	/*
	 * set result wrapper
	 * 
	 * @author sanghyup
	 */
	public void setResultWrapper(Boolean success, Integer code, Integer pk, String message, T data) {
		this.success = success;
		this.code = code;
		this.pk = pk;
		this.message = message;
		this.data = data;
	}

}
