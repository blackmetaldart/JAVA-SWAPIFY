package com.example.springbootmonolith.config;

import com.example.springbootmonolith.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    UserService userService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean("encoder")
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/signup/**", "/login/**").permitAll()
                .antMatchers("/role/**", "/song").hasRole("GENERAL")
                .antMatchers("/user/**", "/profile/**").hasRole("ADMIN")
                .antMatchers("/admin/**", "/role/**").hasRole("ADMIN")
                .and()
                .httpBasic();

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    //    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception{
//
//        User.UserBuilder users = User.withDefaultPasswordEncoder();
//        auth.inMemoryAuthentication().withUser(users.username("test").password("test").roles("ADMIN"));
//        auth.inMemoryAuthentication().withUser(users.username("dba").password("dba").roles("DBA"));
//    }

}