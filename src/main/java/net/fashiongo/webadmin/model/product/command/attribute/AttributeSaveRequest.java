package net.fashiongo.webadmin.model.product.command.attribute;

import lombok.Getter;

@Getter
public class AttributeSaveRequest {

	private final String name;

	private final Boolean active;

	public AttributeSaveRequest(String name, Boolean active) {
		this.name = name;
		this.active = active;
	}
}
