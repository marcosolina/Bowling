package com.marco.bownling.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.marco.bownling.services.implementation.MarcoBowlingGame;
import com.marco.bownling.services.implementation.MarcoBowlingManager;
import com.marco.bownling.services.interfaces.BowlingGameInterface;
import com.marco.bownling.services.interfaces.BownlingManagerInterface;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
@EnableScheduling
public class ServiceConfig {
    /**
     * Stuff for thymeleaf
     * @return LayoutDialect
     */
    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
    
    /**
     * Lo scope è prototype perchè
     * mi serve una istanza per ogni giocatore
     * 
     * @return
     */
    @Bean
    @Scope("prototype")
    public BowlingGameInterface bowlingGameInterface() {
    	return new MarcoBowlingGame();
    }
    
    @Bean
    public BownlingManagerInterface bownlingManagerInterface() {
    	return new MarcoBowlingManager();
    }
}
