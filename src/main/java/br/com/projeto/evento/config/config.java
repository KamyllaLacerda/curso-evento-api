package br.com.projeto.evento.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.projeto.evento.services.EmailService;

@Configuration
public class config {
	
	@Bean
	public EmailService emailService() {
		return new EmailService();
	}

}
