/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.deviceinsight.services.controller;

import com.deviceinsight.services.controller.security.AuthFailureHandler;
import com.deviceinsight.services.controller.security.AuthSuccessHandler;
import com.deviceinsight.services.controller.security.HttpAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
/*import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.persistence.*;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Logger;
*/


/**
 * Customizes Spring Security configuration.
 *
 * @author Rob Winch
 */
@EnableWebSecurity
@Configuration
@ComponentScan("com.deviceinsight.services")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private AuthSuccessHandler authSuccessHandler;

    @Autowired
    private AuthFailureHandler authFailureHandler;


    @Autowired
    private HttpAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;

    @Bean(name = "myAuthenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        // AuthenticationManager au = auth.getOrBuild();

		/*
         * CHRIS String username = "fabrice"; String password =
		 * "$2a$10$0HniSinvB/SizCRUxzavkOVsbtK570Jc9tWi0Tq9ZeVm7k4ZOj6Hm";
		 * UsernamePasswordAuthenticationToken token = new
		 * UsernamePasswordAuthenticationToken(username, password);
		 * 
		 * // generate session if one doesn't exist request.getSession();
		 * 
		 * token.setDetails(new WebAuthenticationDetails(request)); //
		 * AuthenticationManager authenticationManager = auth.build();
		 * 
		 * Authentication authenticatedUser = au.authenticate(token);
		 * 
		 * SecurityContextHolder.getContext().setAuthentication(
		 * authenticatedUser);
		 * 
		 */

    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(new ShaPasswordEncoder());

        return authenticationProvider;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // Refactor login form

                // See https://jira.springsource.org/browse/SPR-11496
                /****     .headers()
                 .addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
                 .and()

                 .formLogin().defaultSuccessUrl("/assets/angular/#/app/dashboard-variant-1").loginPage("/login").failureUrl("/assets/angular/#/app/dashboard-variant-1")
                 .permitAll().and().logout().logoutSuccessUrl("/assets/angular/#/app/dashboard-variant-1").logoutUrl("/logout").permitAll()
                 */
                //.and().antMatcher("/myaccount/dashboard").authorizeRequests().anyRequest().hasAnyRole("USER")

         /*       .and().authorizeRequests()
                .antMatchers("/myaccount/register").anonymous()
               // .antMatchers("/admin/employees/tablelist").hasAnyRole("ADMIN")


                .antMatchers("/login").anonymous()


                .antMatchers("/myaccount/**").hasAnyRole("USER", "ADMIN")


////                .antMatchers("/rest/v1/**").hasAnyRole("USER", "ADMIN")


                .antMatchers("/admin/**").hasAnyRole("ADMIN")


                .antMatchers("/api/v1/backend/devices/**").hasAnyRole("USER", "ADMIN")
         ///////       .antMatchers("/api/v1/**").hasAnyRole("USER", "ADMIN")

                .antMatchers("/assets/angular/app/tpls/tables/**").hasAnyRole("USER", "ADMIN")


                .antMatchers("/portfolio/**").hasAnyRole("USER", "ADMIN")

                .antMatchers("/senders/**").hasAnyRole("USER", "ADMIN")




*/



                // See https://jira.springsource.org/browse/SPR-11496
                .headers()
                .addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
                .and()


                .authenticationProvider(authenticationProvider())
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .formLogin().defaultSuccessUrl("/assets/angular/#/app/dashboard-variant-1").loginPage("/login").failureUrl("/assets/angular/#/lockscreen")






                .permitAll()
                //.loginProcessingUrl(LOGIN_PATH)
                //.usernameParameter(USERNAME)
                //.passwordParameter(PASSWORD)
                .successHandler(authSuccessHandler)
                .failureHandler(authFailureHandler)


                .and().logout().logoutSuccessUrl("/assets/angular/#/lockscreen").logoutUrl("/logout").permitAll()




/*****                .formLogin().defaultSuccessUrl("/livetrading").loginPage("/livetrading").failureUrl("/livetrading")
 .permitAll().and().logout().logoutSuccessUrl("/livetrading").logoutUrl("/logout").permitAll()*//////////////
                //.and().antMatcher("/myaccount/dashboard").authorizeRequests().anyRequest().hasAnyRole("USER")


                .and().authorizeRequests()


                .antMatchers("/latex").permitAll()

                .antMatchers("/createInvoice").permitAll()



                .antMatchers("/api/v1/checkAuth").permitAll()
                .antMatchers("/api/v1/checkRegistration").permitAll()
                .antMatchers("/api/v1/admin/registration").permitAll()


                .antMatchers("/api/v1/admin/analytics/**").hasAnyRole("ADMIN")


                ///   .antMatchers("/myaccount/register").anonymous()
                .antMatchers("/logout").hasAnyRole("USER", "ADMIN")
                .antMatchers("/myaccount/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/admin/**").hasAnyRole("ADMIN")

                .antMatchers("/api/v1/admin/employees/tablelist").hasAnyRole("ADMIN")

                .antMatchers("/api/v1/getUserRole").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/v1/getUser").hasAnyRole("USER", "ADMIN")


                .antMatchers("/api/v1/admin/employees/update/**").hasAnyRole("ADMIN")

//localhost:8080/api/v1/checkRegistration














                .antMatchers("/", "/auth/**").permitAll()
                .antMatchers("/secured").hasRole("USER")
                .antMatchers("/products").hasRole("ADMIN")









                .antMatchers("/api/v1/**").hasAnyRole("USER", "ADMIN")


                .antMatchers("/**").permitAll()
        ;


        //  .antMatchers("/**").permitAll()
        ;

    }

	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth)
	 * throws Exception { auth .inMemoryAuthentication()
	 * .withUser("fabrice").password("fab123").roles("USER").and()
	 * .withUser("paulson").password("bond").roles("ADMIN","USER"); }
	 */

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

}