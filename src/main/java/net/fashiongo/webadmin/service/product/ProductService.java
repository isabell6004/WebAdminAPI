package net.fashiongo.webadmin.service.product;

import net.fashiongo.webadmin.model.product.Product;
import net.fashiongo.webadmin.model.product.ProductSearchCondition;
import net.fashiongo.webadmin.service.externalutil.response.CollectionObject;

/**
 * The interface Product service.
 */
public interface ProductService {

    /**
     * Find list.
     *
     * @param condition the condition
     * @return the list
     */
    CollectionObject<Product> find(ProductSearchCondition condition);
}
