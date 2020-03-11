package net.fashiongo.webadmin.model.product.command.category;

import lombok.Getter;

@Getter
public class CategoryListOrderRequest {

	private final int parentId;

	private final int listOrder;

	public CategoryListOrderRequest(int parentId, int listOrder) {
		this.parentId = parentId;
		this.listOrder = listOrder;
	}
}
