package net.fashiongo.webadmin.model.product.type.attribute;

public enum AttributeRequestType {
	DELETE_ALL,
	DELETE,
	ADD,
	SAVE,
	MAPPING;

	public static AttributeRequestType fromBType(String btype) {
		if (btype == null || btype.isEmpty()) {
			throw new IllegalArgumentException();
		}

		switch (btype) {
			case "ADel":
				return DELETE_ALL;
			case "Del":
				return DELETE;
			case "Add":
				return ADD;
			case "save":
				return SAVE;
			default:
				throw new IllegalArgumentException();
		}
	}
}
