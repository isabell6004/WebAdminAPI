/**
 * 
 */
package net.fashiongo.webadmin.utility;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author kcha
 *
 */
@JsonSerialize
public class JsonResponse<T> {
	
	private boolean success;
	private Integer errorCode;
	private String message;
	private T data;
	
	public JsonResponse() {
		this.success = true;
		this.message = "";
		
	}
	public JsonResponse(boolean success, String message, T data) {
		this.success = success;
		this.message = message;
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public Integer getErrorCode() {
		return errorCode;
	}
	
	@Override
	public String toString() {
		try {
			// return new ObjectMapper().writeValueAsString(this);
			return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return ReflectionToStringBuilder.toString(this);
		}
	}
	
}
