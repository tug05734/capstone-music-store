package com.capstone.MusicStore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.capstone.MusicStore.services.MyUserDetailsService;



@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MyUserDetailsService userDetailsService;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                auth
                    .userDetailsService(userDetailsService)
                    .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {        
        
        http.
		        authorizeRequests()
		        .antMatchers("/").permitAll()
		        .antMatchers("/category").permitAll()
		        .antMatchers("/deleteCategory").permitAll()
		        .antMatchers("/genre").permitAll()
		        .antMatchers("/deleteGenre").permitAll()
		        .antMatchers("/getCartByUserId").permitAll()
		        .antMatchers("/getCartByUser").permitAll()
		        .antMatchers("/deleteCartById").permitAll()
		        .antMatchers("/cart").permitAll()
		        .antMatchers("/order").permitAll()
		        .antMatchers("/orderByUserId").permitAll()
		        .antMatchers("/getCurrentUser").permitAll()
		        .antMatchers("/login").permitAll()
		        .antMatchers("/registration").permitAll()
		        .antMatchers("/products/addCart/{id}").permitAll()
		        .antMatchers("/products/all").permitAll() //for testing
		        .antMatchers("/products/search").permitAll()
                .antMatchers("/products/admin/**").hasAuthority("ADMIN")
                .antMatchers("/categoryView").hasAuthority("ADMIN")
                .antMatchers("/genreView").hasAuthority("ADMIN")
		        .antMatchers("/products/details/{id}").permitAll()
		        .antMatchers("/products/delete/{id}").permitAll()
		        .antMatchers("/products/edit/details/{id}").permitAll()
		        .antMatchers("/user/**").hasAnyAuthority("ADMIN", "USER").anyRequest()
		        .authenticated().and().csrf().disable().formLogin()
		        .loginPage("/login").failureUrl("/login?error=true")
		        .defaultSuccessUrl("/user/home")
		        .usernameParameter("user_name")
		        .passwordParameter("password")
		        .and().logout()
		        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		        .logoutSuccessUrl("/login").and().exceptionHandling()
		        .accessDeniedPage("/access-denied");
       
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/styles/**");
    }

    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver, SpringSecurityDialect sec) {
        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.addDialect(sec); // Enable use of "sec"
        return templateEngine;
    }

}
