package cl.hda.regapp;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.WebInvocationPrivilegeEvaluator;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
	private WebSecurity webSecurity;


	@Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
			.antMatchers("/Producto.xhtml").hasRole("ADMIN")

                .antMatchers("/resources/**", "/registration","/h2-console/**/**","/register.xhtml").permitAll()
                .antMatchers("/javax.faces.resource/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login.xhtml")
                .defaultSuccessUrl("/ListarCab.xhtml", true) 
                .failureUrl("/login.xhtml?error=true")
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/login.xhtml")
                .and()
            .logout()
            .logoutSuccessUrl("/login.xhtml")
                .permitAll()
                .and()
                .exceptionHandling().authenticationEntryPoint(new AjaxAwareAuthenticationEntryPoint("/login.xhtml"));
                ;
        http.csrf().disable();
        http.headers().frameOptions().disable();
        
        


    }

    @Override
    public void configure(final WebSecurity web) throws Exception {
        final HttpSecurity http = getHttp();
        web.postBuildAction(new Runnable() {
            @Override
            public void run() {
                web.securityInterceptor(http.getSharedObject(FilterSecurityInterceptor.class));
            }
        });
    }
    
  
    
    
    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

    	auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    	
    }
}