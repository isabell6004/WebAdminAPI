//package net.fashiongo.webadmin.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.access.channel.ChannelProcessingFilter;
//
//import net.fashiongo.webadmin.config.security.WebadminAuthenticationProvider;
//import net.fashiongo.webadmin.config.security.filter.CORSFilter;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//	
//	@Value("${webAdmin.jwt.develop.check-token}")
//	private boolean isCheckToken;
//	
//	@Autowired
//	private WebadminAuthenticationProvider authenticationProvider;
//  
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.headers().cacheControl();
//		if(isCheckToken) {
//			http
//			.addFilterBefore(new CORSFilter(), ChannelProcessingFilter.class)
//			.csrf().disable()
//			.authorizeRequests()
//			.antMatchers(HttpMethod.POST, "/**").permitAll()
//			.antMatchers(HttpMethod.GET, "/**").permitAll()
//			.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//			.antMatchers(HttpMethod.POST, "/login").permitAll()
//			.antMatchers(HttpMethod.GET, "/expired").permitAll()
//			.anyRequest().authenticated()
//			.and()
//			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
//		} else {
//			http
//			.csrf().disable()
//			.authorizeRequests()
//			.antMatchers("/**").permitAll()
//			.anyRequest().authenticated();
//		}
//	}
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(this.authenticationProvider);
//	}
//}