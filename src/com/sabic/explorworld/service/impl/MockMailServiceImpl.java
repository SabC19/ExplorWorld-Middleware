package com.sabic.explorworld.service.impl;

import java.util.List;

import com.sabic.explorworld.service.MailService;

public class MockMailServiceImpl implements MailService {

	public MockMailServiceImpl() {
	}

	public void sendEmail(String para, String asunto, String contenido) {
		// Sysout.println to simulate email sending
		System.out.println("Enviando email a: " + para);
		System.out.println(contenido);
		System.out.println("Enviado.");
		
		
	}

	@Override
	public void sendEmail(List<String> para, String asunto, String contenido) {
		// 
		for (String destinatario : para) {
			sendEmail(destinatario, asunto, contenido);
		}
		System.out.println("Enviado a" + para.size() + " destinatarios.");
		
	}
	
	
	
	
	
}
