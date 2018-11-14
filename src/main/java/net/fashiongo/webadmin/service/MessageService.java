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

import net.fashiongo.webadmin.dao.primary.TblRetailerNewsRepository;
import net.fashiongo.webadmin.dao.primary.VendorNewsDetailRepository;
import net.fashiongo.webadmin.dao.primary.VendorNewsViewRepository;
import net.fashiongo.webadmin.model.pojo.Message;
import net.fashiongo.webadmin.model.pojo.ResultCode;
import net.fashiongo.webadmin.model.pojo.RetailerNews;
import net.fashiongo.webadmin.model.pojo.Total;
import net.fashiongo.webadmin.model.pojo.VendorNews;
import net.fashiongo.webadmin.model.pojo.parameter.DelVendorNewsParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetMessageParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetRetailerNewsDetailParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetRetailerNewsParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetVendorNewsDetailParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetVendorNewsParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetRetailerNewsParameter;
import net.fashiongo.webadmin.model.pojo.response.GetMessageResponse;
import net.fashiongo.webadmin.model.pojo.response.GetRetailerNewsResponse;
import net.fashiongo.webadmin.model.pojo.response.GetVendorNewsResponse;
import net.fashiongo.webadmin.model.primary.TblRetailerNews;
import net.fashiongo.webadmin.model.primary.VendorNewsDetail;
import net.fashiongo.webadmin.model.primary.VendorNewsView;
import net.fashiongo.webadmin.utility.Utility;

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
	
	@Autowired
	TblRetailerNewsRepository tblRetailerNewsRepository;
	
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
		Integer result = 1;
		String[] widList = selectedVendor.split(",", -1);
		
		List<VendorNewsDetail> newsList = new ArrayList<VendorNewsDetail>();
		VendorNewsDetail vendorNews = vendorNewsDetailRepository.findOneByNewsID(news.getNewsID());
		if(StringUtils.isEmpty(news.getNewsTitle()) || news.getNewsTitle().trim().length() < 1) {
			return -2;
		}
		if(news.getNewsTitle().length() > 50) return -3;
		if(widList.length > 0) {
			for(String wid : widList) {
				if(StringUtils.isNotEmpty(wid)) news.setWholeSalerID(Integer.parseInt(wid));
				newsList.add(setVendorNewsDetail(news, vendorNews));
			}
		} else {
			newsList.add(setVendorNewsDetail(news, vendorNews));
		}
		vendorNewsDetailRepository.saveAll(newsList);
		return result;
	}
	
	public VendorNewsDetail setVendorNewsDetail(VendorNewsDetail news, VendorNewsDetail vendorNews) {
		if(vendorNews == null) {
			vendorNews = new VendorNewsDetail();
			vendorNews.setStartingDate(LocalDateTime.now());
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
		LocalDateTime fromdate = StringUtils.isEmpty(news.getFromDate()) ? null : LocalDateTime.parse(news.getFromDate()+" 00:00:00", formatter);
		LocalDateTime todate = StringUtils.isEmpty(news.getToDate()) ? null : LocalDateTime.parse(news.getToDate()+" 23:59:59", formatter);
	
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
		
		return vendorNews;
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
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 10. 31.
	 * @author Reo
	 * @param parameter
	 * @return
	 */
	public GetRetailerNewsResponse getRetailerNews(GetRetailerNewsParameter parameters) {
		GetRetailerNewsResponse result = new GetRetailerNewsResponse();
		
		String spName = "up_wa_GetRetailerNews";
		List<Object> params = new ArrayList<Object>();
		params.add(parameters.getPageNum());
		params.add(parameters.getPageSize());
		params.add(parameters.getNewsTitle());
		params.add(parameters.getActive());
		params.add(parameters.getPeriod());
		params.add(parameters.getFromDate());
		params.add(parameters.getToDate());
		params.add(parameters.getOrderBy());
		List<Object> _result = jdbcHelper.executeSP(spName, params, Total.class, RetailerNews.class);
		
		result.setRecCnt((List<Total>) _result.get(0));
		result.setCodeDataList((List<RetailerNews>) _result.get(1));
		result.setSuccess(true);
		return result;
	}

	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 1.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	public TblRetailerNews getRetailerNewsDetail(GetRetailerNewsDetailParameter parameters) {
		TblRetailerNews result = tblRetailerNewsRepository.findOneByNewsID(parameters.getNewsID());
		
		return result;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 1.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	@Transactional(value = "primaryTransactionManager")
	public ResultCode setRetailerNews(SetRetailerNewsParameter parameters) {
		ResultCode result = new ResultCode(false, 0, null);
		net.fashiongo.webadmin.utility.Utility utl = new net.fashiongo.webadmin.utility.Utility();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		if(utl.isNullOrEmpty(parameters.getNewsTitle()) || parameters.getNewsTitle().length() < 1) {
			result.setResultCode(-2);
			result.setSuccess(false);
		} else if(parameters.getNewsTitle().length() > 100) {
			result.setResultCode(-3);
			result.setSuccess(false);
		} else {
			TblRetailerNews retailerNews = tblRetailerNewsRepository.findOneByNewsID(parameters.getNewsID());
			
			if(retailerNews == null) {
				retailerNews = new TblRetailerNews();
				retailerNews.setStartingDate(now);
			}
			
			if(parameters.getToDate() != null) {
				parameters.setToDate(((LocalDateTime) parameters.getToDate()).plusDays(1).plusSeconds(-1));
			}
			
			retailerNews.setNewsTitle(parameters.getNewsTitle());
			retailerNews.setNewsContent(parameters.getNewsContent());
			retailerNews.setActive(utl.isNullOrEmpty(parameters.getActive().toString()) ? "N" : parameters.getActive() == true ? "Y" : "N");
			retailerNews.setFromDate(parameters.getFromDate());
			retailerNews.setToDate(parameters.getToDate());
			retailerNews.setLastUser(Utility.getUserInfo().getUsername());
			retailerNews.setLastModifiedDateTime(now);
			retailerNews.setSortNo(parameters.getSortNo());
			tblRetailerNewsRepository.save(retailerNews);
			
			result.setResultCode(1);
			result.setSuccess(true);
			result.setResultMsg(MSG_SAVE_SUCCESS);
		}
		
		return result;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 2.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	@Transactional(value = "primaryTransactionManager")
	public ResultCode delRetailerNews(List<Integer> parameters) {
		ResultCode result = new ResultCode(false, 0, null);
		
		tblRetailerNewsRepository.deleteByNewsIDIn(parameters);
		
		result.setResultCode(1);
		result.setSuccess(true);
		result.setResultMsg(MSG_DELETE_SUCCESS);
		return result;
	}
}
