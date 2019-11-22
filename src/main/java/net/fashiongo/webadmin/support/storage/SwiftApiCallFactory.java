package net.fashiongo.webadmin.support.storage;

public class SwiftApiCallFactory {

	private SwiftAuth auth;

	private SwiftPropertyInterface properties;

	public SwiftApiCallFactory(SwiftAuth auth, SwiftPropertyInterface properties) {
		this.auth = auth;
		this.properties = properties;
	}

	public SwiftApiCaller create() {
		return new SwiftApiCaller(auth,properties.getApiUrl() + properties.getAccount());
	}
}
