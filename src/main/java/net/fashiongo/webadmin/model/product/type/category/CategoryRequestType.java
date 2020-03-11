package net.fashiongo.webadmin.model.product.type.category;

public enum CategoryRequestType {
	ADD,
	UPDATE,
	ACTIVATE,
	DELETE;

	public static CategoryRequestType fromSetType(String setType) {
		if (setType == null || setType.isEmpty()) {
			throw new IllegalArgumentException();
		}

		switch (setType) {
			case "Add":
				return ADD;
			case "Upd":
				return UPDATE;
			case "Act":
				return ACTIVATE;
			case "Del":
				return DELETE;
			default:
				throw new IllegalArgumentException();
		}
	}
}
