package net.fashiongo.webadmin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.fashiongo.webadmin.model.pojo.parameter.GetCollectionCategoryListParameters;
import net.fashiongo.webadmin.model.pojo.response.GetCollectionCategoryListResponse;
import net.fashiongo.webadmin.model.pojo.parameter.GetCategoryListParameters;
import net.fashiongo.webadmin.model.pojo.response.GetCategoryListResponse;
import net.fashiongo.webadmin.model.primary.CollectionCategory;

/**
 * 
 * @author Sanghyup Kim
 */
@Service
public class SitemgmtService extends ApiService {
	
	/**
	 * 
	 * Get Collection Category List
	 * @since 2018. 9. 28.
	 * @author Sanghyup Kim
	 * @param GetCollectionCategoryListParameters
	 * @return GetCollectionCategoryListResponse
	 */
	@SuppressWarnings("unchecked")
//	@Transactional(Propagation.REQUIRED, Isolation.READ_UNCOMMITTED)
	public GetCollectionCategoryListResponse GetCollectionCategoryList(GetCollectionCategoryListParameters parameters) {
		GetCollectionCategoryListResponse result = new GetCollectionCategoryListResponse();
		String spName = "up_wa_GetCollectionCategory";
        List<Object> params = new ArrayList<Object>();
        
        params.add(parameters.getCategoryId());
        params.add(parameters.getExpandAll());
        
        List<Object> _result = jdbcHelper.executeSP(spName, params, CollectionCategory.class);
        List<CollectionCategory> collectionCategorylist = (List<CollectionCategory>)_result.get(0);

        result.setCollectionCategorylist(collectionCategorylist);
		
		return result;
	}
	
	/**
	 * 
	 * Get Category List
	 * @since 2018. 9. 28.
	 * @author Sanghyup Kim
	 * @param GetCategoryListParameters
	 * @return GetCategoryListResponse
	 */
	@SuppressWarnings("unchecked")
	public GetCategoryListResponse GetCategoryList(GetCategoryListParameters parameters) {
		GetCategoryListResponse result = new GetCategoryListResponse();
		String spName = "up_wa_GetCategoryList";
        List<Object> params = new ArrayList<Object>();
        
        params.add(parameters.getCategoryId());
        params.add(parameters.getExpandAll());
        
        List<Object> _result = jdbcHelper.executeSP(spName, params, CollectionCategory.class);
        List<CollectionCategory> collectionCategorylist = (List<CollectionCategory>)_result.get(0);

        result.setCategorylist(collectionCategorylist);
		
		return result;
	}

}
