package com.sabic.explorworld.service;

import java.util.List;

import com.sabic.explorworld.dao.criteria.GuiaCriteria;
import com.sabic.explorworld.model.GuiaDTO;

/**
 * API del servicio de usuario.
 */
public interface GuiaService {

	/**
	 * Registra un nuevo usuario en el sistema.
	 * @param user Datos del usuario a insertar.
	 * @return Id del usuario registrado, pero tambien lo setea en el objeto,
	 * y si el registro falla retorno null.
	 */
	public GuiaDTO register(GuiaDTO guia);

	/**
	 * Autentica un usuario.
	 * @param email E-mail del usuario.
	 * @param password Contraseña en claro.
	 * @return Datos del usuario si la autenticación ha sido exitosa,
	 * o null en otro caso.
	 */
	public GuiaDTO login(String email, String password);


	/**
	 * Busca un usuario por email. 
	 * @param email
	 * @return
	 */
	public GuiaDTO findByEmail(String email);

	/**
	 * Busca usuario por su id.
	 * @param id
	 * @return
	 */
	public GuiaDTO findById(Long id);

	/**
	 * Busqueda estructurada de usuarios.
	 * @param user Encapsula los criterios de búsqueda.
	 * @return Usuarios encontrados.
	 */
	public List<GuiaDTO> findBy(GuiaCriteria user);

	/**
	 * Actualiza todos los datos de un usuario,
	 * en base a su id.
	 * @param user Datos a actualizar.
	 * @return Si se ha podido actualizar.
	 *
	 */
	public boolean update(GuiaDTO user);


	/**
	 * Elimina un usuario.
	 */
	public void delete(Long id);



	/**
	 * Modifica los datos de un usuario.
	 * @param id
	 * @param oldPassword
	 * @param newPassword
	 * @return 
	 */
	public void changePassword(GuiaDTO guia);



}