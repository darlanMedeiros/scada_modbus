package br.com.api.bootstrap;

import jakarta.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

import br.com.main.bootstrap.SystemBootstrap;

@Configuration
public class CoreBootstrap {

    @PostConstruct
    public void startCore() {
        SystemBootstrap.start();
    }
}