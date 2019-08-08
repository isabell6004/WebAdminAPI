package net.fashiongo.webadmin.support.storage;

public class SwiftApiCallFactory {

	private SwiftAuth auth;

	private SwiftProperties properties;

	public SwiftApiCallFactory(SwiftAuth auth, SwiftProperties properties) {
		this.auth = auth;
		this.properties = properties;
	}

	public SwiftApiCaller create() {
		return new SwiftApiCaller(auth,properties.getApiUrl() + properties.getAccount());
	}
}
