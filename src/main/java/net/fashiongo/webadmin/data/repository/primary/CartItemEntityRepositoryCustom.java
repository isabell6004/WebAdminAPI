package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.model.buyer.ShoppingBag;

import java.util.List;

public interface CartItemEntityRepositoryCustom {

	List<Integer> findAllShoppingBagGroupByWholesalerId(int retailerId);

	List<ShoppingBag> findAllShoppingBag(int retailerId);
}
