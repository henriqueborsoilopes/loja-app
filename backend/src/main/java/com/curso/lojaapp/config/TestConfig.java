
package com.curso.lojaapp.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.curso.lojaapp.services.DBService;
import com.curso.lojaapp.services.EmailService;
import com.curso.lojaapp.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.instantiateTestDatabase();
		return true;
	}
	
	@Bean
    public JavaMailSender javaMailSender() {
        return new JavaMailSenderImpl();
    }
	
	@Bean
	public EmailService emailService() {
		return new MockEmailService();
	}
}
