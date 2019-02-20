package net.fashiongo.webadmin.dao.photostudio;

import net.fashiongo.webadmin.model.photostudio.PhotoCart;
import net.fashiongo.webadmin.model.photostudio.PhotoCredit;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface PhotoCartRepository extends CrudRepository<PhotoCart, Integer> {

    @EntityGraph(value = "graph.PhotoCart.cartDetails" , type= EntityGraph.EntityGraphType.FETCH)
    List<PhotoCart> findAllByCreatedOnBetween(Date createdOnStart, Date createdOnEnd);


}
