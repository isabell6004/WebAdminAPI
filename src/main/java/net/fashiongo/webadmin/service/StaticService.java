package net.fashiongo.webadmin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.fashiongo.webadmin.model.pojo.statics.ActiveBuyerStatic;
import net.fashiongo.webadmin.model.pojo.statics.Avg;
import net.fashiongo.webadmin.model.pojo.statics.AvgPOOrderStatic;
import net.fashiongo.webadmin.model.pojo.statics.BuyerFirtstTimeStatic;
import net.fashiongo.webadmin.model.pojo.statics.BuyerRegistrationDateStatic;
import net.fashiongo.webadmin.model.pojo.statics.BuyerRegistrationStatic;
import net.fashiongo.webadmin.model.pojo.statics.Count;
import net.fashiongo.webadmin.model.pojo.statics.DailyBuyerStatic;
import net.fashiongo.webadmin.model.pojo.statics.DailyStatic;
import net.fashiongo.webadmin.model.pojo.statics.MonthClosedVendorStatic;
import net.fashiongo.webadmin.model.pojo.statics.MonthNewVendorStatic;
import net.fashiongo.webadmin.model.pojo.statics.MonthUploadItemStatic;
import net.fashiongo.webadmin.model.pojo.statics.MonthlyBuyerStatic;
import net.fashiongo.webadmin.model.pojo.statics.MonthlyStatic;
import net.fashiongo.webadmin.model.pojo.statics.MonthlyVendorStatic;
import net.fashiongo.webadmin.model.pojo.statics.OrderMonthlyStatic;
import net.fashiongo.webadmin.model.pojo.statics.PoOrderStatic;
import net.fashiongo.webadmin.model.pojo.statics.PurchasedOrderStatic;
import net.fashiongo.webadmin.model.pojo.statics.PurchasingBuyerStatic;
import net.fashiongo.webadmin.model.pojo.statics.TotalRateAvg;
import net.fashiongo.webadmin.model.pojo.statics.TotalRateTotal;
import net.fashiongo.webadmin.model.pojo.statics.YearlyStatic;
import net.fashiongo.webadmin.model.pojo.statics.response.GetDashboardResponse;

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
	public GetDashboardResponse getDashboard() {
		GetDashboardResponse result = new GetDashboardResponse();
		String spName = "up_GetDashboard_WebAdmin";
        List<Object> params = new ArrayList<Object>();
        
        List<Object> _result = jdbcHelper.executeSP(spName, params
        		, TotalRateTotal.class, TotalRateAvg.class, YearlyStatic.class, MonthlyStatic.class, DailyStatic.class // 4
        		, ActiveBuyerStatic.class, PurchasingBuyerStatic.class, MonthlyBuyerStatic.class, BuyerRegistrationDateStatic.class, BuyerFirtstTimeStatic.class, DailyBuyerStatic.class // 10
        		, PurchasedOrderStatic.class, PoOrderStatic.class, AvgPOOrderStatic.class, OrderMonthlyStatic.class // 14
        		, MonthNewVendorStatic.class, MonthUploadItemStatic.class, MonthlyVendorStatic.class, Count.class, MonthClosedVendorStatic.class, Avg.class); // 20
        // visitors
        result.getVisitors().setTotalVisitors(((List<TotalRateTotal>)_result.get(0)).get(0));
        result.getVisitors().setAvgVisitors(((List<TotalRateAvg>)_result.get(1)).get(0));
        result.getVisitors().setVisitorsYearly((List<YearlyStatic>)_result.get(2));
        result.getVisitors().setVisitorsMonthly((List<MonthlyStatic>)_result.get(3));
        result.getVisitors().setVisitorsDaily((List<DailyStatic>)_result.get(4));
        // buyers
        result.getBuyers().setActiveBuyers(((List<ActiveBuyerStatic>)_result.get(5)).get(0));
        result.getBuyers().setPurchasingBuyers(((List<PurchasingBuyerStatic>)_result.get(6)).get(0));
        BuyerRegistrationStatic regiStatis = new BuyerRegistrationStatic(
        		((List<BuyerRegistrationDateStatic>)_result.get(8)).get(0)
        		, ((List<BuyerFirtstTimeStatic>)_result.get(9)).get(0));
        result.getBuyers().setBuyerRegistration(regiStatis);
        result.getBuyers().setBuyerRegMonthly((List<MonthlyBuyerStatic>)_result.get(7));
        result.getBuyers().setBuyerPOMonthly((List<DailyBuyerStatic>)_result.get(10));
        // orders
        result.getOrders().setPurchasedOrders(((List<PurchasedOrderStatic>)_result.get(11)).get(0));
        result.getOrders().setPoOrders(((List<PoOrderStatic>)_result.get(12)).get(0));
        result.getOrders().setAvgPOOrders(((List<AvgPOOrderStatic>)_result.get(13)).get(0));
        result.getOrders().setOrderMonthly((List<OrderMonthlyStatic>)_result.get(14));
        // vendors
        result.getVendors().setNewVendors(((List<MonthNewVendorStatic>)_result.get(15)).get(0));
        result.getVendors().setUploadedItems(((List<MonthUploadItemStatic>)_result.get(16)).get(0));
        result.getVendors().setVendorsMonthly((List<MonthlyVendorStatic>)_result.get(17));
        result.getVendors().setOrderNumOfVendorCount(((List<Count>)_result.get(18)).get(0));
        result.getVendors().setClosedVendors(((List<MonthClosedVendorStatic>)_result.get(19)).get(0));
        result.getVendors().setAvgVendorDuration(((List<Avg>)_result.get(20)).get(0));
        
		return result;
	}
}