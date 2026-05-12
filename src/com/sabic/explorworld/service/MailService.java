package com.sabic.explorworld.service;

import java.util.List;

public interface MailService {

	/**
	 * Envía un correo electrónico a un único destinatario.
	 * 
	 * @param para      Dirección de correo electrónico del destinatario.
	 * @param asunto    Asunto del correo electrónico.
	 * @param contenido Contenido del correo electrónico en formato HTML.
	 */
	public void sendEmail(String para, String asunto, String contenido);
	
	/**
	 * Envía un correo electrónico a múltiples destinatarios.
	 * 
	 * @param para      Lista de direcciones de correo electrónico de los destinatarios.
	 * @param asunto    Asunto del correo electrónico.
	 * @param contenido Contenido del correo electrónico en formato HTML.
	 */
	public void sendEmail(List<String> para, String asunto, String contenido);
	
}
