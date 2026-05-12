package com.sabic.explorworld.service.impl;

import java.util.List;

import org.apache.commons.mail.HtmlEmail;

import com.sabic.explorworld.service.MailService;

/**
 * Implementación del servicio de correo electrónico utilizando Apache Commons Email.
 */

public class MailServiceApacheImpl implements MailService {

	public MailServiceApacheImpl() {
	}
	
	public void sendEmail(String para, String asunto, String contenido) {
		try {
			HtmlEmail email = new HtmlEmail();
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(587);
			email.setStartTLSEnabled(true);
			email.setSSLOnConnect(false);
			email.setFrom("explorworldes@gmail.com", "Explorworld");
			email.setAuthentication("explorworldes@gmail.com", "orjd ycoq fsot yjyy");
			email.setSubject(asunto);
			email.setHtmlMsg(contenido);
			email.addTo(para);
			email.send();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendEmail(List<String> para, String asunto, String contenido) {
		for (String destinatario : para) {
			sendEmail(destinatario, asunto, contenido);
		}
		
	}

}
