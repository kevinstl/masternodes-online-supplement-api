package com.cryptocurrencyservices.masternodessuplement.api.security.oauth2;

import com.cryptocurrencyservices.masternodessuplement.api.security.SecurityUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class JwtAuthorityExtractor extends JwtAuthenticationConverter {

    public JwtAuthorityExtractor() {
    }

    @Override
    protected Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        return SecurityUtils.extractAuthorityFromClaims(jwt.getClaims());
    }
}
