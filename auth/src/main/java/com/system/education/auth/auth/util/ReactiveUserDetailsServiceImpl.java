package com.system.education.auth.auth.util;

import com.system.education.auth.account.query.api.repository.UserAccountRepository;
import com.system.education.auth.auth.reponse.AuthResponse;
import com.system.education.auth.auth.reponse.AuthUserResponse;
import com.system.education.auth.auth.reponse.ui.JWTAuthResponse;
import com.system.education.auth.core.common.LogCreated;
import com.system.education.auth.role.core.payload.RolePayload;
import com.system.education.auth.role.query.api.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ReactiveUserDetailsServiceImpl implements ReactiveUserDetailsService {
    private final UserAccountRepository userAccountRepository;
    private final RoleRepository roleRepository;
    private final RolePayload rolePayload;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return userAccountRepository.findUserAccountByUsername(username)
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("Utilisateur non trouvé")))
                .flatMap(userAccount -> {
                    if (userAccount.getRole().equals("SUPER_ADMIN")) {
                        return Mono.just(User.builder()
                                .username(userAccount.getUsername())
                                .password(userAccount.getPassword())
                                .authorities(GrantedAuthorities.get(rolePayload.stringToList("ALL:ALL")))
                                .build());
                    } else if (userAccount.getRole().equals("PARENT")) {
                        return Mono.just(User.builder()
                                .username(userAccount.getUsername())
                                .password(userAccount.getPassword())
                                .authorities(GrantedAuthorities.get(rolePayload.stringToList("PARENT")))
                                .build());
                    }
                    return roleRepository.findById(userAccount.getRole())
                            .map(role -> User.builder()
                                    .username(userAccount.getUsername())
                                    .password(userAccount.getPassword())
                                    .authorities(GrantedAuthorities.get(rolePayload.stringToList(role.getPermissions())))
                                    .build());
                });
    }
}
