package net.fashiongo.webadmin.support.storage;

public class SwiftApiCallException extends RuntimeException {
	private int statusCode;
	private String containerName;

	public SwiftApiCallException(String message, int statusCode) {
		super(message);
		this.statusCode = statusCode;
	}

	public SwiftApiCallException(String message, int statusCode, String containerName) {
		super(message);
		this.statusCode = statusCode;
		this.containerName = containerName;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getContainerName() {
		return containerName;
	}
}
