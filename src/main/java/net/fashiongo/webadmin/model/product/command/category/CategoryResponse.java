package net.fashiongo.webadmin.model.product.command.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
	private Integer categoryId;

	private String categoryName;

	private String categoryDescription;

	private int parentCategoryId;

	private Integer parentParentCategoryId;

	private int level;

	private String titleImage;

	private boolean landingPage;

	private boolean featured;

	private int listOrder;

	private boolean active;
}
