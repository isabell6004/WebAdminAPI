package net.fashiongo.webadmin.model.product.command.attribute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AttributeDeleteRequest {

	private final List<Integer> ids = new ArrayList<>();

	public AttributeDeleteRequest(List<Integer> ids) {
		this.ids.addAll(ids);
	}

	public List<Integer> getIds() {
		return Collections.unmodifiableList(ids);
	}
}
