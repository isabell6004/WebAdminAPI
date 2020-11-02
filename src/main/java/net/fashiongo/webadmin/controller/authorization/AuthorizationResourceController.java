package net.fashiongo.webadmin.controller.authorization;

import lombok.extern.slf4j.Slf4j;
import net.fashiongo.webadmin.config.security.AuthenticatedUser;
import net.fashiongo.webadmin.config.security.TokenAuthenticationService;
import net.fashiongo.webadmin.model.pojo.login.WebAdminLoginUser;
import net.fashiongo.webadmin.service.externalutil.response.ApiResponseHeader;
import net.fashiongo.webadmin.service.externalutil.response.FashionGoApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/authorization/resources")
public class AuthorizationResourceController {

    @GetMapping(value = "/me")
    public FashionGoApiResponse me(
            HttpServletRequest request
    ) {

        try {
            WebAdminLoginUser webAdminLoginUser = retrieveUser(request).orElseThrow(() -> new AuthorizationResourceException());
            WebAdminUser webAdminUser = WebAdminUser.builder()
                    .userName(webAdminLoginUser.getUsername())
                    .build();

            FashionGoApiResponse fashionGoApiResponse = new FashionGoApiResponse();
            ApiResponseHeader apiResponseHeader = new ApiResponseHeader();
            apiResponseHeader.setResultCode(0);
            apiResponseHeader.setSuccessful(true);
            fashionGoApiResponse.setData(webAdminUser);
            fashionGoApiResponse.setHeader(apiResponseHeader);
            return fashionGoApiResponse;
        } catch (Exception e) {
            log.warn(e.getMessage(),e);
            return createErrorResponse(e);
        }
    }

    private Optional<WebAdminLoginUser> retrieveUser(HttpServletRequest request) {
        return Optional.ofNullable(TokenAuthenticationService.getAuthentication(request))
                .map(authentication -> (AuthenticatedUser)authentication)
                .map(authenticatedUser -> authenticatedUser.getDetails());
    }

    private FashionGoApiResponse createErrorResponse(Exception e) {
        FashionGoApiResponse fashionGoApiResponse = new FashionGoApiResponse();
        ApiResponseHeader apiResponseHeader = new ApiResponseHeader();
        apiResponseHeader.setSuccessful(false);
        fashionGoApiResponse.setHeader(apiResponseHeader);

        if(e instanceof AuthorizationResourceException) {

            apiResponseHeader.setResultCode(400);
            apiResponseHeader.setResultMessage("authorization fail");

            return fashionGoApiResponse;
        }
        apiResponseHeader.setResultCode(500);
        apiResponseHeader.setResultMessage("internal server error.");

        return fashionGoApiResponse;
    }
}
