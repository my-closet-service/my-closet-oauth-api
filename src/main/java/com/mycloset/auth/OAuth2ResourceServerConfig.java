package com.mycloset.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;

    @Value("${security.oauth2.resource.token-info-uri}")
    private String checkTokenEndpointUrl;

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.headers().frameOptions().disable();
        http.authorizeRequests()
                .antMatchers("/v1/users").access("#oauth2.hasScope('read')")
                .anyRequest().authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        RemoteTokenServices remoteTokenService = new RemoteTokenServices();
        remoteTokenService.setClientId(clientId);
        remoteTokenService.setClientSecret(clientSecret);
        remoteTokenService.setCheckTokenEndpointUrl(checkTokenEndpointUrl);

        resources.tokenServices(remoteTokenService);
    }
}
