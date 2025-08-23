package com.system.education.auth.auth.util;

import com.system.education.auth.account.query.api.repository.UserAccountRepository;
import com.system.education.auth.core.common.Status;
import com.system.education.auth.core.exception.response.DeactivateException;
import com.system.education.auth.core.exception.response.PasswordException;
import com.system.education.auth.core.exception.response.UsernameException;
import com.system.education.auth.role.core.payload.RolePayload;
import com.system.education.auth.role.query.api.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserAuthenticationProvider implements ReactiveAuthenticationManager {
    private final PasswordEncoder passwordEncoder;
    private final UserAccountRepository userAccountRepository;
    private final RoleRepository roleRepository;
    private final RolePayload rolePayload;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        return userAccountRepository.findUserAccountByUsername(username)
                .switchIfEmpty(Mono.error(new UsernameException("Le nom d'utilisateur est incorrect")))
                .flatMap(user -> {
                    if (passwordEncoder.matches(password, user.getPassword())) {
                        if (user.getStatus().equals(Status.disable())) {
                            return Mono.error(new DeactivateException("Votre compte est desactive"));
                        }
                        if (user.getRole().equals("SUPER_ADMIN")) {
                            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                                    username, password, GrantedAuthorities.get(rolePayload.stringToList("ALL:ALL")));
                            token.setDetails(authentication.getDetails());
                            return Mono.just(token);
                        } else if (user.getRole().equals("PARENT")) {
                            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                                    username, password, GrantedAuthorities.get(rolePayload.stringToList("PARENT")));
                            token.setDetails(authentication.getDetails());
                            return Mono.just(token);
                        } else {
                            return roleRepository.findById(user.getRole())
                                    .flatMap(role -> {
                                        if (role == null) {
                                            return Mono.error(new BadCredentialsException("Role not found for user"));
                                        }
                                        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                                                username, password, GrantedAuthorities.get(rolePayload.stringToList(role.getPermissions())));
                                        token.setDetails(authentication.getDetails());
                                        return Mono.just(token);
                                    });
                        }

                    } else {
                        return Mono.error(new PasswordException("Le mot de passe est incorrect"));
                    }
                });
    }
}
