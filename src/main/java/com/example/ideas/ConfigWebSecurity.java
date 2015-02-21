package com.example.ideas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.web.servlet.support.csrf.CsrfRequestDataValueProcessor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.support.RequestDataValueProcessor;

@Configuration
@EnableWebSecurity
public class ConfigWebSecurity extends WebSecurityConfigurerAdapter {

	// @Autowired
	// AuthSuccessHandler authSuccessHandler;
	@Configuration
	protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {

		@Override
		public void init(final AuthenticationManagerBuilder auth) throws Exception {
			final ActiveDirectoryLdapAuthenticationProvider provider = new ActiveDirectoryLdapAuthenticationProvider("domain", "ldap://dc.example.com/");
			provider.setConvertSubErrorCodesToExceptions(true);
			provider.setUseAuthenticationRequestCredentials(true);
			// provider.setAuthoritiesMapper(myAuthoritiesMapper()); // see
			// http://comdynamics.net/blog/544/spring-security-3-integration-with-active-directory-ldap/
			provider.setUseAuthenticationRequestCredentials(true);
			auth.authenticationProvider(provider);
		}
	}

	/* used for csrf token in form:form */
	@Bean
	public RequestDataValueProcessor requestDataValueProcessor() {
		return new CsrfRequestDataValueProcessor();
	}

	@Override
	public void configure(final WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/**").permitAll().antMatchers("/post").authenticated();

		http.formLogin().failureUrl("/login?error").loginPage("/login")
				.defaultSuccessUrl("/", true).loginProcessingUrl("/authcheck")
				.usernameParameter("username").passwordParameter("password")
				.defaultSuccessUrl("/", true)
				// .successHandler(this.authSuccessHandler)
				.permitAll();

		http.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/");

		http.authorizeRequests().anyRequest().authenticated();

	}
}
