package cl.hda.regapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // require all requests to be authenticated except for the resources
    http.authorizeRequests().antMatchers("/javax.faces.resource/**","/h2-console/**","/register.xhtml")
    .permitAll().anyRequest().authenticated();
    // login
    http.formLogin().loginPage("/login.xhtml").permitAll()
    .defaultSuccessUrl("/listar.xhtml", true)
    .failureUrl("/login.xhtml?error=true");
        
    // logout
    http.logout().logoutSuccessUrl("/login.xhtml");
    // not needed as JSF 2.2 is implicitly protected against CSRF
    http.csrf().disable();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth)
      throws Exception {
    auth.inMemoryAuthentication().withUser("hmora")
        .password("{noop}1234").roles("USER").and()
        .withUser("admin").password("{noop}1234").roles("ADMIN");
  }
}