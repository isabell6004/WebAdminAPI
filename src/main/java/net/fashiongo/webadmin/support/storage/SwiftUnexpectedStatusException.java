package net.fashiongo.webadmin.support.storage;

public class SwiftUnexpectedStatusException extends SwiftApiCallException {

	public SwiftUnexpectedStatusException(String message, int statusCode) {
		super(message, statusCode);
	}

	public SwiftUnexpectedStatusException(String message, int statusCode, String containerName) {
		super(message, statusCode, containerName);
	}
}
