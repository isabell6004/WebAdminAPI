package net.fashiongo.webadmin.model.product.type.attribute;

public enum AttributeType {
	PATTERN,
	LENGTH,
	STYLE,
	FABRIC;

	public static AttributeType fromTabNo(int tabNo) {
		switch (tabNo) {
			case 1:
				return PATTERN;
			case 2:
				return LENGTH;
			case 3:
				return STYLE;
			case 4:
				return FABRIC;
			default:
				throw new IllegalArgumentException();
		}
	}
}
