package net.fashiongo.webadmin.service;

import net.fashiongo.webadmin.dao.primary.*;
import net.fashiongo.webadmin.data.entity.primary.VendorEntity;
import net.fashiongo.webadmin.model.pojo.common.PagedResult;
import net.fashiongo.webadmin.model.pojo.common.Result;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.parameter.DelVendorBlockParameter;
import net.fashiongo.webadmin.model.pojo.parameter.GetVendorBlockListParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetVendorFormsParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.EditorsPickVendor;
import net.fashiongo.webadmin.model.pojo.vendor.*;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.*;
import net.fashiongo.webadmin.model.pojo.vendor.response.GetVendorCreditCardListResponse;
import net.fashiongo.webadmin.model.primary.*;
import net.fashiongo.webadmin.service.externalutil.HttpClientWrapper;
import net.fashiongo.webadmin.utility.Utility;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author roy
 */
@Service
public class VendorService extends ApiService {
	@Autowired
	private CreditCardTypeRepository creditCardTypeRepository;
	
	@Autowired
	private FashiongoFormRepository fashiongoFormRepository;
	
	@Autowired
	private WholeSalerRatingRepository wholeSalerRatingRepository;
	
	@Autowired
	private BuyerRatingActiveRepository buyerRatingActiveRepository;

	@Autowired
	private VendorCreditCardRepository vendorCreditCardRepository;
	
	@Autowired
	private LogCommunicationRepository logCommunicationRepository;
	
	@Autowired
	private VendorContentRepository vendorContentRepository;
	
	@Autowired
	private SecurityUserRepository securityUserRepository;

	@Autowired
    private VendorEntityRepository vendorEntityRepository;


    /**
     * getVendorCreditCardList
     *
     * @param
     * @return
     * @author Dahye
     * @since 2018. 11. 12.
     */
    @SuppressWarnings("unchecked")
    public GetVendorCreditCardListResponse getVendorCreditCardList(String orderby) {
        String spName = "up_wa_GetVendorCreditCard";
        List<Object> params = new ArrayList<Object>();
        GetVendorCreditCardListResponse result = new GetVendorCreditCardListResponse();
        params.add(orderby);

        List<Object> _result = jdbcHelper.executeSP(spName, params, VendorCreditCardList.class);

        result.setVendorCreditCardList((List<VendorCreditCardList>) _result.get(0));
        return result;
    }

    /**
     * getCreditCardType
     *
     * @param
     * @return
     * @author Dahye
     * @since 2018. 11. 12.
     */
    public List<CreditCardType> getCreditCardType() {

        return creditCardTypeRepository.findAllByActiveTrueOrderByCreditCardTypeID();
    }

    /**
     * DelVendorCreditCard
     *
     * @param DelVendorCreditcardParameter
     * @return
     * @author Dahye
     * @since 2018. 11. 19.
     */
    public void delVendorCreditCard(DelVendorCreditcardParameter parameters) {
        String spName = "up_wa_DeleteVendorCreditCard";
        List<Object> params = new ArrayList<Object>();
        params.add(parameters.getVendorCreditCardIDs());
        jdbcHelper.executeSP(spName, params);
    }

    /**
     * SetVendorRatingActive
     *
     * @param SetVendorRatingActiveParameter
     * @return DeleteCommunicationReasonResponse
     * @author Dahye
     * @since 2018. 11. 19.
     */
    public Integer setVendorRatingActive(SetVendorRatingActiveParameter parameters) {
        WholeSalerRating rating = wholeSalerRatingRepository.findOneByWholeSalerRatingID(parameters.getWid());
        rating.setActive(parameters.getActive());
        wholeSalerRatingRepository.save(rating);
        return 1;
    }

    /**
     * Description Example
     *
     * @return
     * @author Reo
     * @since 2018. 11. 13.
     */
	@Transactional(value = "primaryTransactionManager")
	public ResultCode SetVendorForms(SetVendorFormsParameter parameters) {
		ResultCode result = new ResultCode(false, 0, null);
		FashiongoForm fgForm = new FashiongoForm();
		
		String fileName = validateFileName(parameters.getAttachment());
		
		if (fileName == null) {
			result.setResultCode(0);
			result.setSuccess(false);
			result.setResultMsg("filename error");
		}
		else {
			switch(parameters.getType()) {
				case "Add":
					fgForm.setFormName(parameters.getFormName());
					fgForm.setMemo(parameters.getMemo());
					fgForm.setAttachment(fileName);
					fgForm.setCreatedBy(Utility.getUsername());
					fgForm.setCreatedOn(LocalDateTime.now());
					fgForm.setModifiedBy(Utility.getUsername());
					fgForm.setModifiedOn(LocalDateTime.now());
					fashiongoFormRepository.save(fgForm);
					
					result.setResultCode(1);
					result.setSuccess(true);
					result.setResultMsg(MSG_INSERT_SUCCESS);
					break;
				default:
					fgForm = fashiongoFormRepository.findOneByFashionGoFormID(parameters.getFashionGoFormID());
					fgForm.setFormName(parameters.getFormName());
					fgForm.setMemo(parameters.getMemo());
					fgForm.setAttachment(fileName);
					fgForm.setModifiedBy(Utility.getUsername());
					fgForm.setModifiedOn(LocalDateTime.now());
					fashiongoFormRepository.save(fgForm);
					
					result.setResultCode(1);
					result.setSuccess(true);
					result.setResultMsg(MSG_UPDATE_SUCCESS);
					break;
			}
		}
		return result;
	}
	
    /**
     * SetBuyerRatingActive
     *
     * @param SetBuyerRatingActiveParameter
     * @return Integer
     * @author Dahye
     * @since 2018. 11. 19.
     */
    public Integer setBuyerRatingActive(SetBuyerRatingActiveParameter parameters) {
        RetailerRating rating = buyerRatingActiveRepository.findOneByRetailerRatingID(parameters.getWid());
        rating.setActive(parameters.getActive());
        buyerRatingActiveRepository.save(rating);
        return 1;
    }

    /**
     * Description Example
     *
     * @param parameters
     * @return
     * @author Reo
     * @since 2018. 11. 14.
     */
    public ResultCode delVendorForm(DelVendorFormParameter parameters) {
        ResultCode result = new ResultCode(false, 0, null);
        String spName = "up_wa_DeleteFashionGoForm";
        List<Object> params = new ArrayList<Object>();
        params.add(parameters.getFashionGoFormIDs());

        List<Object> _result = jdbcHelper.executeSP(spName, params, Result.class);
        result.setResultCode(1);
        result.setResultMsg(MSG_DELETE_SUCCESS);
        result.setSuccess(true);
        return result;
    }

    /**
     * Description Example
     *
     * @param parameters
     * @return
     * @author Reo
     * @since 2018. 11. 23.
     */
    @Transactional(value = "primaryTransactionManager")
    public ResultCode setVendorCreditCard(SetVendorCreditCardParameter parameters) {
        ResultCode result = new ResultCode(false, 0, null);

        VendorCreditCard vcc = null;
        VendorEntity vendor = vendorEntityRepository.findByVendorId(parameters.getVendorID().longValue());
        String guid = null;

        switch (parameters.getType()) {
            case "Add":
                vcc = new VendorCreditCard();
                vcc.setCreatedOn(LocalDateTime.now());
                vcc.setCreatedBy(Utility.getUsername());
                break;
            default:
                vcc = Optional.ofNullable(vendorCreditCardRepository.findOneByVendorCreditCardID(parameters.getVendorCreditCardID()))
                    .orElseThrow(()->new IllegalArgumentException("No vendor credit card exists id : "+parameters.getVendorID()));
                break;
        }
        vcc.setWholeSalerID(parameters.getVendorID());
        vcc.setCardTypeID(parameters.getCreditCardType());
        vcc.setLast4Digit(parameters.getCreditCard());
        vcc.setAttachment(parameters.getAttachment());
        vcc.setIsRecurring(parameters.getRecurring());
        vcc.setZipcode(parameters.getZipcode());
        vcc.setState(parameters.getState());
        vcc.setCity(parameters.getCity());
        vcc.setStreetNo(parameters.getStreet());
        vcc.setModifiedOn(LocalDateTime.now());
        vcc.setModifiedBy(Utility.getUsername());
        vendorCreditCardRepository.save(vcc);
        if (vendor != null) {
            guid = vendor.getGuid();
        }

        if (!StringUtils.isEmpty(guid)) {
            result.setResultCode(1);
            result.setResultMsg(guid);
        } else {
            result.setResultCode(0);
            result.setResultMsg(null);
        }
        return result;
    }

    /**
     * Description Example
     *
     * @param wholeSalerID
     * @return
     * @author Reo
     * @since 2018. 12. 14.
     */
    public List<LogCommunication> getVendorCommunicationList(Integer wholeSalerID) {
        List<LogCommunication> result = logCommunicationRepository.findByRetailerIDAndIsForVendorOrderByCommunicationIDDesc(wholeSalerID, true);

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

    /**
     * @author Kenny/Kyungwoo
     * @since 2019-04-15
     */
    public PagedResult<VendorContent> getVendorContents(Integer pagenum, Integer pagesize, String company,Integer contentfileid,
                                                        LocalDateTime datefrom, LocalDateTime dateto, Integer type, Integer filetype, Integer status) {
        return vendorContentRepository.getVendorContents(pagenum, pagesize, company,contentfileid, datefrom, dateto, type, filetype, status);
    }

    /**
     * @author Kenny/Kyungwoo
     * @since 2019-04-16
     */
    public void approveVendorContent(int id) throws Exception {
        Optional<VendorContent> vendorContent = vendorContentRepository.findById(id);
        if (!vendorContent.isPresent()) throw new Exception("It does not exist.");
        if (vendorContent.get().getStatusId() == 2) throw new Exception("It is already approved.");

        vendorContent.get().setStatusId(2);
        vendorContent.get().setApprovedOn(LocalDateTime.now());
        vendorContent.get().setApprovedBy(Utility.getUsername());

        vendorContentRepository.save(vendorContent.get());
    }

    /**
     * @author Kenny/Kyungwoo
     * @since 2019-04-16
     */
    public void denyVendorContent(int id, String reason) throws Exception {
        Optional<VendorContent> vendorContent = vendorContentRepository.findById(id);
        if (!vendorContent.isPresent()) throw new Exception("It does not exist.");
        if (vendorContent.get().getStatusId() == 3) throw new Exception("It is already denied.");
        vendorContent.get().setStatusId(3);
        vendorContent.get().setRejectedOn(LocalDateTime.now());
        vendorContent.get().setRejectedBy(Utility.getUsername());
        vendorContent.get().setIsActive(false);
        vendorContent.get().setRejectedReason(reason);
        vendorContentRepository.save(vendorContent.get());
    }

    /**
     * @author Kenny/Kyungwoo
     * @since 2019-04-18
     */
    public List<SecurityUser> getAssignedUsers() {
        return securityUserRepository.findAllMappedByVendor();
    }

    /**
     * @author Kenny/Kyungwoo
     * @since 2019-05-02
     */
    public List<EditorsPickVendor> getEditorsPickVendors() {
        List<VendorEntity> vendors = vendorEntityRepository.getEditorPickVendors();
        return EditorsPickVendor.create(vendors);
    }
    /**
     * Get vendor by wholeSalerId and Active
     *
     * 05/11/2020 LeeDongSeung
     */
    public VendorEntity getVendorByWholeSalerIdAndActive(int wid) {
        return vendorEntityRepository.findByWholeSalerIdAndActive((long) wid, true);
    }
}
