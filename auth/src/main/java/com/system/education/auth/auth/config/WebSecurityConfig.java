package com.system.education.auth.auth.config;

import com.system.education.auth.auth.filter.AuthoritiesLoggingAfterFilter;
import com.system.education.auth.auth.filter.JwtAccessDeniedHandler;
import com.system.education.auth.auth.filter.JwtAuthenticationEntryPoint;
import com.system.education.auth.auth.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.web.cors.reactive.CorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthoritiesLoggingAfterFilter authoritiesLoggingAfterFilter;
    private final CorsConfigurationSource corsConfigurationSource;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler accessDeniedHandler;


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint))
//                        .accessDeniedHandler(accessDeniedHandler))
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .addFilterAt(jwtAuthenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .addFilterAfter(authoritiesLoggingAfterFilter, SecurityWebFiltersOrder.AUTHORIZATION)
                .authorizeExchange(request -> request
                        .pathMatchers("/api/v1/education/auth/sign-in").permitAll()
                        .pathMatchers("/api/v1/education/user-account/create-user-account").permitAll()
                        .pathMatchers("/api/v1/education/role/create-role").permitAll()
                        .pathMatchers("/api/v1/education/role/lookup-role/get-roles").permitAll()
                        .pathMatchers("/api/v1/education/role/lookup-role/get-permissions").permitAll()
                        .pathMatchers("/api/v1/education/role/lookup-role/get-roles-not-global").permitAll()
                        .pathMatchers("/api/v1/education/role/lookup-role/get-roles-global").permitAll()
                        .pathMatchers("/api/v1/education/role/lookup-role/get-role-by-enterprise").permitAll()
                        .pathMatchers("/api/v1/education/user-account/lookup-user-account/verify-username/{username}").permitAll()
                        .pathMatchers("/v3/api-docs/**").permitAll()
                        .pathMatchers("/swagger-ui/**").permitAll()
                        .pathMatchers("/swagger-resources/**").permitAll()
                        .pathMatchers("/swagger-ui.html").permitAll()
                        .pathMatchers("/webjars/**").permitAll()
                        .pathMatchers("/api/v1/education/user-account/update-user-account").hasAnyAuthority("USER:GLOBAL","ALL:GLOBAL", "USER:UPDATE", "ALL:ALL")
                        .pathMatchers("/api/v1/education/user-account/lookup-user-account/get-user-accounts").hasAnyAuthority("USER:GLOBAL","ALL:GLOBAL")
                        .pathMatchers("/api/v1/education/user-account/lookup-user-account/get-all-users-by-enterprise").hasAnyAuthority("USER:VIEW", "USER:ALL", "ALL:ALL")
                        .pathMatchers("/api/v1/education/user-account/lookup-user-account/get-all-global-users").hasAnyAuthority("USER:GLOBAL", "ALL:GLOBAL")
                        .pathMatchers("/api/v1/education/user-account/lookup-user-account/get-all-not-global-users").hasAnyAuthority("USER:GLOBAL", "ALL:GLOBAL")
                        .pathMatchers("/api/v1/education/role/lookup-role/**").hasAnyAuthority("ROLE:VIEW", "USER:ALL", "ALL:ALL")
                        .pathMatchers("/api/v1/education/role/update-role").hasAnyAuthority("ROLE:UPDATE", "USER:ALL", "ALL:ALL")
                        .pathMatchers("/api/v1/education/**").authenticated()
                        .pathMatchers("/**").denyAll())
                        .build();
    }
}
