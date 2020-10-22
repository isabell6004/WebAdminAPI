/**
 *
 */
package net.fashiongo.webadmin.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.dao.primary.VendorCreditCardRepository;
import net.fashiongo.webadmin.dao.primary.VendorEntityRepository;
import net.fashiongo.webadmin.data.entity.primary.VendorEntity;
import net.fashiongo.webadmin.data.model.vendor.VendorDetailInfo;
import net.fashiongo.webadmin.data.model.vendor.VendorFormListResponse;
import net.fashiongo.webadmin.data.model.vendor.VendorProductListResponse;
import net.fashiongo.webadmin.model.pojo.common.PagedResult;
import net.fashiongo.webadmin.model.pojo.common.ResultCode;
import net.fashiongo.webadmin.model.pojo.parameter.GetVendorFormsListParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetDenyBannerParameter;
import net.fashiongo.webadmin.model.pojo.parameter.SetVendorFormsParameter;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.DelVendorFormParameter;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.GetProductListParameter;
import net.fashiongo.webadmin.model.pojo.vendor.parameter.SetVendorCreditCardParameter;
import net.fashiongo.webadmin.model.primary.ListVendorImageType;
import net.fashiongo.webadmin.model.primary.LogCommunication;
import net.fashiongo.webadmin.model.primary.VendorContent;
import net.fashiongo.webadmin.service.renewal.RenewalVendorService;
import net.fashiongo.webadmin.service.vendor.BannerRequestService;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * @author roy
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class VendorServiceTest {

    @Autowired
    VendorService vendorService;

    @Autowired
    BannerRequestService bannerRequestService;

    @Autowired
    private RenewalVendorService renewalVendorService;

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void exception_when_no_valid_vendorCreditCardId() {
        SetVendorCreditCardParameter parameter = new SetVendorCreditCardParameter();
        parameter.setVendorID(10);
        parameter.setVendorCreditCardID("99999");
        parameter.setType("Not ADD");
		vendorService.setVendorCreditCard(parameter);
    }

    /**
     *
     * Test GetProductList
     *
     * @since 2018. 11. 5.
     * @author Incheol Jung
     */
    @Test
    public void testGetProductList() {
        GetProductListParameter parameters = new GetProductListParameter();
        parameters.setWholesalerid("2858");
        parameters.setVendorcategoryid("0");
        parameters.setProductname("t");

        VendorProductListResponse result = renewalVendorService.getProductList(parameters);
        if (!CollectionUtils.isEmpty(result.getProducts())) {
            assertNotNull(result.getProducts().get(0).getProductID());
        }
    }

    /**
     *
     * Description Example
     * @since 2018. 11. 26.
     * @author Reo
     */
    @Test
    public void testGetVendorImageType() {
        List<ListVendorImageType> result = bannerRequestService.getVendorImageType();
        if (!CollectionUtils.isEmpty(result)) {
            assertNotNull(result.get(0));
        }
    }

    /**
     *
     * Description Example
     * @since 2018. 11. 26.
     * @author Reo
     */
    @Ignore
    @Test
    public void testSetDenyBanner() {
        SetDenyBannerParameter parameters = new SetDenyBannerParameter();
        parameters.setImageRequestId(25565);
        parameters.setDenialReason("test");
        bannerRequestService.setDenyBanner(parameters);
    }

    /**
     *
     * Description Example
     * @since 2018. 11. 26.
     * @author Reo
     */
    @Ignore
    @Test
    public void testSetApproveBanner() {
        SetDenyBannerParameter parameters = new SetDenyBannerParameter();
        parameters.setImageRequestId(25565);
        bannerRequestService.setApproveBanner(parameters);
    }

    /**
     *
     * Description Example
     * @since 2018. 11. 26.
     * @author Reo
     */
    @Ignore
    @Test
    public void testSetRestoreBanner() {
        SetDenyBannerParameter parameters = new SetDenyBannerParameter();
        parameters.setImageRequestId(25565);
        bannerRequestService.setRestoreBanner(parameters);
    }

    /**
     *
     * Description Example
     * @since 2018. 11. 26.
     * @author Reo
     */
    @Ignore
    @Test
    public void testDelBannerRequest() {
        SetDenyBannerParameter parameters = new SetDenyBannerParameter();
        bannerRequestService.delBannerRequest(parameters);
    }

    /**
     *
     * Description Example
     * @since 2018. 11. 26.
     * @author Reo
     */
    @Test
    public void testGetVendorFormsList() {
        GetVendorFormsListParameter parameters = new GetVendorFormsListParameter();
        VendorFormListResponse response = renewalVendorService.getVendorFormsList(parameters);
        if (!CollectionUtils.isEmpty(response.getFashionGoFormList())) {
            assertNotNull(response.getFashionGoFormList());
        }
    }

    /**
     *
     * Description Example
     * @since 2018. 11. 26.
     * @author Reo
     */
    @Ignore
    @Test
    public void testSetVendorForms() {
        SetVendorFormsParameter parameters = new SetVendorFormsParameter();
        parameters.setType("Add");
        parameters.setFormName("testTitle");
        parameters.setMemo("testMemo");
        parameters.setAttachment("testFile.jpg");
        ResultCode result = vendorService.SetVendorForms(parameters);
        assertTrue(result.getResultCode() > 0);
    }

    /**
     *
     * Description Example
     * @since 2018. 11. 26.
     * @author Reo
     */
    @Ignore
    @Test
    public void testDelVendorForm() {
        DelVendorFormParameter parameters = new DelVendorFormParameter();
        ResultCode result = vendorService.delVendorForm(parameters);
        assertTrue(result.getResultCode() > 0);
    }

    /**
     *
     * Description Example
     * @since 2018. 12. 17.
     * @author Reo
     */
    @Test
    public void testGetVendorCommunicationList() {
        Integer wholeSalerID = 3389;
        List<LogCommunication> result = vendorService.getVendorCommunicationList(wholeSalerID);
        if (!CollectionUtils.isEmpty(result)) {
            assertNotNull(result.get(0));
        }
    }

    /**
     * @author Kenny/Kyungwoo
     * @since 2019-04-16
     */
    @Test
    public void testGetVendorContents() {
        PagedResult<VendorContent> page = vendorService.getVendorContents(1, 10, null, null, null, null, null, null, null);
        assertTrue(page.getTotal().getTotalCount() >= 0);
        assertTrue(page.getRecords().size() >= 0);
    }

    @Test
    public void testMapperTest() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String test = "{\"WholeSalerID\":2858,\"SortNo\":null,\"StartingDate\":\"2012-02-07T15:08:32\",\"CompanyName\":\"FashionGo\",\"RegCompanyName\":\"North Star\",\"DirName\":\"northstar\",\"CodeName\":\"NRSTR\",\"FirstName\":\"NHN Global\",\"LastName\":\"FashionGo\",\"Description\":\"FashionGo is the No. 1 Wholesale Marketplace in DTLA!\\n\\nOPEN HOURS : AM 9:00 -  PM 5:00   MONDAY-FRIDAY\\n\\nWE SELL T-SHIRTS /ACTIVE WEAR (SWEAT TOP, PANTS / HOODIES), POLO SHIRTS, PRINT T-SHIRTS/CARGO SHORTS.\",\"BillStreetNo\":\"3414\",\"BillCity\":\"Los Angeless\",\"BillSTATE\":\"CO\",\"BillZipcode\":\"91213\",\"BillCountry\":\"United Kingdom\",\"BillPhone\":\"213-745-26677\",\"BillFax\":\"213-745-26688\",\"StreetNo\":\"726 E 12TH ST STE  #307\",\"City\":\"Los Angeless\",\"STATE\":\"CO\",\"Zipcode\":\"90015\",\"Country\":\"United Kingdom\",\"Phone\":\"123-123-12344\",\"EMail\":\"analytics@fashiongo.com\",\"Fax\":\"213-745-26688\",\"Memo\":\"memo\",\"WebSite\":\"\",\"UserID\":\"northstar\",\"Pwd\":null,\"WebSiteActive\":null,\"SLActive\":null,\"ReportActive\":null,\"CatalogActive\":null,\"LambsActive\":null,\"Group1\":null,\"Group2\":null,\"DM\":null,\"POSUse\":null,\"MainPage\":null,\"TitlePageMemo\":null,\"WSAPolicy\":null,\"WholeSalerTitlePage\":null,\"OnSale\":null,\"NewCustYN\":\"N\",\"GoodUpYN\":null,\"MinTQYN\":null,\"MinTQ\":null,\"MinDollarYN\":null,\"MinDollar\":null,\"MinECQYN\":null,\"MinECQ\":null,\"QTYSeqYN\":null,\"MinPolicyUseYN\":null,\"OrderNotice\":\"<p>Please note that all orders take two days to ship from our warehouse...</p>\\n\\n<p>All items are sold in packs of <strong>2-2-2 (S-M-L)</strong> or<strong> 3-2-1-1 (S-M-L-XL)</strong>.&nbsp;</p>\\n\\n<p>&nbsp;</p>\\n\\n<p><img alt=\\\"Image result for prop 65 warning sign for clothing\\\" src=\\\"https://www.seton.com/media/catalog/product/cache/4/image/85e4522595efc69f496374d01ef2bf13/1455748764/c/h/chemical-cancer-signs-clothing-contaminated-with-lead-l8471-lg.jpg\\\" style=\\\"height:200px; width:200px\\\" />.</p>\\n\\n<p>&nbsp;</p>\\n\",\"CompCharge\":\"Y \",\"Ratio\":0,\"AutoRActive\":null,\"MinCharge\":null,\"MinAmount\":null,\"IsMontyly\":null,\"IsYearly\":null,\"YearlyAmount\":null,\"NoticeToAll\":\"Notice to All Buyers\",\"AsKnownAs\":null,\"HOnote1\":null,\"HOnote2\":null,\"HOnote3\":null,\"HOnote4\":null,\"ActualOpenDate\":\"2019-03-18T02:41:11.227Z\",\"BillingNote2\":null,\"SpecialNote1\":null,\"SpecialNote2\":null,\"Adv1\":null,\"Adv2\":null,\"AdvertiseYN\":null,\"ActualOpen\":null,\"OwnerCountry\":null,\"ContractExpireDate\":null,\"BillReviewHoLee\":null,\"OpenDate\":null,\"CompsolutionNo\":1543,\"RetailerYes\":null,\"RetailerBlockList\":null,\"RetailerOpenList\":null,\"DateTimeModified\":null,\"PrePackYN\":null,\"SalesItem\":null,\"HotItems\":null,\"PromotionalItems\":null,\"BillStreetNo2\":null,\"StreetNo2\":null,\"ItemLocation\":null,\"LastUser\":\"developer\",\"LastModifiedDateTime\":\"2019-11-05T17:24:28.793\",\"MinTQYNStyle\":null,\"MinTQStyle\":null,\"BillingNote1\":null,\"BillingYN\":null,\"InHouseMemo\":\"in house memo\",\"SizeID\":null,\"PackID\":null,\"DistrictID\":null,\"FGPlan\":2,\"WebSiteLinkCount\":null,\"HowKnownType\":1,\"HowKnownOther\":null,\"DisCountYN\":false,\"InsertedWhichApp\":0,\"AllowImage2Anony\":false,\"MaxPictureQty\":50,\"AllowImmediateShopping\":false,\"BusinessCategory\":\"ABC\",\"ImageServerID\":7,\"ContactPerson\":null,\"CompanyTypeID\":1,\"EstablishedYear\":1997,\"PictureMain\":\"2858_art_main.jpg\",\"PictureLogo\":\"2858_northstar_logo.jpg\",\"SizeChart\":\"<p>aabbbccdddddd</p>\\n\\n<p>test test save 34</p>\\n\",\"MadeIn\":null,\"ProductSortByLastUpdate\":true,\"Active\":true,\"ShopActive\":true,\"OrderActive\":true,\"BillCountryID\":229,\"CountryID\":229,\"BillPhone2\":\"\",\"Phone2\":\"\",\"CreditCardAccessPassword\":\"rkzqAlcmm0qSzeSHjoR0pg==\",\"AdminAccountCap\":5,\"DefaultSizeID\":37739,\"DefaultPackID\":31706,\"DefaultFabricDescriptionID\":40787,\"ManageInventory\":false,\"AllowAccess2Y3\":false,\"SM_Facebook\":\"http://www.facebook.com/Fashiongo.net\",\"SM_Twitter\":\"http://www.twitter.com/myfashiongo\",\"SM_Youtube\":null,\"Blog\":null,\"MinOrderByAmount\":100,\"MinOrderByQty\":null,\"ExtraCharge\":10,\"ExtraChargeAmountFrom\":100,\"ExtraChargeAmountTo\":199.99,\"IndustryType\":\"Women's Clothing,Man's Clothing,Children's Clothing,Accessories\",\"OrderActiveLock\":true,\"ActiveOld\":null,\"ShopActiveOld\":null,\"MinOrderAmount\":null,\"MinOrderAmountYN\":null,\"MinTQAllYN\":null,\"MinTQAll\":null,\"ItemUploadCap\":null,\"DefaultMadeInCountryID\":3179,\"DefaultLabelID\":1,\"DefaultInventoryStatusID\":2,\"PictureMain2\":\"2858_art_sub - Copy.jpg\",\"ShowFeedback\":true,\"ConsolidationYN\":false,\"DefaultVendorID\":2691,\"AdminWebServerID\":6,\"WholeSalerGUID\":\"D33DFC63-C0F9-4F41-BDDD-B3CDD8DAA603\",\"FashionGoExclusive\":true,\"SizeChartImage\":\"sizechartttt.jpg\",\"BlockPolicy\":true,\"SM_Instagram\":\"http://https://www.instagram.com/fashiongonet/\",\"ChargedByCreditCard\":true,\"Category\":\"Women\",\"IsLockedOut\":false,\"LastLockoutDate\":\"2017-09-29T22:03:49.260+0000\",\"IsLockedOut2\":0,\"UseCreditCardPaymentService\":true,\"TransactionFeeRate1\":0.029,\"TransactionFeeRate2\":0.029,\"TransactionFeeRate1Intl\":0.0415,\"TransactionFeeRate2Intl\":0.048,\"TransactionFeeFixed\":0.3,\"CommissionRate\":0.02,\"BillingEmail1\":\"brian@fashiongo.comm\",\"BillingEmail2\":\"abc@abc.com\",\"BillingEmail3\":\"bbb@ddd.com\",\"ShowRoomStreetNo\":\"3530 Wilshire Blvd Ste TEST\",\"ShowRoomCity\":\"LAa\",\"ShowRoomCountry\":\"United Kingdom\",\"ShowRoomSTATE\":\"CO\",\"ShowRoomZipcode\":\"90015\",\"ShowRoomPhone\":\"12345678900\",\"ShowRoomFax\":\"1234567899\",\"elambsuser\":1,\"IsADBlock\":false}";
//
//		JSONParser p = new JSONParser();
//		JSONObject obj = null;
//		try {
//			obj = (JSONObject)p.parse(test);
//		} catch (org.json.simple.parser.ParseException e) {
//			System.out.println("no");
//		}
//
//		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        VendorDetailInfo r;

        r = mapper.readValue(test, VendorDetailInfo.class);

        assertNotNull(r);

        System.out.println(r.getActualOpenDate());

//		String test2 = "{\"WholeSalerID\":2858, \"CompanyName\":null}";
//		net.fashiongo.webadmin.data.model.vendor.Vendor v;
//
//		v = mapper.readValue(test2, net.fashiongo.webadmin.data.model.vendor.Vendor.class);
//
//		assertNotNull(v);
    }

}
