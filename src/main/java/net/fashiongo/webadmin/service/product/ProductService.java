package net.fashiongo.webadmin.service.product;

import net.fashiongo.webadmin.model.product.Product;
import net.fashiongo.webadmin.model.product.ProductSearchCondition;
import net.fashiongo.webadmin.service.externalutil.response.CollectionObject;
import net.fashiongo.webadmin.service.externalutil.response.SingleObject;

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

    /**
     * Find product.
     *
     * @param condition the condition
     * @return product
     */
    SingleObject<Product> find(ProductSearchCondition condition, int productId);
}
