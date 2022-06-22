package com.example.exame_backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {


    final BCryptPasswordEncoder bCryptPasswordEncoder;
    final UserDetailsImpl userDetails;


    public WebSecurityConfiguration(BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsImpl userDetails) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDetails = userDetails;
    }

    // authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {



        http.cors().and().csrf().disable().authorizeRequests()

//                .antMatchers("/api/users/**").hasRole("admin")

                .antMatchers("/api/**").permitAll()

        //                .antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL).permitAll()
        //                .antMatchers(HttpMethod.POST, SecurityConstants.LOGIN_URL).permitAll()


                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


    // authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // call user details and get the result
        auth.userDetailsService(userDetails).passwordEncoder(bCryptPasswordEncoder);

    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }


}
