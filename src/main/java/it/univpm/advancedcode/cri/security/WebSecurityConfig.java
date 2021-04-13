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
    // @Override
    // protected void configure(HttpSecurity http) throws Exception {

    //     http.authorizeRequests().
    //     antMatchers("/sign_up").not().authenticated().
    //     antMatchers("/").permitAll().
    //     antMatchers("/about").permitAll().
    //     antMatchers("/contacts").permitAll().
    //     antMatchers("/disclaimer").permitAll().
    //     antMatchers("/cri").permitAll().
    //     antMatchers("/users").hasAnyRole("admin").
    //     antMatchers("/user/?**/**").hasAnyRole("admin").
    //     antMatchers("/documentazioni").hasAnyRole("admin","account").
    //     antMatchers("/documentazione/?**/**").hasAnyRole("admin","account").
    //     antMatchers("/manutenzioni").hasAnyRole("admin","account").
    //     antMatchers("/manutenzione/?**/**").hasAnyRole("admin","account").
    //     antMatchers("/allegati/?**").hasAnyRole("admin","account").
    //     antMatchers("/prenotazioni").hasAnyRole("admin","account").
    //     antMatchers("/myprenotazioni").hasAnyRole("driver")
    //     antMatchers("/prenotazione/?**").hasAnyRole("admin","account","driver").
    //     antMatchers("/cars").hasAnyRole("admin","account").
    //     antMatchers("/car/**").hasAnyRole("admin","account").
    //     and().formLogin().loginPage("/login").defaultSuccessUrl("/cri")
    //     .failureUrl("/login?error=true").permitAll().
    //     and().logout().logoutSuccessUrl("/cri")
    //     .invalidateHttpSession(true).permitAll().
    //     and().csrf().disable();

    // }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().
        antMatchers("/sign_up").not().authenticated().
        antMatchers("/").permitAll().
        antMatchers("/**").permitAll().
        antMatchers("/**/**").permitAll().
        antMatchers("/**/**/**").permitAll().
        and().formLogin().loginPage("/login").defaultSuccessUrl("/cri")
        .failureUrl("/login?error=true").permitAll().
        and().logout().logoutSuccessUrl("/cri")
        .invalidateHttpSession(true).permitAll().
        and().csrf().disable();

    }
}