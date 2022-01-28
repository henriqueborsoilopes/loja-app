package com.curso.lojaapp.services;

import org.springframework.mail.SimpleMailMessage;

import com.curso.lojaapp.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
