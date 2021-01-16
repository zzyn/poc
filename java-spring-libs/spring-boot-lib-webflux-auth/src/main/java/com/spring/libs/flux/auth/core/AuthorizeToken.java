package com.spring.libs.flux.auth.core;

import com.spring.libs.flux.auth.core.entities.JwtAuthenticationToken;
import com.spring.libs.flux.auth.core.entities.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class AuthorizeToken {
    private final RetrieveAcls retrieveAcls;
    private final ExtractPrograms extractPrograms;
    private final Set<String> authorizedPrograms;

    @Autowired
    public AuthorizeToken(@Autowired(required = false) RetrieveAcls retrieveAcls,
                          ExtractPrograms extractPrograms,
                          SecurityProperties securityProperties) {
        this.retrieveAcls = retrieveAcls;
        this.extractPrograms = extractPrograms;
        this.authorizedPrograms = Optional.ofNullable(securityProperties.getAuthorizedPrograms())
                .map(ps -> new HashSet<>(ps))
                .orElse(new HashSet<>());
    }

    public Mono<Authentication> execute(JwtAuthenticationToken inputToken) {
        String accessRefToken = inputToken.getAccessToken();

        return retrieveAcls.execute(accessRefToken)
                .map(aclList -> extractPrograms.execute(aclList))
                .flatMap(programList -> {
                    programList.retainAll(authorizedPrograms);
                    if (programList.size() == 0) {
                        return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized program."));
                    }
                    List<GrantedAuthority> roles = programsToRoles(programList);
                    JwtAuthenticationToken outputToken = new JwtAuthenticationToken(
                            inputToken.getIdToken(),
                            accessRefToken,
                            roles);
                    outputToken.setUserId(inputToken.getUserId());
                    return Mono.just(outputToken);
                });
    }

    private List<GrantedAuthority> programsToRoles(List<String> programs) {
        return programs
                .stream()
                .map(program -> "ROLE_" + program)
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
    }
}
