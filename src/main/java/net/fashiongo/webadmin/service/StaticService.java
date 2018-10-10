package net.fashiongo.webadmin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.fashiongo.webadmin.model.pojo.ActiveBuyerStatic;
import net.fashiongo.webadmin.model.pojo.Avg;
import net.fashiongo.webadmin.model.pojo.AvgPOOrderStatic;
import net.fashiongo.webadmin.model.pojo.BuyerRegistrationStatic;
import net.fashiongo.webadmin.model.pojo.Count;
import net.fashiongo.webadmin.model.pojo.DailyStatic;
import net.fashiongo.webadmin.model.pojo.MonthVendorStatic;
import net.fashiongo.webadmin.model.pojo.MonthlyStatic;
import net.fashiongo.webadmin.model.pojo.OrderMonthlyStatic;
import net.fashiongo.webadmin.model.pojo.PoOrderStatic;
import net.fashiongo.webadmin.model.pojo.PurchasedOrderStatic;
import net.fashiongo.webadmin.model.pojo.PurchasingBuyerStatic;
import net.fashiongo.webadmin.model.pojo.TotalRate;
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
        
        List<Object> _result = jdbcHelper.executeSP(spName, params, TotalRate.class, TotalRate.class, YearlyStatic.class, MonthlyStatic.class, DailyStatic.class
        		, ActiveBuyerStatic.class, PurchasingBuyerStatic.class, BuyerRegistrationStatic.class, MonthlyStatic.class, DailyStatic.class
        		, PurchasedOrderStatic.class, PoOrderStatic.class, AvgPOOrderStatic.class, OrderMonthlyStatic.class
        		, MonthVendorStatic.class, MonthVendorStatic.class, MonthlyStatic.class, Count.class, MonthVendorStatic.class, Avg.class);
        
		return null;
	}
}