package net.fashiongo.webadmin.utility;

import net.fashiongo.webadmin.config.security.AuthenticatedUser;
import net.fashiongo.webadmin.model.pojo.login.WebAdminLoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithCustomMockUser> {
    @Override
    public SecurityContext createSecurityContext(WithCustomMockUser user) {
        SecurityContext context = SecurityContextHolder.getContext();
        WebAdminLoginUser userInfo = new WebAdminLoginUser();
        userInfo.setUserId(57);
        userInfo.setUsername("developer");
        Authentication auth = new AuthenticatedUser(userInfo.getUsername(), userInfo);
        context.setAuthentication(auth);
        return context;
    }
}
