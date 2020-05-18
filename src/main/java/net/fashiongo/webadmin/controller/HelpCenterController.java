package net.fashiongo.webadmin.controller;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.data.model.helpcenter.BoardBulkSaveParameter;
import net.fashiongo.webadmin.data.model.helpcenter.BoardCategoryParameter;
import net.fashiongo.webadmin.data.model.helpcenter.BoardFaqBulkSaveParameter;
import net.fashiongo.webadmin.data.model.helpcenter.BoardTopicParameter;
import net.fashiongo.webadmin.data.model.helpcenter.response.*;
import net.fashiongo.webadmin.service.HelpCenterService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/helpcenter", produces = "application/json")
public class HelpCenterController {
    @Autowired
    private HelpCenterService helpCenterService;

    @GetMapping("/getcategorygroups")
    public JsonResponse<List<CategoryGroupsResponse>> getCategoryGroups() {
        List<CategoryGroupsResponse> data = helpCenterService.getCategoryGroups();
        return new JsonResponse<>(true, null, data);
    }

    @GetMapping("/getcategoriesandtopics")
    public JsonResponse<List<CategoryAndTopicResponse>> getCategoriesAndTopics(@RequestParam(value = "categoryGroupId") int categoryGroupId) {
        List<CategoryAndTopicResponse> data = helpCenterService.getCategoriesAndTopics(categoryGroupId);
        return new JsonResponse<>(true, null, data);
    }

    @GetMapping("/getcategory")
    public JsonResponse<CategoryResponse> getCategory(@RequestParam(value = "categoryId") int categoryId) {
        CategoryResponse data = helpCenterService.getCategory(categoryId);
        return new JsonResponse<>(true, null, data);
    }

    @GetMapping("/getfaqs")
    public JsonResponse<List<FaqsResponse>> getFaqs(@RequestParam(value = "categoryGroupId") int categoryGroupId) {
        List<FaqsResponse> data = helpCenterService.getFaqs(categoryGroupId);
        return new JsonResponse<>(true, null, data);
    }

    @GetMapping("/gettopic")
    public JsonResponse<TopicResponse> getTopic(@RequestParam(value = "topicId") int topicId) {
        TopicResponse data = helpCenterService.getTopic(topicId);
        return new JsonResponse<>(true, null, data);
    }

    @PostMapping("/savecategory")
    public JsonResponse<Integer> saveCategory(@RequestPart("category") BoardCategoryParameter boardCategoryParameter,
                                              @RequestPart(value = "colorIconImageFile", required = false) MultipartFile colorIconImageFile,
                                              @RequestPart(value = "greyIconImageFile", required = false) MultipartFile greyIconImageFile) throws IOException {
        Integer categoryId = helpCenterService.saveCategory(boardCategoryParameter, colorIconImageFile, greyIconImageFile);
        return new JsonResponse<>(true, null, categoryId);
    }

    @PatchMapping("/setcategory/{categoryId}")
    public JsonResponse<Void> setCategory(@PathVariable int categoryId,
                                          @RequestPart("category") BoardCategoryParameter boardCategoryParameter,
                                          @RequestPart(value = "colorIconImageFile", required = false) MultipartFile colorIconImageFile,
                                          @RequestPart(value = "greyIconImageFile", required = false) MultipartFile greyIconImageFile) throws IOException {
        helpCenterService.setCategory(categoryId, boardCategoryParameter, colorIconImageFile, greyIconImageFile);
        return new JsonResponse<>(true, null, null);
    }

    @PostMapping("/savetopic")
    public JsonResponse<Integer> saveTopic(@RequestBody BoardTopicParameter boardTopicParameter) {
        Integer topicId = helpCenterService.saveTopic(boardTopicParameter);
        return new JsonResponse<>(true, null, topicId);
    }

    @PatchMapping("/settopic/{topicId}")
    public JsonResponse<Void> setTopic(@PathVariable int topicId,
                                       @RequestBody BoardTopicParameter boardTopicParameter) {
        helpCenterService.setTopic(topicId, boardTopicParameter);
        return new JsonResponse<>(true, null, null);
    }

    @GetMapping("/getfeedbacks")
    public JsonResponse<List<FeedbacksResponse>> getTopicFeedbacks(@RequestParam("topicId") int topicId) {
        List<FeedbacksResponse> data = helpCenterService.getFeedbacks(topicId);
        return new JsonResponse<>(true, null, data);
    }

    @GetMapping("/getreasons")
    public JsonResponse<List<OtherReasonsResponse>> getOtherReasons(@RequestParam("topicId") int topicId) {
        List<OtherReasonsResponse> data = helpCenterService.getOtherReasons(topicId);
        return new JsonResponse<>(true, null, data);
    }

    @PatchMapping("/savefaqsorder")
    public JsonResponse<Void> saveFaqOrder(@RequestBody List<BoardFaqBulkSaveParameter> boardFaqBulkSaveParameterList) {
        helpCenterService.saveFaqOrder(boardFaqBulkSaveParameterList);
        return new JsonResponse<>(true, null, null);
    }

    @PatchMapping("/savecategoriesandtopicsorder")
    public JsonResponse<Void> saveCategoriesAndTopicsOrder(@RequestBody BoardBulkSaveParameter boardBulkSaveParameter) {
        helpCenterService.saveCategoriesAndTopicsOrder(boardBulkSaveParameter);
        return new JsonResponse<>(true, null, null);
    }
}
