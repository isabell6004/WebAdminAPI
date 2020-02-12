package net.fashiongo.webadmin.model.product.type.attribute;

public enum AttributeRequestType {
	DELETE_ALL,
	DELETE,
	ADD,
	UPDATE,
	MAPPING;

	public static AttributeRequestType fromBType(String bType) {
		if (bType == null || bType.isEmpty()) {
			throw new IllegalArgumentException();
		}

		switch (bType) {
			case "ADel":
				return DELETE_ALL;
			case "Del":
				return DELETE;
			case "Add":
				return ADD;
			case "save":
				return UPDATE;
			default:
				throw new IllegalArgumentException();
		}
	}
}
