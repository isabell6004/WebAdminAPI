package net.fashiongo.webadmin.config.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import net.fashiongo.webadmin.model.pojo.login.WebAdminLoginUser;

/**
 * 
 * @author Incheol Jung
 */
public class AuthenticatedUser implements Authentication {
	private static final long serialVersionUID = -8023724900051585083L;
	private String name;
    private boolean authenticated = true;
    private WebAdminLoginUser webAdminLoginUser;

    AuthenticatedUser(String name, WebAdminLoginUser webAdminLoginUser){
        this.name = name;
        this.webAdminLoginUser = webAdminLoginUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public WebAdminLoginUser getDetails() {
        return webAdminLoginUser;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        this.authenticated = b;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
