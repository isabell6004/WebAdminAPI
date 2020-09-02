package net.fashiongo.webadmin.service.product;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import net.fashiongo.webadmin.model.pojo.login.WebAdminLoginUser;
import net.fashiongo.webadmin.model.product.Product;
import net.fashiongo.webadmin.model.product.ProductSearchCondition;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiConfig;
import net.fashiongo.webadmin.service.externalutil.FashionGoApiHeader;
import net.fashiongo.webadmin.service.externalutil.HttpClientWrapper;
import net.fashiongo.webadmin.service.externalutil.response.CollectionObject;
import net.fashiongo.webadmin.service.externalutil.response.FashionGoApiResponse;
import net.fashiongo.webadmin.utility.Utility;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final HttpClientWrapper client;

    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Find list.
     *
     * @param condition the condition
     * @return the list
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
            String result = client.get(fashionGoApi, header);

            mapper.registerModule(new JavaTimeModule())
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            FashionGoApiResponse<CollectionObject<Product>> fashionGoApiResponse = mapper.readValue(result,
                    new TypeReference<FashionGoApiResponse<CollectionObject<Product>>>() {});

            Assert.isTrue(fashionGoApiResponse.getHeader().isSuccessful(), "Fail to get product from FashionGo API");

            return fashionGoApiResponse.getData();

        } catch (Exception e) {
            throw new RuntimeException("Fail to get product from FashionGo API");
        }
    }
}
