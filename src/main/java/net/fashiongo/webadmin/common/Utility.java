/**
 * 
 */
package net.fashiongo.webadmin.common;

import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import net.fashiongo.common.enums.UserRole;

/**
 * @author Brian
 *
 */
public class Utility {
private static final HashMap<String, Integer[]> psMap = new HashMap<String, Integer[]>(); 
	
	private static void createSizeMap() {
		// customer
		psMap.put("getCustomerOrderStat", new Integer[]{6});
		psMap.put("getCustomerOrders", new Integer[]{20, 40, 100, 200});
		psMap.put("getCustomerRating", new Integer[]{20, 40, 100, 200});
		psMap.put("getCustomers", new Integer[]{10, 20, 40, 100, 200, 500});
		psMap.put("getCustomerGroups", new Integer[]{10, 25, 50});
		psMap.put("searchCustomers", new Integer[]{10, 20, 40, 100, 200, 500});
		psMap.put("getCustomersFromCustomerGroup", new Integer[]{10, 20, 40, 100, 200, 500});
		
		// dashboard
		psMap.put("getAlerts", new Integer[]{10});
		
		// feedback
		psMap.put("getVendorFeedback", new Integer[]{20, 40, 100, 200});
		psMap.put("getRetailerFeedback", new Integer[]{20, 40, 100, 200});
		
		// item
		psMap.put("getColors", new Integer[]{100, 200, 300});
		psMap.put("getSupplyingVendor", new Integer[]{10, 25, 50});
		psMap.put("getItems", new Integer[]{36, 90, 126, 180});
		psMap.put("getCrossSellingItems", new Integer[]{15, 25, 40, 55, 70});
		psMap.put("getFastEditItems", new Integer[]{10, 20, 30});
		psMap.put("getSortItems", new Integer[]{50, 100, 150});
		psMap.put("getItemsImportLog", new Integer[]{10, 25, 50});
		
		// marketing
		psMap.put("SearchEmailAddresses", new Integer[]{10, 25, 50});
		psMap.put("GetPaidCampaigns", new Integer[]{10, 25, 50});
		psMap.put("GetFGCampaigns", new Integer[]{10, 25, 50});
		psMap.put("GetCatalogs", new Integer[]{10, 25, 50});
		psMap.put("GetCategoryItems", new Integer[]{20, 40, 50, 55, 70, 100, 150});
		psMap.put("GetTodayDealHistory", new Integer[]{10, 25, 50});
		psMap.put("getBanners", new Integer[]{20, 40, 100, 200});
		
		// ad
		psMap.put("getAdItems", new Integer[]{50, 100, 200});
		
		// message
		psMap.put("GetInboxMessages", new Integer[]{10, 25, 50, 100});
		psMap.put("GetOutboxMessages", new Integer[]{10, 25, 50, 100});
		
		// order
		psMap.put("getOrders", new Integer[]{10, 25, 50});
		psMap.put("getCartOrders", new Integer[]{10, 25, 50});
		psMap.put("getStoreCreditOverall", new Integer[]{10});
		
		// stat
		psMap.put("getStatsItems", new Integer[]{10, 25, 50, 100});
		psMap.put("getStatsOrder", new Integer[]{10, 12, 17, 10});
		
		// vendor admin
		psMap.put("getAdminLog", new Integer[]{36, 90, 126, 180});
	}
	
	
	public static String getUsername() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication auth = context.getAuthentication();
		return auth.getName();
	}
	
	public static String getFileExtension(String fileName) {
		return ".jpg";
	}
	
	public static boolean checkValidPageSize(String methodName, Integer pageSize) {
		boolean bResult = false;
		
		if (methodName != null && pageSize != null) {
			if (psMap.size() < 1) {
				createSizeMap();
			}
			
			if (psMap.containsKey(methodName)) {
				Integer[] sizes = psMap.get(methodName);
				if (Arrays.asList(sizes).contains(pageSize)) {
					bResult = true;
				}
			}
		}
		
		return bResult;
	}
}
