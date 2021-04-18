package it.univpm.advancedcode.cri.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.univpm.advancedcode.cri.services.UserServiceDefault;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserServiceDefault();
    }

    /**
     * Configurazione delle autenticazioni.
     *
     * @param auth AuthenticationManagerBuilder
     * @param passwordEncoder PasswordEncoder
     * @throws Exception eventuale eccezione generata
     */
    @Autowired
    public void configure(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder) throws Exception {

        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder);
    }

    /**
     * Configurazione delle autorizzazioni.
     *
     * @param http HttpSecurity
     * @throws Exception eventuale eccezione generata
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().
        antMatchers("/sign_up").not().authenticated().
        antMatchers("/").permitAll().
        antMatchers("/about").permitAll().
        antMatchers("/contacts").permitAll().
        antMatchers("/disclaimer").permitAll().
        antMatchers("/cri").permitAll().
        antMatchers("/profile").hasAnyRole("driver","admin","account").
        antMatchers("/myprenotazioni").hasAnyRole("driver").
        antMatchers("/prenotazioni").hasAnyRole("account").
        antMatchers("/prenotazione/?**/").hasAnyRole("account","driver").
        antMatchers("/prenotazione/?**/**/").hasAnyRole("account","driver").
        antMatchers("/cars_admin").hasAnyRole("admin").
        antMatchers("/cars_admin/?**/").hasAnyRole("admin").
        antMatchers("/prenotazioni_admin_show").hasAnyRole("admin").
        antMatchers("/documentazioni_admin_show").hasAnyRole("admin").
        antMatchers("/manutenzioni_admin_show").hasAnyRole("admin").
        antMatchers("/users").hasAnyRole("admin").
        antMatchers("/user/?**/**/").hasAnyRole("admin").
        antMatchers("/car/?**/").hasAnyRole("admin","account").
        antMatchers("/car/?**/**/").hasAnyRole("admin","account").
        antMatchers("/documentazioni").hasAnyRole("admin","account").
        antMatchers("/documentazione/?**/").hasAnyRole("account").
        antMatchers("/documentazione/?**/**/").hasAnyRole("account").
        antMatchers("/manutenzioni").hasAnyRole("account").
        antMatchers("/manutenzione/?**/").hasAnyRole("account").
        antMatchers("/manutenzione/?**/**").hasAnyRole("account").
        antMatchers("/allegati/").hasAnyRole("account").
        antMatchers("/allegato/?**").hasAnyRole("account").
        antMatchers("/allegato/?**/**").hasAnyRole("account").
        and().formLogin().loginPage("/login").defaultSuccessUrl("/cri")
        .failureUrl("/login?error=true").permitAll().
        and().logout().logoutSuccessUrl("/cri")
        .invalidateHttpSession(true).permitAll().
        and().csrf().disable();

    }


    // @Override
    // protected void configure(HttpSecurity http) throws Exception {

    //     http.authorizeRequests().
    //     antMatchers("/sign_up").not().authenticated().
    //     antMatchers("/").permitAll().
    //     antMatchers("/**").permitAll().
    //     antMatchers("/**/**").permitAll().
    //     antMatchers("/**/**/**").permitAll().
    //     and().formLogin().loginPage("/login").defaultSuccessUrl("/cri")
    //     .failureUrl("/login?error=true").permitAll().
    //     and().logout().logoutSuccessUrl("/cri")
    //     .invalidateHttpSession(true).permitAll().
    //     and().csrf().disable();

    // }
}