package az.bina.main.verify.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"az.bina"})
@RequiredArgsConstructor
public class UserRegistrationSecurityConfig {
    private final UserRegistrationDetailsService service;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth=new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder());
        auth.setUserDetailsService(service);
        return auth;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.cors()
                .and().csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/assets/**","/register/**")
                .permitAll().and().authorizeHttpRequests()
                .requestMatchers("/elan/**","/category/**","/agency/**","/mail/**")
                .hasAnyAuthority("ADMIN").and().authorizeHttpRequests()
                .requestMatchers("/**")
                .hasAnyAuthority("USER","ADMIN")
                .and()
                .authorizeHttpRequests()
                .anyRequest()
                .authenticated().and()
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/myLogin")
                .permitAll().and()
                .logout()
                .logoutUrl("/myLogout")
                .logoutSuccessUrl("/login")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll().and().build();
    }
}
