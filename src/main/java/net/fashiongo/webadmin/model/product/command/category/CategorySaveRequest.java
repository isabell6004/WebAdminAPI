package net.fashiongo.webadmin.model.product.command.category;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategorySaveRequest {

	private final String categoryName;

	private final String categoryDescription;

	private final int parentCategoryId;

	private final Integer parentParentCategoryId;

	private final int level;

	private final String titleImage;

	private final boolean isLandingPage;

	private final boolean isFeatured;

	private final int listOrder;

	private final boolean isActive;
}
