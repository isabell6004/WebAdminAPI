package net.fashiongo.webadmin.controller.vendor;

import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.entity.primary.CodeVendorIndustryEntity;
import net.fashiongo.webadmin.data.entity.primary.SecurityUserEntity;
import net.fashiongo.webadmin.data.entity.primary.vendor.ProductColorRow;
import net.fashiongo.webadmin.data.model.buyer.SetAccountLockOutParameter;
import net.fashiongo.webadmin.data.model.vendor.*;
import net.fashiongo.webadmin.data.model.vendor.response.GetAssignedUserListResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorAdminAccountLogListResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorCodeNameCheckResponse;
import net.fashiongo.webadmin.data.model.vendor.response.GetVendorCommunicationListResponse;
import net.fashiongo.webadmin.model.pojo.buyer.parameter.SetModifyPasswordParameter;
import net.fashiongo.webadmin.model.pojo.common.PagedResult;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.parameter.GetVendorFormsListParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetVendorFormsParameter;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.*;
import net.fashiongo.webadmin.model.primary.SecurityUser;
import net.fashiongo.webadmin.model.primary.VendorAutocomplete;
import net.fashiongo.webadmin.model.primary.VendorContent;
import net.fashiongo.webadmin.service.CacheService;
import net.fashiongo.webadmin.service.UserService;
import net.fashiongo.webadmin.service.VendorService;
import net.fashiongo.webadmin.service.renewal.RenewalVendorService;
import net.fashiongo.webadmin.service.vendor.VendorInfoService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author roy
 */
@RestController
@RequestMapping(value = "/vendor", produces = "application/json")
@Slf4j
public class VendorController {

    private final VendorService vendorService;
    private final RenewalVendorService renewalVendorService;
    private final CacheService cacheService;
    private final UserService userService;
    private final VendorInfoService vendorInfoService;

    public VendorController(VendorService vendorService,
                            RenewalVendorService renewalVendorService,
                            CacheService cacheService,
                            UserService userService,
                            VendorInfoService vendorInfoService) {
        this.vendorService = vendorService;
        this.renewalVendorService = renewalVendorService;
        this.cacheService = cacheService;
        this.userService = userService;
        this.vendorInfoService = vendorInfoService;
    }


    /**
     * 05/11/2020 by LeeDongSeung
     */
    @GetMapping(value = "/getvendorcompanyname/{wid}")
    public JsonResponse<String> getVendorName(@PathVariable("wid") int wid) {

        Optional<net.fashiongo.webadmin.model.primary.Vendor> vendor = vendorService.getVendorByWholeSalerIdAndActive(wid);
        JsonResponse<String> response = vendor.map(v -> new JsonResponse<>(true, null, v.getCompanyName()))
                .orElseGet(() -> new JsonResponse<>(false, "cannot find companyname", null));
        return response;
    }

    /**
     * Get vendor list
     *
     * @return vendor list
     * @author roy
     * @since 2018. 10. 15.
     */
    @RequestMapping(value = "getvendorlistall", method = RequestMethod.POST)
    public JsonResponse<List<Vendor>> getVendorListAll() {

        List<Vendor> vendors = renewalVendorService.getVendorListAll();
        return new JsonResponse<List<Vendor>>(true, null, 0, vendors);
    }

    /**
     * Get ProductList
     *
     * @param parameters
     * @return
     * @author Incheol Jung
     * @since 2018. 10. 29.
     */
    @RequestMapping(value = "getproductlist", method = RequestMethod.POST)
    public JsonResponse<VendorProductListResponse> getProductList(@RequestBody GetProductListParameter parameters) {
        JsonResponse<VendorProductListResponse> result = new JsonResponse<>(true, null, null);

        VendorProductListResponse products = renewalVendorService.getProductList(parameters);
        result.setData(products);

        return result;
    }

    @RequestMapping(value = "getproductcolor", method = RequestMethod.POST)
    public JsonResponse<List<ProductColorRow>> getProductColor(@RequestBody GetProductColorParameter parameters) {
        JsonResponse<List<ProductColorRow>> result = new JsonResponse<>(true, null, null);

        result.setData(renewalVendorService.getProductColor(parameters.getProductid()));

        return result;
    }

    /**
     * SetVendorRatingActive
     *
     * @param SetVendorRatingActiveParameter
     * @return DeleteCommunicationReasonResponse
     * @author Dahye
     * @since 2018. 11. 19.
     */
    @RequestMapping(value = "setvendorratingactive", method = RequestMethod.POST)
    public JsonResponse<Integer> setVendorRatingActive(@RequestBody SetVendorRatingActiveParameter parameters) {
        Integer result = vendorService.setVendorRatingActive(parameters);
        return new JsonResponse<Integer>(true, null, result);
    }

    /**
     * Description Example
     *
     * @param parameters
     * @return
     * @author Reo
     * @since 2018. 11. 13.
     */
    @RequestMapping(value = "getvendorformsList", method = RequestMethod.POST)
    public JsonResponse<VendorFormListResponse> getVendorFormsList(@RequestBody GetVendorFormsListParameter parameters) {
        JsonResponse<VendorFormListResponse> results = new JsonResponse<>(true, null, null);
        results.setData(renewalVendorService.getVendorFormsList(parameters));
        return results;
    }

    /**
     * Description Example
     *
     * @param parameters
     * @return
     * @author Reo
     * @since 2018. 11. 13.
     */
    @RequestMapping(value = "setvendorform", method = RequestMethod.POST)
    public JsonResponse<ResultCode> SetVendorForms(@RequestBody SetVendorFormsParameter parameters) {
        JsonResponse<ResultCode> results = new JsonResponse<ResultCode>(true, null, 0, null);

        ResultCode result = vendorService.SetVendorForms(parameters);

        results.setData(result);
        return results;
    }

    /**
     * SetBuyerRatingActive
     *
     * @param SetBuyerRatingActiveParameter
     * @return
     * @author Dahye
     * @since 2018. 11. 19.
     */
    @RequestMapping(value = "setbuyerratingactive", method = RequestMethod.POST)
    public JsonResponse<String> setBuyerRatingActive(@RequestBody SetBuyerRatingActiveParameter parameters) {
        Integer result = vendorService.setBuyerRatingActive(parameters);
        return new JsonResponse<String>(true, null, result, null);
    }

    /**
     * Description Example
     *
     * @param parameters
     * @return
     * @author Reo
     * @since 2018. 11. 14.
     */
    @RequestMapping(value = "delvendorform", method = RequestMethod.POST)
    public JsonResponse<ResultCode> delVendorForm(@RequestBody DelVendorFormParameter parameters) {
        JsonResponse<ResultCode> results = new JsonResponse<ResultCode>(true, null, 0, null);

        ResultCode result = vendorService.delVendorForm(parameters);

        results.setData(result);
        return results;
    }

    /**
     * Description Example
     *
     * @param parameters
     * @return
     * @author Reo
     * @since 2018. 11. 23.
     */
    @RequestMapping(value = "setvendorcreditcard", method = RequestMethod.POST)
    public JsonResponse<String> setVendorCreditCard(@RequestBody SetVendorCreditCardParameter parameters) {
        ResultCode result = vendorService.setVendorCreditCard(parameters);
        return new JsonResponse<String>(true, result.getResultMsg(), result.getResultCode(), null);
    }

    /**
     * Description Example
     *
     * @param wholeSalerID
     * @return
     * @author Reo
     * @since 2018. 12. 14.
     */
    @RequestMapping(value = "getvendordetailinfodata", method = RequestMethod.GET)
    public JsonResponse<net.fashiongo.webadmin.data.model.vendor.response.GetVendorDetailInfoDataResponse> getVendorDetailInfoData(@RequestParam(value = "wid") Integer wholeSalerID) {
        JsonResponse<net.fashiongo.webadmin.data.model.vendor.response.GetVendorDetailInfoDataResponse> results = new JsonResponse<net.fashiongo.webadmin.data.model.vendor.response.GetVendorDetailInfoDataResponse>(true, null, null);

        net.fashiongo.webadmin.data.model.vendor.response.GetVendorDetailInfoDataResponse vendorDetailInfoData = renewalVendorService.getVendorDetailInfoData(wholeSalerID);
        results.setData(vendorDetailInfoData);

        return results;
    }

    @GetMapping(value = "/autocomplete/{prefix:.+}")
    public JsonResponse<List<VendorAutocomplete>> getVendorsAutoomplete(@PathVariable("prefix") String prefix) {
        JsonResponse<List<VendorAutocomplete>> response = new JsonResponse<>(false, null, null);

        try {
            List<VendorAutocomplete> vendors = vendorService.getVendorsAutoomplete(prefix);
            response.setSuccess(true);
            response.setData(vendors);
        } catch (Exception ex) {
            log.error("Exception Error: ", ex);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    /**
     * @author Kenny/Kyungwoo
     * @since 2019-04-15
     */
    @GetMapping(value = "mediarequests")
    public JsonResponse<PagedResult<VendorContent>> getMediaRequests(
            @RequestParam(value = "pagenum", required = false) String pagenum,
            @RequestParam(value = "pagesize", required = false) String pagesize,
            @RequestParam(value = "company", required = false) String company,
            @RequestParam(value = "contentfileid", required = false) String contentfileid,
            @RequestParam(value = "datefrom", required = false) String datefrom,
            @RequestParam(value = "dateto", required = false) String dateto,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "status", required = false) String status) {
        JsonResponse<PagedResult<VendorContent>> response = new JsonResponse<>(false, null, null);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HH:mm:ss");
            PagedResult<VendorContent> result = vendorService.getVendorContents(
                    StringUtil.isNullOrEmpty(pagenum) ? null : Integer.parseInt(pagenum),
                    StringUtil.isNullOrEmpty(pagesize) ? null : Integer.parseInt(pagesize),
                    company,
                    StringUtil.isNullOrEmpty(contentfileid) ? null : Integer.parseInt(contentfileid),
                    StringUtil.isNullOrEmpty(datefrom) ? null : LocalDateTime.parse(datefrom + " 00:00:00", formatter),
                    StringUtil.isNullOrEmpty(dateto) ? null : LocalDateTime.parse(dateto + " 23:59:59", formatter),
                    StringUtil.isNullOrEmpty(type) ? null : Integer.parseInt(type),
                    StringUtil.isNullOrEmpty(status) ? null : Integer.parseInt(status));

            response.setSuccess(true);
            response.setData(result);
        } catch (Exception ex) {
            log.error("Exception Error: ", ex);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    /**
     * @author Kenny/Kyungwoo
     * @since 2019-04-16
     */
    @PostMapping(value = "mediarequest/{vendorContentId}/approve")
    public JsonResponse<String> approveMediaRequest(@PathVariable("vendorContentId") String vendorContentId) {
        JsonResponse<String> response = new JsonResponse<>(false, null, null);
        try {
            vendorService.approveVendorContent(Integer.parseInt(vendorContentId));
            response.setSuccess(true);
        } catch (Exception ex) {
            log.error("Exception Error: ", ex);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    /**
     * @author Kenny/Kyungwoo
     * @since 2019-04-16
     */
    @PostMapping(value = "mediarequest/{vendorContentId}/deny")
    public JsonResponse<String> denyMediaRequest(
            @PathVariable("vendorContentId") String vendorContentId,
            @RequestBody Map<String, String> body) {
        JsonResponse<String> response = new JsonResponse<>(false, null, null);
        try {
            vendorService.denyVendorContent(Integer.parseInt(vendorContentId), body.get("reason"));
            response.setSuccess(true);
        } catch (Exception ex) {
            log.error("Exception Error: ", ex);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    /**
     * @author Kenny/Kyungwoo
     * @since 2019-04-18
     */
    @GetMapping(value = "getassignedusers")
    public JsonResponse<List<SecurityUser>> getAssignedUsers() {
        JsonResponse<List<SecurityUser>> response = new JsonResponse<>(false, null, null);
        try {
            response.setData(vendorService.getAssignedUsers());
            response.setSuccess(true);
        } catch (Exception ex) {
            log.error("Exception Error: ", ex);
            response.setMessage(ex.getMessage());
        }
        return response;
    }

    @PostMapping(value = "getvwpaymentmethodsforvendor")
    public JsonResponse<List<GetVwPaymentMethodsForVendor>> getvwpaymentmethodsforvendor(@RequestBody GetVwPaymentMethodsForVendorParameter parameter) {
        JsonResponse<List<GetVwPaymentMethodsForVendor>> response = new JsonResponse<>();

        List<GetVwPaymentMethodsForVendor> data = renewalVendorService.getVwPaymentMethodsForVendor(parameter);

        response.setSuccess(true);
        response.setData(data);

        return response;
    }

    @PostMapping(value = "getvwshipmethodsforvendor")
    public JsonResponse<List<GetVwShipMethodsForVendor>> getvwshipmethodsforvendor(@RequestBody GetVwShipMethodsForVendorParameter parameter) {
        JsonResponse<List<GetVwShipMethodsForVendor>> response = new JsonResponse<>();

        List<GetVwShipMethodsForVendor> data = renewalVendorService.getVwShipMethodsForVendor(parameter);

        response.setSuccess(true);
        response.setData(data);

        return response;
    }

    @PostMapping(value = "setnewpassword")
    public JsonResponse setnewpassword(@RequestBody SetNewPasswordParameter param) {
        SetModifyPasswordParameter parameter = new SetModifyPasswordParameter();
        parameter.setUserName(param.getUserid());
        parameter.setNewPassword(param.getNewpassword());

        ResultCode result = userService.resetPassword(parameter);

        return new JsonResponse<String>(result.getSuccess(), result.getResultMsg(), result.getResultCode(), null);
    }

    @PostMapping(value = "getvendorindustry")
    public JsonResponse<List<CodeVendorIndustryEntity>> getvendorindustry() {
        JsonResponse<List<CodeVendorIndustryEntity>> response = new JsonResponse<>(false, null, null);

        try {
            response.setData(renewalVendorService.getCodeVendorIndustryEntity());
            response.setSuccess(true);
        } catch (Exception ex) {
            log.error("Exception Error: {}", ex);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @PostMapping(value = "setaccountlockout")
    public Integer setaccountlockout(@RequestBody SetAccountLockOutParameter param) {
        Integer wholeSalerID = param.getWholeSalerID() == null ? 0 : param.getWholeSalerID();
        Boolean active = param.getActive() == null ? false : param.getActive();

        Integer result = renewalVendorService.setAccountLockOut(active, wholeSalerID);

        cacheService.cacheEvictVendor(wholeSalerID);

        return result;
    }

    @PostMapping(value = "setaccountlockoutsubaccount")
    public Integer setaccountlockoutsubaccount(@RequestBody SetAccountLockOutParameter param) {
        Integer wholeSalerID = param.getWholeSalerID() == null ? 0 : param.getWholeSalerID();
        Boolean active = param.getActive() == null ? false : param.getActive();

        Integer result = renewalVendorService.setAccountLockOutSubAccount(active, wholeSalerID);

        return result;
    }

    @PostMapping(value = "setvendorcommunication")
    public ResultCode setvendorcommunication(@RequestBody SetVendorCommunicationParameter param) {
        Integer communitaionID = param.getCommunicationID() == null ? 0 : param.getCommunicationID();
        String notes = StringUtils.isEmpty(param.getNotes()) ? "" : param.getNotes();
        Integer wid = param.getWid() == null ? 0 : param.getWid();

        ResultCode result = renewalVendorService.setVendorCommunication(communitaionID, notes, wid);

        cacheService.cacheEvictVendor(wid);

        return result;
    }

    @PostMapping(value = "getvendorcommunicationlist")
    public JsonResponse<GetVendorCommunicationListResponse> getvendorcommunicationlist(@RequestBody GetVendorCommunicationListParameter param) {
        JsonResponse<GetVendorCommunicationListResponse> response = new JsonResponse<>(false, null, null);
        Integer wid = param.getWid() == null ? 0 : param.getWid();

        try {
            List<VendorCommunicationList> vclist = renewalVendorService.getVendorCommunicationList(wid);

            response.setSuccess(true);
            response.setData(GetVendorCommunicationListResponse.builder().vclist(vclist).build());
        } catch (Exception ex) {
            log.error("Exception Error: {}", ex);
            response.setMessage(ex.getMessage());
        }

        return response;
    }

    @PostMapping(value = "delvendorcommunication")
    public ResultCode delvendorcommunication(@RequestBody DelVendorCommunicationParameter param) {
        Integer communicationID = param.getCommunicationID() == null ? 0 : param.getCommunicationID();

        ResultCode result = renewalVendorService.delVendorCommunication(communicationID);

        return result;
    }

    @PostMapping(value = "setentityactionlog")
    public Integer setentityactionlog(@RequestBody SetEntityActionLogParameter param) {
        Integer entityTypeID = param.getEntityTypeID() == null ? 0 : param.getEntityTypeID();
        Integer wholeSalerID = param.getWholeSalerID() == null ? 0 : param.getWholeSalerID();
        Integer actionID = param.getActionID() == null ? 0 : param.getActionID();

        // TODO
        Integer result = vendorInfoService.setEntityActionLog(entityTypeID, wholeSalerID, actionID);

        return result;
    }

    @PostMapping(value = "vendordirnamecheck")
    public Boolean vendordirnamecheck(@RequestBody VendorDirNameCheckParameter param) {
        Integer wholeSalerID = param.getWholeSalerID() == null ? 0 : param.getWholeSalerID();
        String codeName = StringUtils.isEmpty(param.getDirName()) ? "" : param.getDirName();

        Boolean result = renewalVendorService.vendorDirNameCheck(wholeSalerID, codeName);

        return result;
    }

    @PostMapping(value = "vendorcodenamecheck")
    public JsonResponse<GetVendorCodeNameCheckResponse> vendorcodenamecheck(@RequestBody VendorCodeNameCheckParameter param) {
        JsonResponse<GetVendorCodeNameCheckResponse> response = new JsonResponse<>(false, null, null);

        Integer wholeSalerID = param.getWholeSalerID() == null ? 0 : param.getWholeSalerID();
        String codeName = StringUtils.isEmpty(param.getCodeName()) ? "" : param.getCodeName();

        try {
            Long result = renewalVendorService.vendorCodeNameCheck(wholeSalerID, codeName);

            response.setSuccess(true);
            response.setData(GetVendorCodeNameCheckResponse.builder().codeNameCount(result).build());
            response.setMessage("success");
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
            response.setMessage("fail");
        }

        return response;
    }

    @GetMapping(value = "getvendorsecurityusers")
    public JsonResponse<List<SecurityUserEntity>> getvendorsecurityusers() {
        JsonResponse<List<SecurityUserEntity>> response = new JsonResponse<>(false, null, null);

        try {
            List<SecurityUserEntity> result = renewalVendorService.getVendorSecurityUsers();

            response.setSuccess(true);
            response.setData(result);
            response.setMessage("success");
        } catch (Exception ex) {
            log.warn(ex.getMessage(), ex);
            response.setMessage("fail");
        }

        return response;
    }

    @PostMapping(value = "setvendorsettingaccount")
    public ResultCode setvendorsettingaccount(@RequestBody SetVendorSettingAccountParamter param) {
        Integer wid = param.getWid();

        ResultCode result = renewalVendorService.setVendorSettingAccount(wid);

        return result;
    }

    @GetMapping(value = "getvendoradminaccountlist")
    public JsonResponse getvendoradminaccountlist(@RequestParam(value = "WholeSalerID") Integer wholeSalerID) {
        Integer wid = wholeSalerID == null ? 0 : wholeSalerID;

        JsonResponse response = renewalVendorService.getVendorAdminAccountList(wid);

        return response;
    }

    @GetMapping(value = "getvendoradminaccountloglist")
    public JsonResponse<GetVendorAdminAccountLogListResponse> getvendoradminaccountloglist(
            @RequestParam(value = "pagenum") Integer pagenum,
            @RequestParam(value = "pagesize") Integer pagesize,
            @RequestParam(value = "WholeSalerID") Integer wholeSalerID,
            @RequestParam(value = "UserID") String userID,
            @RequestParam(value = "Date") String dateString,
            @RequestParam(value = "IPAddress") String ipAddress,
            @RequestParam(value = "orderby") String orderBy
    ) {
        Integer pageNum = pagenum == null ? 0 : pagenum;
        Integer pageSize = pagesize == null ? 0 : pagesize;
        Integer wid = wholeSalerID == null ? 0 : wholeSalerID;
        String uID = StringUtils.isEmpty(userID) ? "" : userID;

        dateString = dateString.replace("%2F", "/");

        LocalDate dateLocalDate = StringUtils.isEmpty(dateString) ? null : LocalDate.parse(dateString, DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        ipAddress = StringUtils.isEmpty(ipAddress) ? "" : ipAddress;

        JsonResponse<GetVendorAdminAccountLogListResponse> response = new JsonResponse<>(false, null, null);

        try {
            GetVendorAdminAccountLogListResponse data = renewalVendorService.getVendorAdminAccountLogList(pageNum, pageSize, wid, uID, dateLocalDate, ipAddress, orderBy);
            response.setSuccess(true);
            response.setData(data);
            response.setMessage("success");
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            response.setMessage("fail");
        }

        return response;
    }

    @PostMapping(value = "getassigneduserlist")
    public JsonResponse<GetAssignedUserListResponse> getassigneduserlist() {
        JsonResponse<GetAssignedUserListResponse> response = new JsonResponse<GetAssignedUserListResponse>(false, null, null);

        try {
            GetAssignedUserListResponse data = renewalVendorService.getAssignedUserList();

            response.setSuccess(true);
            response.setData(data);
            response.setMessage("success");
        } catch (Exception e) {
            log.warn(e.getMessage(), e);

            response.setMessage("fail");
        }

        return response;
    }

    @RequestMapping(value = "setretailerratingactive", method = RequestMethod.POST)
    public JsonResponse<Integer> setRetailerRatingActive(@RequestBody SetRetailerRatingActiveParameter parameters) {
        Integer result = renewalVendorService.setRetailerRatingActive(parameters);
        return new JsonResponse<Integer>(true, null, result);
    }
}
	
