package net.fashiongo.webadmin.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.fashiongo.webadmin.dao.primary.VendorNewsDetailRepository;
import net.fashiongo.webadmin.dao.primary.VendorNewsViewRepository;
import net.fashiongo.webadmin.model.pojo.Message;
import net.fashiongo.webadmin.model.pojo.Total;
import net.fashiongo.webadmin.model.pojo.VendorNews;
import net.fashiongo.webadmin.model.pojo.parameter.DelVendorNewsParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetMessageParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetVendorNewsDetailParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetVendorNewsParameter;
import net.fashiongo.webadmin.model.pojo.response.GetMessageResponse;
import net.fashiongo.webadmin.model.pojo.response.GetVendorNewsResponse;
import net.fashiongo.webadmin.model.primary.VendorNewsDetail;
import net.fashiongo.webadmin.model.primary.VendorNewsView;

/**
 * 
 * @author Incheol Jung
 */
@Service
public class MessageService extends ApiService {
	@Autowired
	VendorNewsDetailRepository vendorNewsDetailRepository;
	
	@Autowired
	VendorNewsViewRepository vendorNewsViewRepository;
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 9. 19.
	 * @author Incheol Jung
	 * @param parameters
	 * @return 
	 */
	public GetMessageResponse getMessage(GetMessageParameter parameters) {
		GetMessageResponse result = new GetMessageResponse();
		String spName = "up_wa_GetAdminMessage";
        List<Object> params = new ArrayList<Object>();
        
        params.add(parameters.getPagenum());
        params.add(parameters.getPagesize());
        params.add(parameters.getParent());
        params.add(parameters.getSendertypeid());
        params.add(parameters.getRecipienttypeid());
        params.add(null);
        params.add(null);
        params.add(parameters.getSender());
        params.add(parameters.getTopic());
        params.add(parameters.getSubject());
        params.add(parameters.getPeriod());
        params.add(parameters.getFromdate());
        params.add(parameters.getTodate());
        params.add(parameters.getStatus());
        
        List<Object> _result = jdbcHelper.executeSP(spName, params, Total.class, Message.class);
        
		result.setTotal((List<Total>)_result.get(0));
		result.setMessagelist((List<Message>) _result.get(1));
		
		return result;
	}
	
	/**
	 * 
	 * GetVendorNews
	 * 
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param GetVendorNewsParameter
	 * @return GetVendorNewsResponse
	 */
	@SuppressWarnings("unchecked")
	public GetVendorNewsResponse getVendorNews (GetVendorNewsParameter parameters) {
		GetVendorNewsResponse result = new GetVendorNewsResponse();
		String spName = "up_wa_GetVendorNews";
        List<Object> params = new ArrayList<Object>();
        
        params.add(parameters.getPageNum());
        params.add(parameters.getPageSize());
        params.add(parameters.getVendor());
        params.add(parameters.getNewsTitle());
        params.add(parameters.getActive());
        params.add(parameters.getPeriod());
        params.add(parameters.getFromDate());
        params.add(parameters.getToDate());
        params.add(parameters.getOrderBy());
        params.add(parameters.getDropOffNotice());
        
        List<Object> _result = jdbcHelper.executeSP(spName, params, Total.class, VendorNews.class);
        
		result.setTotal((List<Total>)_result.get(0));
		result.setNewsList((List<VendorNews>) _result.get(1));
		
		return result;
	}
	
	/**
	 * 
	 * DelVendorNews
	 * 
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param DelVendorNewsParameter
	 * @return Integer
	 */
	@Transactional("primaryTransactionManager")
	public Integer delVendorNews (DelVendorNewsParameter parameters) {
		String spName = "up_wa_DeleteVendorNews";
        List<Object> params = new ArrayList<Object>();
        params.add(parameters.getArrayNewsID());
        jdbcHelper.executeSP(spName, params, VendorNewsDetail.class);
		return 1;
	}
	
	/**
	 * 
	 * GetVendorNewsDetail
	 * 
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param GetVendorNewsDetailParameter
	 * @return VendorNewsDetail
	 */
	public VendorNewsView getVendorNewsDetail (GetVendorNewsDetailParameter parameters) {
		VendorNewsView result = vendorNewsViewRepository.findOneByNewsID(parameters.getNewsID());
		return result;
	}
	
	/**
	 * 
	 * SetVendorNews
	 * 
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param news, selectedVendor
	 * @return Integer
	 */
	public Integer setVendorNews(VendorNewsDetail news, String selectedVendor) {
		Integer result = 0;
		String[] widList = selectedVendor.split(",", -1);
		if(widList.length > 0) {
			for(String wid : widList) {
				if(wid != "") news.setWholeSalerID(Integer.parseInt(wid));
				result = setVendorNewsDetail(news);
			}
		} else {
			result = setVendorNewsDetail(news);
		}
		return result;
	}
	
	public Integer setVendorNewsDetail(VendorNewsDetail news) {
		Integer result = 0;
		if(StringUtils.isEmpty(news.getNewsTitle()) || news.getNewsTitle().trim().length() < 1) {
			return -2;
		}
		if(news.getNewsTitle().length() > 50) return -3;
		VendorNewsDetail vendorNews = vendorNewsDetailRepository.findOneByNewsID(news.getNewsID());
		if(vendorNews == null) {
			vendorNews = new VendorNewsDetail();
			vendorNews.setStartingDate(LocalDateTime.now());
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
		LocalDateTime fromdate = StringUtils.isEmpty(news.getFromDate()) ? null : LocalDateTime.parse(news.getFromDate()+" 00:00:00", formatter);
		LocalDateTime todate = StringUtils.isEmpty(news.getToDate()) ? null : LocalDateTime.parse(news.getToDate()+" 23:59:59", formatter);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");	
		
		vendorNews.setNewsTitle(news.getNewsTitle());
		vendorNews.setNewsContent(news.getNewsContent());
		vendorNews.setWholeSalerID(news.getWholeSalerID());
		vendorNews.setNewsType(news.getNewsType());
		vendorNews.setActive(news.getActive());
		vendorNews.setSortNo(news.getSortNo());
		vendorNews.setShowBanner(news.getShowBanner());
		vendorNews.setFromDate(fromdate);
		vendorNews.setToDate(todate);
		vendorNews.setLastUser(news.getLastUser());
		vendorNews.setLastModifiedDateTime(LocalDateTime.now());
		System.out.println(vendorNews);
		vendorNewsDetailRepository.save(vendorNews);
		result = 1;
		
		return result;
	}
	
	/**
	 * 
	 * SetVendorNewsInActive
	 * 
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param DelVendorNewsParameter
	 * @return Integer
	 */
	public Integer setVendorNewsInActive (DelVendorNewsParameter parameters) {
		String spName = "up_wa_SetVendorNewsInactive";
        List<Object> params = new ArrayList<Object>();
        params.add(parameters.getArrayNewsID());
        jdbcHelper.executeSP(spName, params, VendorNewsDetail.class);
		return 1;
	}

}
