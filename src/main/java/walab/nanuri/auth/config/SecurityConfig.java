package walab.nanuri.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import walab.nanuri.auth.filter.ExceptionHandlerFilter;
import walab.nanuri.auth.filter.JwtTokenFilter;
import walab.nanuri.auth.service.AuthService;
import walab.nanuri.user.entity.enums.UserStatus;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthService authService;

    // 수정
//    @Value("${custom.host.client}")
//    private List<String> client;

    @Value("${custom.jwt.secret}")
    private String SECRET_KEY;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors((cors) -> cors
                        .configurationSource(corsConfigurationSource())
                )

                .csrf(AbstractHttpConfigurer::disable)

                .addFilterBefore(new ExceptionHandlerFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtTokenFilter(authService, SECRET_KEY), UsernamePasswordAuthenticationFilter.class)

                // 세션을 사용하지 않기 때문에 STATELESS로 설정
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/api/nanuri/auth/**", "/error", "/api/nanuri/all/**", "/file/**").permitAll()
                        .requestMatchers("/api/nanuri/admin/**").hasAuthority(UserStatus.ADMIN.name())
                        .requestMatchers("/api/nanuri/**").authenticated()
                )
        ;

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // 수정
//        config.setAllowedOrigins(client);
        config.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:8080"));
        config.setAllowedMethods(Arrays.asList("POST", "GET", "PATCH", "DELETE", "PUT"));
        config.setAllowedHeaders(Arrays.asList(HttpHeaders.AUTHORIZATION, HttpHeaders.CONTENT_TYPE));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}