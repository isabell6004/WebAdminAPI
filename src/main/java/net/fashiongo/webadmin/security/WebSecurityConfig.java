package net.fashiongo.webadmin.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${waapi.disableAuthenticate}")
	private Boolean disableAuthenticate;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.headers().cacheControl();

		if (disableAuthenticate.booleanValue()) {
			http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			http.addFilterBefore(new CORSFilter(), ChannelProcessingFilter.class)
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/**").permitAll()
				.anyRequest().permitAll().and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		} else {
			http.addFilterBefore(new CORSFilter(), ChannelProcessingFilter.class)
				.csrf().disable()
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/**").permitAll().antMatchers(HttpMethod.GET, "/**").permitAll()
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.antMatchers(HttpMethod.POST, "/login").permitAll()
				.antMatchers(HttpMethod.GET, "/expired").permitAll()
				.anyRequest().authenticated().and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
		}

	}
}