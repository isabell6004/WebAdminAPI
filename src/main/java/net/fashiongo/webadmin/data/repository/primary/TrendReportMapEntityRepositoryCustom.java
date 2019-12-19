package net.fashiongo.webadmin.data.repository.primary;

import net.fashiongo.webadmin.data.entity.primary.TrendReportMapEntity;
import net.fashiongo.webadmin.data.model.sitemgmt.ProductsSelectCheck;

import java.util.List;

public interface TrendReportMapEntityRepositoryCustom {
    ProductsSelectCheck getProductsSelectCheck(Integer trendReport, Integer productID);

    List<TrendReportMapEntity> findAllByTrendReportID(Integer trendReportID);
}
