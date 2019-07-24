package net.fashiongo.webadmin.support.storage;

public class SwiftBulkOperation {
	private String containerName;
	private String fileName;

	public SwiftBulkOperation(String containerName, String fileName) {
		this.containerName = containerName;
		this.fileName = fileName;
	}

	public String getContainerName() {
		return containerName;
	}

	public String getFileName() {
		return fileName;
	}

	/**
	 * toString 수정 금지
	 * @return
	 */
	@Override
	public String toString() {
		return containerName + "/" + fileName;
	}
}
