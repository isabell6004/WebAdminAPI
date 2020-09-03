package net.fashiongo.webadmin.service.product;

import net.fashiongo.webadmin.model.pojo.login.WebAdminLoginUser;
import net.fashiongo.webadmin.model.product.Product;
import net.fashiongo.webadmin.model.product.ProductSearchCondition;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiConfig;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiHeader;
import net.fashiongo.webadmin.service.externalutil.HttpClientWrapper;
import net.fashiongo.webadmin.service.externalutil.response.CollectionObject;
import net.fashiongo.webadmin.service.externalutil.response.FashionGoApiResponse;
import net.fashiongo.webadmin.utility.Utility;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    private final HttpClientWrapper client;

    public ProductServiceImpl(HttpClientWrapper client) {
        this.client = client;
    }

    /**
     * Find list.
     *
     * @param condition the condition
     * @return the list
     * @throws RuntimeException 질의에 실패하거나 정상적으로 수행하지 못하였을 경우 발생.
     */
    @Override
    public CollectionObject<Product> find(ProductSearchCondition condition) {
        String fashionGoApi = FashionGoApiConfig.fashionGoApi.concat("/v1.0/products");

        if (Objects.nonNull(condition)) {
            fashionGoApi = fashionGoApi.concat(condition.toQueryString());
        }

        WebAdminLoginUser userInfo = Utility.getUserInfo();
        Map<String, String> header = FashionGoApiHeader.getHeader(userInfo.getUserId(), userInfo.getUsername());

        try {

            //응답이 200이 아니거나 FashiongoAPI에 연결이 되지 않았을 경우 null을 반환함.
            FashionGoApiResponse<CollectionObject<Product>> fashionGoApiResponse = client.get(fashionGoApi, header,
                    new ParameterizedTypeReference<FashionGoApiResponse<CollectionObject<Product>>>() {});

            Assert.notNull(fashionGoApiResponse, "Fail to get product from FashionGo API");
            Assert.isTrue(fashionGoApiResponse.getHeader().isSuccessful(), "Fail to get product from FashionGo API");

            return fashionGoApiResponse.getData();

        } catch (Exception e) {
            throw new RuntimeException("Fail to get product from FashionGo API");
        }
    }
}
