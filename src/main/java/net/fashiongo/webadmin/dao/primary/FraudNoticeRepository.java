package net.fashiongo.webadmin.dao.primary;

import net.fashiongo.webadmin.model.primary.FraudNotice;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FraudNoticeRepository extends CrudRepository<FraudNotice, Integer> {
    List<FraudNotice> findByRetailerIdAndActiveIsTrueOrderByFraudNoticeIdDesc(int retailerId);
}
