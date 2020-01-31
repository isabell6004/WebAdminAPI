package net.fashiongo.webadmin.controller.ads;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.model.ads.request.CollectionCategoryItemByDateRequest;
import net.fashiongo.webadmin.model.ads.request.CollectionCategoryItemCountsByDateRequest;
import net.fashiongo.webadmin.model.ads.response.CollectionCategoryItemByDateResponse;
import net.fashiongo.webadmin.model.ads.response.CollectionCategoryItemCountsByDateResponse;
import net.fashiongo.webadmin.service.ads.AdCollectionCategoryItemService;
import net.fashiongo.webadmin.utility.JsonResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("collection-categories/*/items")
public class AdCollectionCategoryController {

    private AdCollectionCategoryItemService adCollectionCategoryItemService;

    public AdCollectionCategoryController(AdCollectionCategoryItemService adCollectionCategoryItemService) {
        this.adCollectionCategoryItemService = adCollectionCategoryItemService;
    }

    // spec : FG-AD-API/191
    @PostMapping("counts-by-dates")
    public JsonResponse<CollectionCategoryItemCountsByDateResponse> getCollectionCategoryItemCountsByDate(
            @RequestBody @Valid CollectionCategoryItemCountsByDateRequest request,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return new JsonResponse<>(
                    Boolean.FALSE,
                    bindingResult.getAllErrors().stream()
                            .map(ObjectError::getDefaultMessage)
                            .collect(Collectors.joining(" ")),
                    null);

        CollectionCategoryItemCountsByDateResponse response = adCollectionCategoryItemService.getCountsByDates(
                request.getBeginDisplayOn().atStartOfDay(),
                request.getEndDisplayOn().atStartOfDay()
        );

        return new JsonResponse<>(true, null, response);
    }

    // spec : FG-AD-API/191
    @PostMapping("fetch-by-date")
    public JsonResponse<CollectionCategoryItemByDateResponse> getCollectionCategoryItemByDate(@RequestBody @Valid CollectionCategoryItemByDateRequest request,
                                                                                              BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return new JsonResponse<>(
                    Boolean.FALSE,
                    bindingResult.getAllErrors().stream()
                            .map(ObjectError::getDefaultMessage)
                            .collect(Collectors.joining(" ")),
                    null);

        CollectionCategoryItemByDateResponse response = adCollectionCategoryItemService.getItemsByDate(
                request.getDisplayOn().atStartOfDay()
        );

        return new JsonResponse<>(true, null, response);
    }
}
