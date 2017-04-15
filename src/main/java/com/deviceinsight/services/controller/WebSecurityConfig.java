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
                .antMatchers("/", "/auth/**").permitAll()
                .antMatchers("/secured").hasRole("USER")
                .antMatchers("/products").hasRole("ADMIN")
                .antMatchers("/api/v1/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/**").permitAll()
        ;

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
}
