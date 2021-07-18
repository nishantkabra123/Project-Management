package com.example.pma.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//by marking configuration its bean will be registered to spring context
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	//spring with autowire h2 db config from app.prop
	@Autowired
	DataSource dataSource;
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;
	
	//configuring authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.jdbcAuthentication().usersByUsernameQuery(
				 "select username,password,enabled from user_accounts where username=?")
		 .authoritiesByUsernameQuery("select username,role from user_accounts where username=?")
		 .dataSource(dataSource).passwordEncoder(bCryptEncoder);
		
//		auth.jdbcAuthentication().dataSource(dataSource).withDefaultSchema()
//			.withUser("nishant").password("pass").roles("USER").and()
//			.withUser("Tom").password("pass").roles("USER").and()
//			.withUser("manager").password("pass").roles("ADMIN");
		//we can also use jpa,jdbc auth
//	 auth.inMemoryAuthentication()
//	.withUser("nishant").password("pass").roles("USER").and()
//	.withUser("Tom").password("pass").roles("USER").and()
//	.withUser("manager").password("pass").roles("ADMIN");
}
//	@Bean
//	public PasswordEncoder getPasswordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
	//configuring authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests().antMatchers("/").permitAll();
//		http.authorizeRequests().antMatchers("/projects/new").hasRole("ADMIN").
//		antMatchers("/employees/new").hasRole("ADMIN").
//		antMatchers("/","/**").permitAll().and().formLogin();
//		antMatchers("/h2_console/**").permitAll(). hasAuthority("ADMIN")
//		formLogin().loginPage(loginPage)
//		authenticated().and().formLogin();
		http.csrf().disable();
		http.headers().frameOptions().disable();//way to get around h2 db
	}
}