package net.fashiongo.webadmin.config.security;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import net.fashiongo.webadmin.model.pojo.MenuDS;
import net.fashiongo.webadmin.model.pojo.WebAdminLoginUser;

/**
 * 
 * @author Incheol Jung
 */
public class WebAdminUserAuthenticationToken extends UsernamePasswordAuthenticationToken{
	private HttpServletRequest httpServletRequest;
	private WebAdminLoginUser userInfo;
	private MenuDS menuDs;
	
	public WebAdminUserAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}

	public WebAdminUserAuthenticationToken(Object principal, Object credentials) {
		super(principal, credentials);
	}
	
	public WebAdminUserAuthenticationToken(Object principal, Object credentials, HttpServletRequest httpServletRequest) {
		super(principal, credentials);
		this.httpServletRequest = httpServletRequest;
	}

	public WebAdminLoginUser getUserInfo() {
		return this.userInfo;
	}

	public void setUserInfo(WebAdminLoginUser userInfo) {
		this.userInfo = userInfo;
	}

	public HttpServletRequest getHttpServletRequest() {
		return httpServletRequest;
	}

	public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
		this.httpServletRequest = httpServletRequest;
	}
	
	public MenuDS getMenuDs() {
		return menuDs;
	}

	public void setMenuDs(MenuDS menuDs) {
		this.menuDs = menuDs;
	}
}