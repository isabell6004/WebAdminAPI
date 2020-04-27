package net.fashiongo.webadmin.model.pojo.message;

public class ResultMessage {
	int result;
	String guid;
	String attachedFileName;
	String attachedFileName2;
	String attachedFileName3;
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getAttachedFileName() { return attachedFileName; }
	public void setAttachedFileName(String attachedFileName) { this.attachedFileName = attachedFileName; }
	public String getAttachedFileName2() { return attachedFileName2; }
	public void setAttachedFileName2(String attachedFileName2) { this.attachedFileName2 = attachedFileName2; }
	public String getAttachedFileName3() { return attachedFileName3; }
	public void setAttachedFileName3(String attachedFileName3) { this.attachedFileName3 = attachedFileName3; }
}
