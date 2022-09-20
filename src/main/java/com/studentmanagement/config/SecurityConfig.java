package com.studentmanagement.config;

import com.studentmanagement.StudentUserDetailsService;
import com.studentmanagement.service.UserAuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{//by eztending this class we wl override its methds to chnge the default spring secuirty config
	
	@Autowired
	private UserAuthenticationService customUserDetailsService;
	
	@Autowired
	private JwtAuthenticationFilter jwtFilter;
	
	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;
	private static final String[] AUTH_WHITELIST = {

			
		"/swagger-resources/**",
		"/swagger-ui.html",
		"/v2/api-docs",
		"/webjars/**"
};
	
		@Override
	protected void configure(HttpSecurity http) throws Exception {
	http
	.csrf()
	.disable()//disabling this attack
	.cors()
	.disable()
	.authorizeRequests()
	.antMatchers(AUTH_WHITELIST).permitAll()
	.antMatchers("/api/auth/register/**","/swagger-ui/**").permitAll()
	.anyRequest().authenticated()
	.and()
	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	.and()
	.exceptionHandling().authenticationEntryPoint(entryPoint);
	
http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService);
		
}


	@Bean
		public PasswordEncoder passwordEnocder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}

	
	
	

}
