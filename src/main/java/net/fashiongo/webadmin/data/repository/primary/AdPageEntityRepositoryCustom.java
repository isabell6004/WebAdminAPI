package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.AdPageEntity;
import net.fashiongo.webadmin.data.model.ad.BidAdPage;

import java.util.List;

public interface AdPageEntityRepositoryCustom {
    List<AdPageEntity> findAllOrderByPageIDAsc();
    List<AdPageEntity> findAllDistinctOrderByPageIDAsc();
    List<BidAdPage> up_wa_GetBidAdPages();
}
