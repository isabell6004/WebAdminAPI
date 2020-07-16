package net.fashiongo.webadmin.model.product.command.category;

import lombok.Getter;

@Getter
public class ProductCategoryResponse {
    private int categoryId;

    private String categoryName;

    private String categoryDescription;

    private int parentId;

    private Integer grandparentId;

    private int level;

    private String titleImage;

    private int listOrder;
}
