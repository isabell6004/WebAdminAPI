package net.fashiongo.webadmin.model.product.command.attribute;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class AttributeMappingRequest {

	private final int categoryId;

	private final List<Integer> ids = new ArrayList<>();

	public AttributeMappingRequest(int categoryId, List<Integer> ids) {
		this.categoryId = categoryId;
		this.ids.addAll(ids);
	}

	public List<Integer> getIds() {
		return Collections.unmodifiableList(ids);
	}
}
