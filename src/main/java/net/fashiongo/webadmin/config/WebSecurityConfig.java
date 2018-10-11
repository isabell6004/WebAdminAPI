package net.fashiongo.webadmin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import net.fashiongo.webadmin.config.security.WebadminAuthenticationProvider;
import net.fashiongo.webadmin.config.security.filter.CORSFilter;
import net.fashiongo.webadmin.config.security.filter.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Value("${webAdminapi.jwt.develop.check-token}")
	private boolean isCheckToken;
	
	@Autowired
	private WebadminAuthenticationProvider authenticationProvider;
  
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.headers().cacheControl();
		if(isCheckToken) {
			http
			.addFilterBefore(new CORSFilter(), ChannelProcessingFilter.class)
			.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
			.exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint()).and()
			.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
			.antMatchers(HttpMethod.POST, "/logout").permitAll()
			.antMatchers(HttpMethod.GET, "/expired").permitAll()
			.antMatchers(HttpMethod.POST, "/payment/**").permitAll()
			.antMatchers(HttpMethod.GET, "/payment/**").permitAll()
			.antMatchers(HttpMethod.GET, 
					"/v2/api-docs", 
					"/configuration/ui", 
					"/swagger-resources/**", 
					"/configuration/**", 
					"/swagger-ui.html", 
					"/webjars/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
			
		} else {
			http
			.addFilterBefore(new CORSFilter(), ChannelProcessingFilter.class)
			.csrf().disable()
			.authorizeRequests()
			.anyRequest().permitAll();
		}
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(this.authenticationProvider);
	}
	
	LogoutSuccessHandler logoutSuccessHandler() {
        return new HttpStatusReturningLogoutSuccessHandler();
    }
}