package net.fashiongo.webadmin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.fashiongo.webadmin.model.pojo.ActiveBuyerStatic;
import net.fashiongo.webadmin.model.pojo.Avg;
import net.fashiongo.webadmin.model.pojo.AvgPOOrderStatic;
import net.fashiongo.webadmin.model.pojo.BuyerFirtstTimeStatic;
import net.fashiongo.webadmin.model.pojo.BuyerRegistrationStatic;
import net.fashiongo.webadmin.model.pojo.Count;
import net.fashiongo.webadmin.model.pojo.DailyBuyerStatic;
import net.fashiongo.webadmin.model.pojo.DailyStatic;
import net.fashiongo.webadmin.model.pojo.MonthClosedVendorStatic;
import net.fashiongo.webadmin.model.pojo.MonthNewVendorStatic;
import net.fashiongo.webadmin.model.pojo.MonthUploadItemStatic;
import net.fashiongo.webadmin.model.pojo.MonthlyBuyerStatic;
import net.fashiongo.webadmin.model.pojo.MonthlyStatic;
import net.fashiongo.webadmin.model.pojo.MonthlyVendorStatic;
import net.fashiongo.webadmin.model.pojo.OrderMonthlyStatic;
import net.fashiongo.webadmin.model.pojo.PoOrderStatic;
import net.fashiongo.webadmin.model.pojo.PurchasedOrderStatic;
import net.fashiongo.webadmin.model.pojo.PurchasingBuyerStatic;
import net.fashiongo.webadmin.model.pojo.TotalRateAvg;
import net.fashiongo.webadmin.model.pojo.TotalRateTotal;
import net.fashiongo.webadmin.model.pojo.YearlyStatic;
import net.fashiongo.webadmin.model.pojo.response.GetDashboardResponse;

/**
 * 
 * @author Incheol Jung
 */
@Service
public class StaticService extends ApiService {
	
	/**
	 * 
	 * Description Example
	 * 
	 * @since 2018. 10. 8.
	 * @author Incheol Jung
	 * @return
	 */
	public GetDashboardResponse GetDashboard() {
		String spName = "up_GetDashboard_WebAdmin";
        List<Object> params = new ArrayList<Object>();
        
        List<Object> _result = jdbcHelper.executeSP(spName, params
        		, TotalRateTotal.class, TotalRateAvg.class, YearlyStatic.class, MonthlyStatic.class, DailyStatic.class
        		, ActiveBuyerStatic.class, PurchasingBuyerStatic.class, MonthlyBuyerStatic.class, BuyerRegistrationStatic.class, BuyerFirtstTimeStatic.class, DailyBuyerStatic.class
        		, PurchasedOrderStatic.class, PoOrderStatic.class, AvgPOOrderStatic.class, OrderMonthlyStatic.class
        		, MonthNewVendorStatic.class, MonthUploadItemStatic.class, MonthlyVendorStatic.class, Count.class, MonthClosedVendorStatic.class, Avg.class);
        
		return null;
	}
}