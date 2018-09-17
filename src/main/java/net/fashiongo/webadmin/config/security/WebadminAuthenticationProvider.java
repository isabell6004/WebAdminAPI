//package net.fashiongo.webadmin.config.security;
//
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.List;
//
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import net.fashiongo.common.UserRole;
//import net.fashiongo.elambs_api.model.primary.VendorLambsKey;
//import net.fashiongo.elambs_api.model.primary.WholeSaler;
//import net.fashiongo.vendoradmin.api.security.VendorAdminAuthenticationToken;
//import net.fashiongo.vendoradmin.api.security.VendorAdminLoginUser;
//import net.fashiongo.vendoradmin.api.security.VendorBlockedException;
//import net.fashiongo.vendoradmin.api.utility.FGServiceVendorInfo;
//import net.fashiongo.vendoradmin.api.utility.JsonResponse;
//
//@Component
//public class WebadminAuthenticationProvider implements AuthenticationProvider {
//	@Override
//	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//		// throw new BadCredentialsException();
//		String username = authentication.getName();
//		String password = authentication.getCredentials().toString(); // app.vaapi.fgServiceAPI_EndPoint
//
//		// HttpClient client = new HttpClient(this.fgServiceAPIEndPoint);
//		// JsonResponse response = client.post("/vendorAdmin/authenticate",
//		// "{\"userName\":\"" + username + "\", \"password\":\"" + password + "\"}");
//		JsonResponse response = client.post("/membership/authenticate/2",
//				"{\"userName\":\"" + username + "\", \"password\":\"" + password + "\"}");
//
//		if (!response.isSuccess())
//			throw new BadCredentialsException();
//
//		FGServiceVendorInfo vi = new FGServiceVendorInfo();
//
//		LinkedHashMap<String, Object> d = (LinkedHashMap<String, Object>) response.getData();
//		if (!(Boolean) d.get("isAuthenticated")) {
//			if ((Boolean) d.get("isBlocked") != null && (Boolean) d.get("isBlocked")) {
//				throw new VendorBlockedException((String) d.get("message"));
//			} else {
//				throw new BadCredentialsException((String) d.get("message"));
//			}
//		}
//
//		// vi.setWid((Integer)d.get("wid"));
//		// vi.setWguid((String)d.get("wguid"));
//		vi.setWid((Integer) d.get("id"));
//		vi.setWguid((String) d.get("guid"));
//		vi.setRole((String) d.get("role"));
//		vi.setSecurityUserId((Integer) d.get("securityUserId"));
//		vi.setSecurityUserRole((String) d.get("securityUserRole"));
//
//		// check whether valid
//
//		List<GrantedAuthority> grantedAuths = new ArrayList<>();
//		grantedAuths.add(new SimpleGrantedAuthority("VENDOR_ADMIN"));
//
//		VendorAdminAuthenticationToken auth = new VendorAdminAuthenticationToken(authentication.getName(),
//				authentication.getCredentials(), grantedAuths);
//		VendorAdminLoginUser userInfo = new VendorAdminLoginUser();
//		userInfo.setWholesalerId(vi.getWid());
//		userInfo.setRole(UserRole.valueOf(vi.getRole()));
//		userInfo.setSecurityUserId(vi.getSecurityUserId());
//		userInfo.setSecurityUserRole(vi.getSecurityUserRole());
//		userInfo.setGuid(vi.getWguid());
//
//		// get resources
//		if (!vi.getWid().equals(0)) {
//			List<String> resources = vendorAdminService.getVendorAdminResources(username, vi.getWid());
//			String strResources = resources.toString();
//			strResources = strResources.substring(1, strResources.length() - 1);
//			userInfo.setResources(strResources);
//		}
//
//		auth.setUserInfo(userInfo);
//		return auth;
//	}
//
//	@Override
//	public boolean supports(Class<?> authentication) {
//		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
//	}
//}