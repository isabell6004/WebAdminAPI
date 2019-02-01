package net.fashiongo.webadmin.model.pojo.message;

import java.util.UUID;

public class ResultMessage {
	int result;
	String guid;
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(UUID guid) {
		this.guid = guid.toString();
	}
	
}
