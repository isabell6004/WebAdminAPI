package net.fashiongo.webadmin.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.fashiongo.webadmin.dao.primary.*;
import net.fashiongo.webadmin.model.primary.*;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.message.MessageReply;
import net.fashiongo.webadmin.model.pojo.message.ResultMessage;
import net.fashiongo.webadmin.model.pojo.message.RetailerNews;
import net.fashiongo.webadmin.model.pojo.message.RetailerRating;
import net.fashiongo.webadmin.model.pojo.message.Total;
import net.fashiongo.webadmin.model.pojo.message.VendorNews;
import net.fashiongo.webadmin.model.pojo.message.VendorRating;
import net.fashiongo.webadmin.model.pojo.message.VwWaMessage;
import net.fashiongo.webadmin.model.pojo.message.parameter.DelVendorNewsParameter;
import net.fashiongo.webadmin.model.pojo.message.parameter.GetContactUsParameter;
import net.fashiongo.webadmin.model.pojo.message.parameter.GetMessageParameter;
import net.fashiongo.webadmin.model.pojo.message.parameter.GetRetailerNewsDetailParameter;
import net.fashiongo.webadmin.model.pojo.message.parameter.GetRetailerNewsParameter;
import net.fashiongo.webadmin.model.pojo.message.parameter.GetVendorNewsDetailParameter;
import net.fashiongo.webadmin.model.pojo.message.parameter.GetVendorNewsParameter;
import net.fashiongo.webadmin.model.pojo.message.parameter.GetVendorRatingParameter;
import net.fashiongo.webadmin.model.pojo.message.parameter.SetContactUsReplyParameter;
import net.fashiongo.webadmin.model.pojo.message.parameter.SetMessageParameter;
import net.fashiongo.webadmin.model.pojo.message.parameter.SetMessageReadYNParameter;
import net.fashiongo.webadmin.model.pojo.message.parameter.SetRetailerNewsParameter;
import net.fashiongo.webadmin.model.pojo.message.response.GetMessageReplyResponse;
import net.fashiongo.webadmin.model.pojo.message.response.GetMessageResponse;
import net.fashiongo.webadmin.model.pojo.message.response.GetRetailerNewsResponse;
import net.fashiongo.webadmin.model.pojo.message.response.GetRetailerRatingResponse;
import net.fashiongo.webadmin.model.pojo.message.response.GetVendorNewsResponse;
import net.fashiongo.webadmin.model.pojo.message.response.GetVendorRatingResponse;
import net.fashiongo.webadmin.model.pojo.vendor.response.GetContactUsResponse;
import net.fashiongo.webadmin.utility.HtmlUtility;
import net.fashiongo.webadmin.utility.HttpClient;
import net.fashiongo.webadmin.utility.JsonResponse;
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
	
	@Autowired
	ContactUsRepository contactUsRepository;
	
	@Autowired
	MessageCategoryRepository messageCategoryRepository;
	
	@Autowired
	MessageRepository messageRepository;
	
	@Autowired
	MessageMapRepository messageMapRepository;

	@Autowired
	@Qualifier("serviceJsonClient")
	private HttpClient httpClient;
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 20.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	public GetMessageResponse getMessage(GetMessageParameter parameters) {
		GetMessageResponse result = new GetMessageResponse();
		String spName = "up_wa_GetAdminMessage";
        List<Object> params = new ArrayList<Object>();
        
        params.add(parameters.getPagesize());
        params.add(parameters.getPagenum());
        params.add(parameters.getParent());
        params.add(parameters.getSendertypeid());
        params.add(parameters.getRecipienttypeid());
        params.add(null);
        params.add(null);
        if (StringUtils.isNotEmpty(parameters.getSender())) {
        	parameters.setSender(parameters.getSender().replace("'", "''"));
		}
        params.add(parameters.getSender());
        params.add(parameters.getTopic());
        params.add(parameters.getSubject());
        params.add(parameters.getPeriod());
        params.add(parameters.getFromdate());
        params.add(parameters.getTodate());
        params.add(parameters.getStatus());
        
        List<Object> _result = jdbcHelper.executeSP(spName, params, Total.class, VwWaMessage.class);
        
		result.setTotal((List<Total>)_result.get(0));
		result.setMessagelist((List<VwWaMessage>) _result.get(1));
		
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
	
		vendorNews.setNewsTitle(HtmlUtility.tagValidation(news.getNewsTitle(), false));
		vendorNews.setNewsContent(HtmlUtility.tagValidation(news.getNewsContent(), true));
		vendorNews.setWholeSalerID(news.getWholeSalerID());
		vendorNews.setNewsType(news.getNewsType());
		vendorNews.setActive(news.getActive());
		vendorNews.setSortNo(news.getSortNo());
		vendorNews.setShowBanner(news.getShowBanner());
		vendorNews.setFromDate(fromdate);
		vendorNews.setToDate(todate);
		//vendorNews.setLastUser(news.getLastUser());
		vendorNews.setLastUser(Utility.getUserInfo().getUsername());
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
		result.setRetailerNewsList((List<RetailerNews>) _result.get(1));
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
		LocalDateTime now = LocalDateTime.now();
		
		if(Utility.isNullOrEmpty(parameters.getNewsTitle()) || parameters.getNewsTitle().length() < 1) {
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
			
//			if(parameters.getToDate() != null) {
//				parameters.setToDate(((LocalDateTime) parameters.getToDate()).plusDays(1).plusSeconds(-1));
//			}
			
			retailerNews.setNewsTitle(HtmlUtility.tagValidation(parameters.getNewsTitle(), false));
			retailerNews.setNewsContent(HtmlUtility.tagValidation(parameters.getNewsContent(), true));
			retailerNews.setActive(Utility.isNullOrEmpty(parameters.getActive().toString()) ? "N" : parameters.getActive() == true ? "Y" : "N");
			retailerNews.setFromDate(parameters.getFromDate());
			retailerNews.setToDate(parameters.getToDate());
			retailerNews.setLastUser(Utility.getUserInfo().getUsername());
			retailerNews.setLastModifiedDateTime(now);
			retailerNews.setSortNo(parameters.getSortNo());
			retailerNews.setExternalLink(parameters.getExternalLink());
			retailerNews.setExternalUrl(parameters.getExternalUrl());
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
	

	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 16.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	public GetContactUsResponse getContactUs(GetContactUsParameter parameters) {
		GetContactUsResponse result = new GetContactUsResponse();
		String spName = "up_wa_GetContactUs";
		List<Object> params = new ArrayList<Object>();
		params.add(parameters.getPageSize());
		params.add(parameters.getPageNum());
		params.add(parameters.getSender());
		params.add(parameters.getEmail());
		params.add(parameters.getTopic());
		params.add(parameters.getPeriod());
		params.add(parameters.getFromDate());
		params.add(parameters.getToDate());
		List<Object> _result = jdbcHelper.executeSP(spName, params, Total.class, ContactUs.class);
		result.setTotal((List<Total>) _result.get(0));
		result.setContactUsList((List<ContactUs>)_result.get(1));
		
		return result;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 19.
	 * @author Reo
	 * @return
	 * @throws JsonProcessingException 
	 */
	@Transactional(value = "primaryTransactionManager")
	public ResultCode setContactUsReply(SetContactUsReplyParameter parameters) throws JsonProcessingException {
		ResultCode result = new ResultCode(false, 0, null);
		ContactUs cu = contactUsRepository.findOneByContactID(parameters.getContactID());
		cu.setReply(parameters.getReply());
		cu.setRepliedOn(LocalDateTime.now());
		cu.setRepliedBy(parameters.getRepliedBy());
		contactUsRepository.save(cu);
		
		String EmailSignatureCS = "Customer Support Team$$E-mail: info@fashiongo.net$http:// www.fashiongo.net$765 E. 12th St., #306 Los Angeles, CA 90021$Tel: 213-745-2667";
		String signature = EmailSignatureCS.replaceAll("$", "<br/>");
		String message = String.format("<div>{0}</div><div style='margin-top:20px;'>{1}</div><div style='margin-top:20px;'><b>You wrote your inquiry on FashionGo.net at {2} as follows:</b></div><div style=''font-style:italic; color:#666666;''>{3}</div>", parameters.getReply(), signature, cu.getCreatedOn(), cu.getMessage());
		String title = "Your FashionGo Inquiry";
		String recipient = cu.getEmail();
		String recipientName = cu.getName();
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("additionalInfo", "new {}");
		jsonObj.put("message", message);
		jsonObj.put("recipientEmailAddress", recipient);
		jsonObj.put("recipientName", recipientName);
		jsonObj.put("senderEmailAddress", "info@fashiongo.net");
		jsonObj.put("senderName", "FashionGo Team");
		jsonObj.put("subject", title);
		
		ObjectMapper mapper = new ObjectMapper();
		String uri = "/email/sendEmail";
		JsonResponse<?> ret = httpClient.postObject(uri, mapper.writeValueAsString(jsonObj));
		if (ret.isSuccess()) {
			result.setResultCode(1);
			result.setSuccess(true);
		}
		
		return result;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 20.
	 * @author Reo
	 * @return
	 */
	public List<MessageCategory> getMessageCategory() {
		List<MessageCategory> result = messageCategoryRepository.findByActiveOrderBySortNoRetailerDesc(true);
		
		return result;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 20.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	@Transactional(value = "primaryTransactionManager")
	public ResultMessage setMessage(SetMessageParameter parameters) {
		ResultMessage result = new ResultMessage();
		String[] idList = parameters.getRecipientidlist().split(",");
		String msgGuid = UUID.randomUUID().toString();

		String attachedFileName = validateFileName(parameters.getFilename());
		String attachedFileName2 = validateFileName(parameters.getFilename2());
		String attachedFileName3 = validateFileName(parameters.getFilename3());

		for (String id : idList)
        {
            if (Integer.parseInt(id) > 0)
            {
            	Message msg = new Message();
        		msg.setSenderID(parameters.getSenderid());
        		msg.setCreatedBy(parameters.getCreatedby());
        		msg.setModifiedBy(parameters.getCreatedby());
        		msg.setSenderTypeID(3);
        		msg.setTitle(parameters.getTitle());
        		msg.setBody(parameters.getContent());
        		msg.setActive(true);
        		msg.setMessageGUID(msgGuid);
				msg.setAttachedFileName(attachedFileName);
				msg.setAttachedFileName2(attachedFileName2);
				msg.setAttachedFileName3(attachedFileName3);
        		msg.setReferenceID(parameters.getReferenceid());
        		msg.setUpdatedOn(LocalDateTime.now());
        		msg.setCreatedOn(LocalDateTime.now());
        		
        		if(parameters.getTopreferenceid() > 0) {
        			msg.setTopReferenceID(parameters.getTopreferenceid());
        			
        			Message msgTop = messageRepository.findByMessageID(parameters.getTopreferenceid());
        			msgTop.setUpdatedOn(LocalDateTime.now());
        			msgTop.setHasNewReply(true);
        			messageRepository.save(msgTop);
        		}
        		
        		if(parameters.getTopic() > 0) msg.setMessageCategoryID(parameters.getTopic());
        		messageRepository.save(msg);
        		
            	MessageMap messageMap = new MessageMap();
                messageMap.setMessageID(msg.getMessageID());
                messageMap.setRecipientTypeID(parameters.getRecipienttypeid());
                messageMap.setRecipientID(Integer.parseInt(id));
                messageMap.setIsDeletedByRecipient(false);
                
                messageMapRepository.save(messageMap);
            }
        }
		
		result.setResult(1);
		result.setGuid(msgGuid);
		result.setAttachedFileName(attachedFileName);
		result.setAttachedFileName2(attachedFileName2);
		result.setAttachedFileName3(attachedFileName3);
		return result;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 21.
	 * @author Reo
	 * @param parameters
	 * @return
	 */
	@Transactional(value = "primaryTransactionManager")
	public ResultCode setMessageReadYN(SetMessageReadYNParameter parameters) {
		ResultCode result = new ResultCode(false, 0, null);
		MessageMap msgMap = messageMapRepository.findOneByMessageID(parameters.getMessageID());
		Message msg = messageRepository.findByMessageID(parameters.getMessageID());
		
        if(parameters.getReadYn().equals(true)) {
        	if(parameters.getHasReplyYN().equals(false)) {
        		msgMap.setReadOn(LocalDateTime.now());
        	}
        	msg.setHasNewReply(false);
        } else {
        	msgMap.setReadOn(null);
        	msg.setHasNewReply(true);
        }
        
        messageMapRepository.save(msgMap);
        messageRepository.save(msg);
        
        List<Message> messages = messageRepository.findByReferenceID(parameters.getMessageID());
        if(!CollectionUtils.isEmpty(messages)) {
        	List<Integer> messageIds = messages.stream().map(message -> message.getMessageID()).collect(Collectors.toList());
            List<MessageMap> messageMaps = messageMapRepository.findByMessageIDInAndReadOnIsNull(messageIds);
            for(MessageMap messageMap : messageMaps) {
            	messageMap.setReadOn(LocalDateTime.now());
            }
            
            messageMapRepository.saveAll(messageMaps);
        }
		
        result.setResultMsg(LocalDateTime.now().toString());
		return result;
	}
	
	/**
	 * 
	 * Description Example
	 * @since 2018. 11. 21.
	 * @author Reo
	 * @param topReferenceID
	 * @return
	 */
	public GetMessageReplyResponse getMessageReply(Integer topReferenceID) {
		GetMessageReplyResponse result = new GetMessageReplyResponse();
		String spName = "up_wa_GetMessageReply";
		List<Object> params = new ArrayList<Object>();
		params.add(topReferenceID);
		List<Object> _result = jdbcHelper.executeSP(spName, params, MessageReply.class);
		result.setMessageReplyList((List<MessageReply>)_result.get(0));
		
		return result;
	}
	
	/**
	 * getVendorRating
	 * 
	 * @since 2018. 11. 27.
	 * @author dahye
	 * @param GetVendorRatingParameter
	 * @return GetVendorRatingResponse
	 */
	public GetVendorRatingResponse getVendorRating(GetVendorRatingParameter param) {
		GetVendorRatingResponse result = new GetVendorRatingResponse();
		String spName = "up_wa_GetVendorInfoRating";
		List<Object> params = new ArrayList<Object>();
		params.add(param.getWholeSalerID());
		params.add(param.getRetailerID());
		params.add(param.getPageNum());
		params.add(param.getPageSize());
		params.add(param.getActive());
		params.add(param.getAdditional());
		params.add(param.getFromDate());
		params.add(param.getToDate());
		params.add(param.getOrderby());
		List<Object> _result = jdbcHelper.executeSP(spName, params, Total.class, VendorRating.class);
		result.setRecCnt((List<Total>) _result.get(0));
		result.setVendorRatingList((List<VendorRating>) _result.get(1));
		return result;
	}
	

	
	/**
	 * getRetailerRating
	 * 
	 * @since 2018. 11. 27.
	 * @author dahye
	 * @param GetVendorRatingParameter
	 * @return 
	 */
	public GetRetailerRatingResponse getRetailerRating(GetVendorRatingParameter param) {
		GetRetailerRatingResponse result = new GetRetailerRatingResponse();
		String spName = "up_wa_GetRetailerRating";
		List<Object> params = new ArrayList<Object>();
		params.add(param.getRetailerID());
		params.add(param.getWholeSalerID());
		params.add(param.getPageNum());
		params.add(param.getPageSize());
		params.add(param.getActive());
		params.add(param.getAdditional());
		params.add(param.getFromDate());
		params.add(param.getToDate());
		params.add(param.getOrderby());
		List<Object> _result = jdbcHelper.executeSP(spName, params, Total.class, RetailerRating.class);
		result.setRecCnt((List<Total>) _result.get(0));
		result.setRetailerRatingList((List<RetailerRating>) _result.get(1));
		return result;
	}

	private String validateFileName(String fileName) {
		if(fileName == null) {
			return null;
		}
		Set<String> extensionSet = Stream.of(".jpg", ".gif", ".png", ".pdf", ".xls", ".xlsx", ".doc", ".docx")
				.collect(Collectors.toCollection(HashSet::new));

		String fileExtension = fileName.substring(fileName.lastIndexOf('.')).toLowerCase();
		if(!extensionSet.contains(fileExtension)) {
			return null;
		}

		String[] fileNameBlackList = {"/", "\\", "%0", ";"};
		for(String wrongName : fileNameBlackList) {
			if(fileName.indexOf(wrongName) != -1) {
				return null;
			}
		}

		return fileName;
	}
}
