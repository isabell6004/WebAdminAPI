package net.fashiongo.webadmin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.fashiongo.webadmin.dao.primary.VendorNewsDetailRepository;
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

/**
 * 
 * @author Incheol Jung
 */
@Service
public class MessageService extends ApiService {
	@Autowired
	VendorNewsDetailRepository vendorNewsDetailRepository;
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 9. 19.
	 * @author Incheol Jung
	 * @param parameters
	 * @return 
	 */
	public GetMessageResponse GetMessage(GetMessageParameter parameters) {
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
	public GetVendorNewsResponse GetVendorNews (GetVendorNewsParameter parameters) {
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
	 * 
	 * 
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	@Transactional("primaryTransactionManager")
	public Integer DelVendorNews (DelVendorNewsParameter parameters) {
		String spName = "up_wa_DeleteVendorNews";
        List<Object> params = new ArrayList<Object>();
        
        params.add(parameters.getArrayNewsID());
        
        List<Object> _result = jdbcHelper.executeSP(spName, params, VendorNewsDetail.class);
        
        System.out.println(_result);
		return 1;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	public VendorNewsDetail GetVendorNewsDetail (GetVendorNewsDetailParameter parameters) {
		VendorNewsDetail result = vendorNewsDetailRepository.findOneByNewsID(parameters.getNewsID());
		return result;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	public void SetVendorNews () {
		
	}
	
	/**
	 * 
	 * 
	 * 
	 * @since 2018. 10. 22.
	 * @author Dahye
	 * @param 
	 * @return 
	 */
	public void SetVendorNewsInActive () {
		
	}
}
