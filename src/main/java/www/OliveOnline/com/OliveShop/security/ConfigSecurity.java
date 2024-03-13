package www.OliveOnline.com.OliveShop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


@Configuration

public class ConfigSecurity      {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager=new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery("select username,passwords,is_active from user where username=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select username,role_name  from user u inner join roles r on u.role_id=r.role_id  where username=?");

        return jdbcUserDetailsManager;
    }
@Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
    http. authorizeHttpRequests(config->config
                    .requestMatchers("/admin/**").hasAuthority("Admin")
            .requestMatchers("/home","/shop/**","/register").permitAll()
            .anyRequest()
            .authenticated())
    .formLogin(form->
            form.loginPage("/login").permitAll()
                    .successHandler(customAuthenticationSuccessHandler())
                        );

    http.logout(logout->logout
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID"));
    http.exceptionHandling(exp->exp.configure(http));
    http.csrf(csrf->csrf.disable());
    http.headers(head->head.frameOptions(frame->frame.sameOrigin()));


    return http.build();
}
    @Bean
    public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .requestMatchers(
                        // URLs to ignore from security filtering
                        "/resources/**",
                        "/static/**",
                        "/static/images/**",
                        "/static/productimages/**",
                        "/css/**",
                        "/js/**"
                );
    }

}
