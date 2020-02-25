package net.fashiongo.webadmin.model.product.type.attribute;

public enum AttributeType {
	PATTERNS,
	LENGTHS,
	STYLES,
	FABRICS;

	public static AttributeType fromTabNo(int tabNo) {
		switch (tabNo) {
			case 1:
				return PATTERNS;
			case 2:
				return LENGTHS;
			case 3:
				return STYLES;
			case 4:
				return FABRICS;
			default:
				throw new IllegalArgumentException();
		}
	}
}
