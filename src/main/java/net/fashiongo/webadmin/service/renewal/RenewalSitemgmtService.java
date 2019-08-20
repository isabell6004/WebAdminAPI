package net.fashiongo.webadmin.service.renewal;

import net.fashiongo.webadmin.data.entity.primary.CodeFabricEntity;
import net.fashiongo.webadmin.data.entity.primary.CodeLengthEntity;
import net.fashiongo.webadmin.data.entity.primary.CodePatternEntity;
import net.fashiongo.webadmin.data.entity.primary.CodeStyleEntity;
import net.fashiongo.webadmin.data.model.Total;
import net.fashiongo.webadmin.data.model.sitemgmt.CodeData;
import net.fashiongo.webadmin.data.model.sitemgmt.PolicyAgreement;
import net.fashiongo.webadmin.data.model.sitemgmt.response.GetPolicyDetailResponse;
import net.fashiongo.webadmin.data.model.sitemgmt.response.GetProductAttributesResponse;
import net.fashiongo.webadmin.data.repository.primary.*;
import net.fashiongo.webadmin.data.repository.primary.view.CategoryViewRepository;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetPolicyDetailParameter;
import net.fashiongo.webadmin.model.pojo.sitemgmt.parameter.GetProductAttributesParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class RenewalSitemgmtService {

	private final PolicyAgreementEntityRepository policyAgreementEntityRepository;

	private final CodeLengthEntityRepository codeLengthEntityRepository;

	private final CodeStyleEntityRepository codeStyleEntityRepository;

	private final CodeFabricEntityRepository codeFabricEntityRepository;

	private final CategoryViewRepository categoryViewRepository;

	private final CodePatternEntityRepository codePatternEntityRepository;

	@Autowired
	public RenewalSitemgmtService(PolicyAgreementEntityRepository policyAgreementEntityRepository, CodeLengthEntityRepository codeLengthEntityRepository, CodeStyleEntityRepository codeStyleEntityRepository, CodeFabricEntityRepository codeFabricEntityRepository, CategoryViewRepository categoryViewRepository, CodePatternEntityRepository codePatternEntityRepository) {
		this.policyAgreementEntityRepository = policyAgreementEntityRepository;
		this.codeLengthEntityRepository = codeLengthEntityRepository;
		this.codeStyleEntityRepository = codeStyleEntityRepository;
		this.codeFabricEntityRepository = codeFabricEntityRepository;
		this.categoryViewRepository = categoryViewRepository;
		this.codePatternEntityRepository = codePatternEntityRepository;
	}

	public GetPolicyDetailResponse getPolicyDetail (GetPolicyDetailParameter parameters) {
		Page<PolicyAgreement> detailPolicyAgreement = policyAgreementEntityRepository.findDetailPolicyAgreement(parameters.getPolicyID(), parameters.getSearchItem(), parameters.getSearchTxt(), parameters.getPageNum(), parameters.getPageSize());
		int totalCnt = Long.valueOf(detailPolicyAgreement.getTotalElements()).intValue();
		return GetPolicyDetailResponse.builder()
				.total(Arrays.asList(new Total(totalCnt)))
				.policyDetail(
						detailPolicyAgreement.getContent()
				)
				.build();
	}

	public GetProductAttributesResponse getProductAttributes(GetProductAttributesParameter parameter) {
		GetProductAttributesResponse.GetProductAttributesResponseBuilder builder = GetProductAttributesResponse.builder();
		long totalElements = 0;
		List<CodeData> codeDataList = null;

		switch (parameter.getTabNo())
		{
			case 2:// "Length":

				Page<CodeLengthEntity> codeLengthEntities = codeLengthEntityRepository.findAllByLengthNameAndActiveOrderByLengthName(parameter.getAttrName(), parameter.getActive(), 1, 1000);
				totalElements = codeLengthEntities.getTotalElements();
				codeDataList = codeLengthEntities.getContent().stream()
						.map(entity -> CodeData.builder()
								.active(entity.isActive())
								.codeID(entity.getLengthId())
								.codeName(entity.getLengthName())
								.build())
						.collect(Collectors.toList());
				break;
			case 3:// "Style":
				Page<CodeStyleEntity> codeStyleEntities = codeStyleEntityRepository.findAllByStyleNameAndActiveOrderByStyleName(parameter.getAttrName(), parameter.getActive(), 1, 1000);
				totalElements = codeStyleEntities.getTotalElements();

				codeDataList = codeStyleEntities.getContent().stream()
						.map(entity -> CodeData.builder()
								.active(entity.isActive())
								.codeID(entity.getStyleId())
								.codeName(entity.getStyleName())
								.build())
						.collect(Collectors.toList());

				break;
			case 4:// "Fabric":
				Page<CodeFabricEntity> codeFabricEntities = codeFabricEntityRepository.findAllByFabricNameAndActiveOrderByFabricName(parameter.getAttrName(), parameter.getActive(), 1, 1000);
				totalElements = codeFabricEntities.getTotalElements();

				codeDataList = codeFabricEntities.getContent().stream()
						.map(entity -> CodeData.builder()
								.active(entity.isActive())
								.codeID(entity.getFabricId())
								.codeName(entity.getFabricName())
								.build())
						.collect(Collectors.toList());
				break;
			case 5:// "Category Mapping":
				Page<CodeData> codeDataPage = null;
				switch (parameter.getPrevTab())
				{
					case 2:
//						DataSrc = "vwLengthCategory";
//						ColumnList = "MapID,LengthID As CodeID,LengthName As CodeName,Case When MapID > 0 Then Cast(1 As Bit) Else Cast(0 As Bit) End As Active";
//						Filter = Filter + " and CategoryID = " + parameter.getCategoryID() + "";
//						OrderBy = "LengthName";

						codeDataPage = categoryViewRepository.findAllvwLengthCategoryByCategoryIdOrderByLengthName(parameter.getCategoryID(),1,1000);
						totalElements = codeDataPage.getTotalElements();
						codeDataList = codeDataPage.getContent();
						break;
					case 3:
//						DataSrc = "vwStyleCategory";
//						ColumnList = "MapID,StyleID As CodeID,StyleName As CodeName,Case When MapID > 0 Then Cast(1 As Bit) Else Cast(0 As Bit) End As Active";
//						Filter = Filter + " and CategoryID = " + parameter.getCategoryID() + "";
//						OrderBy = "StyleName";

						codeDataPage = categoryViewRepository.findAllvwStyleCategoryByCategoryIdOrderByStyleName(parameter.getCategoryID(),1,1000);
						totalElements = codeDataPage.getTotalElements();
						codeDataList = codeDataPage.getContent();
						break;
					case 4:
//						DataSrc = "vwFabricCategory";
//						ColumnList = "MapID,FabricID As CodeID,FabricName As CodeName,Case When MapID > 0 Then Cast(1 As Bit) Else Cast(0 As Bit) End As Active";
//						Filter = Filter + " and CategoryID = " + parameter.getCategoryID() + "";
//						OrderBy = "FabricName";
						codeDataPage = categoryViewRepository.findAllvwFabricCategoryByCategoryIdOrderByfabricName(parameter.getCategoryID(),1,1000);
						totalElements = codeDataPage.getTotalElements();
						codeDataList = codeDataPage.getContent();
						break;
					default:
//						DataSrc = "vwPatternCategory";
//						ColumnList = "MapID,PatternID As CodeID,PatternName As CodeName,Case When MapID > 0 Then Cast(1 As Bit) Else Cast(0 As Bit) End As Active";
//						Filter = Filter + " and CategoryID = " + parameter.getCategoryID() + "";
//						OrderBy = "PatternName";
						codeDataPage = categoryViewRepository.findAllvwPatternCategoryByCategoryIdOrderByPatternName(parameter.getCategoryID(),1,1000);
						totalElements = codeDataPage.getTotalElements();
						codeDataList = codeDataPage.getContent();
						break;
				}
				break;
			default://"Pattern":
				Page<CodePatternEntity> codePatternEntities = codePatternEntityRepository.findAllByPattenNameAndActiveOrderByPattenName(parameter.getAttrName(), parameter.getActive(), 1, 1000);
				totalElements = codePatternEntities.getTotalElements();

				codeDataList = codePatternEntities.getContent().stream()
						.map(entity -> CodeData.builder()
								.active(entity.isActive())
								.codeID(entity.getPatternId())
								.codeName(entity.getPatternName())
								.build())
						.collect(Collectors.toList());

				return builder.codeDataList(codeDataList)
						.recCnt(Arrays.asList(Total.builder().recCnt((int) totalElements).build()))
						.build();
		}

		return builder.codeDataList(codeDataList)
				.recCnt(Arrays.asList(Total.builder().recCnt((int) totalElements).build()))
				.build();
	}
}
