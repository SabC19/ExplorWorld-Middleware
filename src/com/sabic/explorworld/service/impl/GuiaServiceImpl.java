package com.sabic.explorworld.service.impl;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.sabic.explorworld.dao.GuiaDAO;
import com.sabic.explorworld.dao.criteria.GuiaCriteria;
import com.sabic.explorworld.model.GuiaDTO;
import com.sabic.explorworld.service.GuiaService;
import com.sabic.explorworld.service.MailService;

public class GuiaServiceImpl implements GuiaService {

	private EncryptionServiceImpl encryptionService;
	private GuiaDAO guiaDAO;
	private MailService mailService;

	public GuiaServiceImpl() {
		encryptionService = new EncryptionServiceImpl();
		guiaDAO = new GuiaDAO();
		mailService = new MailServiceApacheImpl();
	}

	@Override
	public GuiaDTO register(GuiaDTO guia) {
		// 1. Verificar si el email ya está registrado
		GuiaDTO guiaExistente = guiaDAO.findByEmail(guia.getEmail());
		if (guiaExistente != null) {
			return null;
		}

		// 2. Encriptar la contraseña
		String passwordEncrypted = encryptionService.encrypt(guia.getPassword());
		guia.setPassword(passwordEncrypted);

		
		GuiaDTO guiaRegistrado= guiaDAO.create(guia);

		if (guiaRegistrado != null) {
			mailService.sendEmail(
					guiaRegistrado.getEmail(),
					"Registro en ExplorWorld",
					"Gracias por registrarte en ExplorWorld."
					+ " Ya puedes iniciar sesión" + guiaRegistrado.getNombre() +"."
			);
		}
		return guiaRegistrado;
	}

	@Override
	public GuiaDTO login(String email, String password) {
		// Busca usuario guia por email
		GuiaDTO guia = guiaDAO.findByEmail(email);
		if (guia == null) {
			return null;
		}

		if (encryptionService.check(password, guia.getPassword())) {
			return guia;
		} else {
			return null;
		}
	}
	
	@Override
	public GuiaDTO findByEmail(String email) {
		return guiaDAO.findByEmail(email);
	}

	@Override
	public GuiaDTO findById(Long id) {
		return guiaDAO.findById(id);
	}

	@Override
	public boolean update(GuiaDTO guia) {
		return guiaDAO.update(guia);
	}

	@Override
	public void delete(Long id) {
		guiaDAO.delete(id);
	}

	@Override
	public void changePassword(GuiaDTO guia) {

		String hashSeguro = BCrypt.hashpw(guia.getPassword(), BCrypt.gensalt());
		guia.setPassword(hashSeguro);

		guiaDAO.update(guia);
	}

	@Override
	public List<GuiaDTO> findBy(GuiaCriteria criteria) {
		return guiaDAO.findBy(criteria);
	}

}
