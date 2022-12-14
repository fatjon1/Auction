package com.auction.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter  {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("../auction/**").permitAll()
                .antMatchers("/auctions/**").permitAll()

                .antMatchers("/required/**").permitAll()
                .antMatchers("/static/**").permitAll()
                .antMatchers("/webfonts/**").permitAll()
                .antMatchers("/fonts/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/images/**").permitAll()

                .antMatchers("/signUp").permitAll()
                .antMatchers("/checkEmail").permitAll()
                .antMatchers("/resetPassword").permitAll()
                .antMatchers("/topic/**","/user/**").permitAll()

                .antMatchers("/admin/**").authenticated()
                .anyRequest().permitAll()//.authenticated()
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll()
                .and().logout().permitAll();
        //.antMatchers("/getStudentRoles").hasAuthority("ROLE_WRITE");
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.httpFirewall(new DefaultHttpFirewall());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
