package com.system.education.auth.auth.controller;

import com.system.education.auth.auth.command.CreateSignInCommand;
import com.system.education.auth.auth.filter.JwtTokenProvider;
import com.system.education.auth.auth.reponse.AuthResponse;
import com.system.education.auth.auth.reponse.AuthUserResponse;
import com.system.education.auth.auth.reponse.ui.JWTAuthResponse;
import com.system.education.auth.core.common.LogCreated;
import com.system.education.auth.account.query.api.repository.UserAccountRepository;
import com.system.education.auth.core.common.Status;
import com.system.education.auth.core.dto.MessageResponse;
import com.system.education.auth.core.exception.response.DeactivateException;
import com.system.education.auth.core.exception.response.PasswordException;
import com.system.education.auth.core.exception.response.UsernameException;
import com.system.education.auth.role.core.payload.RolePayload;
import com.system.education.auth.role.query.api.repository.RoleRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/education/auth/sign-in")
@Tag(name = "auth", description = "Auth sign in REST API")
public class AuthController {
    private final JwtTokenProvider tokenProvider;
    private final ReactiveAuthenticationManager authenticationManager;
    private final UserAccountRepository userAccountRepository;
    private final RoleRepository roleRepository;
    private final RolePayload rolePayload;

    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Sign in or login user REST API")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> authenticateUser(@Valid @RequestBody CreateSignInCommand signInRequest) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(signInRequest.username(), signInRequest.password());

        var jwtAuthResponse = authenticationManager.authenticate(authToken)
                .flatMap(auth -> {
                        String token = tokenProvider.generateToken(auth);

                        return userAccountRepository.findUserAccountByUsername(auth.getName())
                                .flatMap(user -> {
                                    if (user.getStatus().equals(Status.disable())) {
                                        return Mono.just(ResponseEntity.ok(new JWTAuthResponse(false,
                                                new AuthResponse("Your account is deactivate, you can't be connected"), null)));
                                    } else {
                                        if (user.getRole().equals("SUPER_ADMIN")) {
                                            return Mono.just(ResponseEntity.ok(
                                                    new JWTAuthResponse(true, new AuthResponse(token),
                                                            new AuthUserResponse(user.getFullName(), user.getUsername(),
                                                                    user.getUserCode(), user.getEstablishmentCode(),
                                                                    user.getEstablishmentName(), LogCreated.Date(),
                                                                    "SUPER_ADMIN", rolePayload.stringToList("ALL:ALL"))
                                                    )
                                            ));
                                        } else if (user.getRole().equals("PARENT")) {
                                            return Mono.just(ResponseEntity.ok(
                                                    new JWTAuthResponse(true, new AuthResponse(token),
                                                            new AuthUserResponse(user.getFullName(), user.getUsername(),
                                                                    user.getUserCode(), user.getEstablishmentCode(),
                                                                    user.getEstablishmentName(), LogCreated.Date(),
                                                                    "PARENT", rolePayload.stringToList("PARENT"))
                                                    )
                                            ));
                                        } else {
                                        return roleRepository.findById(user.getRole())
                                                .flatMap(role ->  Mono.just(ResponseEntity.ok(
                                                        new JWTAuthResponse(true, new AuthResponse(token),
                                                                new AuthUserResponse(user.getFullName(), user.getUsername(),
                                                                        user.getUserCode(), user.getEstablishmentCode(),
                                                                        user.getEstablishmentName(), LogCreated.Date(),
                                                                        role.getRoleName(), rolePayload.stringToList(role.getPermissions()))
                                                        )
                                                )));
                                        }
                                    }
                                });
                })
                .onErrorResume(e -> {
                    String message = "Erreur d’authentification "+ e.getMessage();

                    if (e instanceof UsernameException || e instanceof PasswordException || e instanceof DeactivateException) {
                        message = e.getMessage();
                    }
                    return Mono.just(ResponseEntity.ok(new JWTAuthResponse(false, new AuthResponse(message), null)));
                });

        return jwtAuthResponse.map(jwtAuthResponseResponseEntity -> {
            if (Objects.requireNonNull(jwtAuthResponseResponseEntity.getBody()).success()) {
                return ResponseEntity.ok(jwtAuthResponseResponseEntity.getBody());
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new MessageResponse(false, jwtAuthResponseResponseEntity.getBody().auth().accessToken()));
            }
        });
    }
}
