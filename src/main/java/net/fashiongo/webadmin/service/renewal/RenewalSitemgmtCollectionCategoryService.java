package net.fashiongo.webadmin.service.renewal;

import net.fashiongo.webadmin.data.model.sitemgmt.SitemgmtAdPageSpot;
import net.fashiongo.webadmin.data.model.sitemgmt.SitemgmtCategory;
import net.fashiongo.webadmin.data.model.sitemgmt.SitemgmtCollectionCategory;
import net.fashiongo.webadmin.data.model.sitemgmt.SitemgmtMapCollectionCategory;
import net.fashiongo.webadmin.data.model.sitemgmt.response.GetCollectionCategoryListResponse;
import net.fashiongo.webadmin.data.repository.primary.procedure.PrimaryProcedureRepository;
import net.fashiongo.webadmin.data.repository.primary.procedure.ResultGetCollectionCategory;
import net.fashiongo.webadmin.model.pojo.parameter.GetCollectionCategoryListParameters;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RenewalSitemgmtCollectionCategoryService {
    private final PrimaryProcedureRepository primaryProcedureRepository;

    public RenewalSitemgmtCollectionCategoryService(PrimaryProcedureRepository primaryProcedureRepository) {
        this.primaryProcedureRepository = primaryProcedureRepository;
    }


    public GetCollectionCategoryListResponse getCollectionCategoryList(GetCollectionCategoryListParameters parameters) {

        // add parameters
        final Integer categoryID = parameters.getCategoryId();
        final Integer expandAll = parameters.getExpandAll();

        GetCollectionCategoryListResponse resultSet = new GetCollectionCategoryListResponse();
        if (categoryID == 0) { // list
            ResultGetCollectionCategory _result = primaryProcedureRepository.up_wa_GetCollectionCategory(categoryID, expandAll);
            List<SitemgmtCollectionCategory> collectionCategoryList = _result.getCollectionCategories();
            resultSet.setCollectionCategoryList(collectionCategoryList);
        } else { // detail by categoryId
            ResultGetCollectionCategory _result = primaryProcedureRepository.up_wa_GetCollectionCategory(categoryID, expandAll);

            final List<SitemgmtCollectionCategory> collectionCategoryList = _result.getCollectionCategories();
            final List<SitemgmtMapCollectionCategory> mapCollectionCategoryList = _result.getMapCollectionCategories();
            final List<SitemgmtAdPageSpot> adPageSpotist = _result.getAdPageSpots();
            final List<SitemgmtCategory> categoryList = _result.getCategories();

            resultSet.setCollectionCategoryList(collectionCategoryList);
            resultSet.setMapCollectionCategoryList(mapCollectionCategoryList);
            resultSet.setAdPageSpotList(adPageSpotist);
            resultSet.setCategoryList(categoryList);
        }

        return resultSet;
    }
}
