/**
 *
 */
package net.fashiongo.webadmin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.fashiongo.webadmin.data.model.message.GetRetailerRatingParameter;
import net.fashiongo.webadmin.data.model.message.GetVendorRatingParameter;
import net.fashiongo.webadmin.data.model.message.response.GetRetailerRatingResponse;
import net.fashiongo.webadmin.data.model.message.response.GetVendorRatingResponse;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.message.ResultMessage;
import net.fashiongo.webadmin.model.pojo.message.parameter.*;
import net.fashiongo.webadmin.model.pojo.message.response.GetMessageReplyResponse;
import net.fashiongo.webadmin.model.pojo.message.response.GetMessageResponse;
import net.fashiongo.webadmin.model.pojo.message.response.GetRetailerNewsResponse;
import net.fashiongo.webadmin.model.pojo.message.response.GetVendorNewsResponse;
import net.fashiongo.webadmin.model.pojo.vendor.response.GetContactUsResponse;
import net.fashiongo.webadmin.model.primary.MessageCategory;
import net.fashiongo.webadmin.model.primary.TblRetailerNews;
import net.fashiongo.webadmin.model.primary.VendorNewsView;
import net.fashiongo.webadmin.service.MessageService;
import net.fashiongo.webadmin.service.renewal.RenewalMessageService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/message", produces = "application/json")
public class MessageController {

    final MessageService messageService;

    final RenewalMessageService renewalMessageService;

    public MessageController(MessageService messageService, RenewalMessageService renewalMessageService) {
        this.messageService = messageService;
        this.renewalMessageService = renewalMessageService;
    }

    @PostMapping("getmessage")
    public JsonResponse<GetMessageResponse> getMessage(
            @Valid @RequestBody GetMessageParameter parameters) {
        GetMessageResponse result = messageService.getMessage(parameters);
        return JsonResponse.success(result);
    }

    @PostMapping("getvendornews")
    public JsonResponse<GetVendorNewsResponse> getVendorNews(@RequestBody GetVendorNewsParameter parameters) {
        GetVendorNewsResponse result = messageService.getVendorNews(parameters);
        return JsonResponse.success(result);
    }

    @PostMapping("delvendornews")
    public JsonResponse<Integer> delVendorNews(@RequestBody DelVendorNewsParameter parameters) {
        Integer result = messageService.delVendorNews(parameters);
        return JsonResponse.success(result);
    }

    @PostMapping("getvendornewsdetail")
    public JsonResponse<VendorNewsView> getVendorNewsDetail(@RequestBody GetVendorNewsDetailParameter parameters) {
        VendorNewsView result = messageService.getVendorNewsDetail(parameters);
        return JsonResponse.success(result);
    }

    @PostMapping("setvendornews")
    public JsonResponse<Integer> setVendorNews(@RequestBody SetVendorNewsParameter parameters) {
        Integer result = messageService.setVendorNews(parameters.getVendorNews(), parameters.getSelectedVendor());
        return JsonResponse.success(result);
    }

    @PostMapping("setvendornewsinactive")
    public JsonResponse<Integer> setVendorNewsInActive(@RequestBody DelVendorNewsParameter parameters) {
        Integer result = messageService.setVendorNewsInActive(parameters);
        return JsonResponse.success(result);
    }

    @PostMapping("getretailernews")
    public JsonResponse<GetRetailerNewsResponse> getRetailerNews(@RequestBody GetRetailerNewsParameter parameters) {
        GetRetailerNewsResponse result = messageService.getRetailerNews(parameters);
        return JsonResponse.success(result);
    }

    @PostMapping("getretailernewsdetail")
    public JsonResponse<TblRetailerNews> getRetailerNewsDetail(@RequestBody GetRetailerNewsDetailParameter parameters) {
        TblRetailerNews result = messageService.getRetailerNewsDetail(parameters);
        return JsonResponse.success(result);
    }

    @PostMapping("setretailernews")
    public JsonResponse<ResultCode> setRetailerNews(@RequestBody SetRetailerNewsParameter parameters) {
        ResultCode result = messageService.setRetailerNews(parameters);
        return JsonResponse.success(result);
    }

    @PostMapping("delretailernews")
    public JsonResponse<ResultCode> delRetailerNews(@RequestBody List<Integer> parameters) {
        ResultCode result = messageService.delRetailerNews(parameters);
        return JsonResponse.success(result);
    }

    @PostMapping("getcontactus")
    public JsonResponse<GetContactUsResponse> getContactUs(
            @Valid @RequestBody GetContactUsParameter parameters) {
        GetContactUsResponse result = messageService.getContactUs(parameters);
        return JsonResponse.success(result);
    }

    @PostMapping("setcontactusreply")
    public JsonResponse<ResultCode> setContactUsReply(@RequestBody SetContactUsReplyParameter parameters) throws JsonProcessingException {
        ResultCode result = messageService.setContactUsReply(parameters);
        return JsonResponse.success(result);
    }

    @PostMapping("getmessagecategory")
    public JsonResponse<List<MessageCategory>> getMessageCategory() {
        List<MessageCategory> result = messageService.getMessageCategory();
        return JsonResponse.success(result);
    }

    @PostMapping("setmessage")
    public JsonResponse<ResultMessage> setMessage(@RequestBody SetMessageParameter parameters) {
        ResultMessage result = messageService.setMessage(parameters);
        return JsonResponse.success(result);
    }

    @PostMapping("setmessagereadyn")
    public JsonResponse<ResultCode> setMessageReadYN(@RequestBody SetMessageReadYNParameter parameters) {
        ResultCode result = messageService.setMessageReadYN(parameters);
        return JsonResponse.success(result);
    }

    @GetMapping("getmessagereply")
    public JsonResponse<GetMessageReplyResponse> getMessageReply(@RequestParam(value = "referenceid") Integer topReferenceID) {
        GetMessageReplyResponse result = messageService.getMessageReply(topReferenceID);
        return JsonResponse.success(result);
    }

    @PostMapping("getvendorrating")
    public JsonResponse<GetVendorRatingResponse> getVendorRating(
            @Valid @RequestBody GetVendorRatingParameter param) {
        GetVendorRatingResponse vendorRating = renewalMessageService.getVendorRating(param);
        return JsonResponse.success(vendorRating);
    }

    @PostMapping("getretailerrating")
    public JsonResponse<GetRetailerRatingResponse> getRetailerRating(
            @Valid @RequestBody GetRetailerRatingParameter param) {
        GetRetailerRatingResponse retailerRating = renewalMessageService.getRetailerRating(param);
        return JsonResponse.success(retailerRating);
    }

}
