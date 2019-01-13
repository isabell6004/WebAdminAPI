package net.fashiongo.webadmin.utility;

import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import net.fashiongo.webadmin.model.pojo.login.WebAdminLoginUser;


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
		
		// photo order
		psMap.put("getPhotoOrders", new Integer[] { 20, 40, 100 });

		// photo Model
		psMap.put("getModels", new Integer[] { 20, 40, 100 });

		// photo Credit
		psMap.put("getCredits", new Integer[] { 20, 40, 100 });
	}
	
	
	public static String getUsername() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication auth = context.getAuthentication();
		return (auth == null) ? null : auth.getName();
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
	
	public static String ReplaceString(String paramstr) {
		String result;
		result= paramstr.replace("'", "''");
		return result;
	}
	
	public static WebAdminLoginUser getUserInfo() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication auth = context.getAuthentication();
		return (WebAdminLoginUser)auth.getDetails();
	}
	
	public static String getIpAddress(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) ipAddress = request.getRemoteAddr();
        
		return ipAddress.equals("0:0:0:0:0:0:0:1") ? "::1" : ipAddress;
	}
	
	public static String getUserAgent(HttpServletRequest request) {
		return request.getHeader("User-Agent");
	}
	
	public static void HttpResponse(String exceptionMsg) throws Throwable {
		HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getResponse();
	    
		JsonResponse<String> res = new JsonResponse<String>();
		res.setSuccess(false);
		res.setCode(-1);
		res.setMessage(exceptionMsg);
		res.setData(null);
		
	    ObjectMapper om = new ObjectMapper();
		String returnStr = om.writeValueAsString(res);
		OutputStream ostr = response.getOutputStream();
		ostr.write(returnStr.getBytes());
		ostr.flush();
		ostr.close();
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 23.
	 * @author Reo
	 * @param weekday
	 * @return
	 */
	public static Integer GetWeekday(String weekday) {
		Integer weekid = 1;
		
		switch (weekday) {
		case "Sun":
			weekid = 1;
			break;
		case "Mon":
			weekid = 2;
			break;
		case "Tue":
			weekid = 3;
			break;
		case "Wed":
			weekid = 4;
			break;
		case "Thu":
			weekid = 5;
			break;
		case "Fri":
			weekid = 6;
			break;
		case "Sat":
			weekid = 7;
			break;
		}
		
		return weekid;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 29.
	 * @author Reo
	 * @param string
	 * @return
	 */
	public static boolean isNullOrEmpty(@Nullable String string) {
	    return string == null || string.length() == 0;
	}
}