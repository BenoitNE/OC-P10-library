package fr.benoitne.libraryweb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private SpringUserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		String loginPage = "/login";
		String logoutPage = "/logout";

		http.authorizeRequests().antMatchers("/").permitAll().antMatchers("/img/*").permitAll().antMatchers(loginPage).permitAll().antMatchers("/user/**")
				.hasAuthority("USER").anyRequest().authenticated().and().csrf().disable().formLogin()
				.loginPage(loginPage).loginPage("/").failureUrl("/login?error=true").defaultSuccessUrl("/user/home")
				.usernameParameter("user_name").passwordParameter("password").and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher(logoutPage)).logoutSuccessUrl(loginPage).and()
				.exceptionHandling();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/src/main/webapp/assets/**", "/static/**", "/css/**", "/js/**", "/images/**",
				"/webapp/**");
	}

}
