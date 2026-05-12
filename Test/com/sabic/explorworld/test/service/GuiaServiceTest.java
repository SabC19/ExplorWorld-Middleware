package com.sabic.explorworld.test.service;

import java.util.Date;
import java.util.List;

import com.sabic.explorworld.dao.criteria.GuiaCriteria;
import com.sabic.explorworld.model.GuiaDTO;
import com.sabic.explorworld.service.GuiaService;
import com.sabic.explorworld.service.impl.GuiaServiceImpl;

public class GuiaServiceTest {

	private GuiaService service = null;

	public GuiaServiceTest() {
		this.service = new GuiaServiceImpl();
	}

	/**
	 * Prueba el registro (create) de un nuevo guía.
	 */
	public void testRegister() {
		System.out.println("\n--- Test: GuiaService.register ---");
		GuiaDTO nuevo = new GuiaDTO();
		nuevo.setNombre("Carlos");
		nuevo.setPrimerApellido("Ruiz");
		nuevo.setNie("12345678X");
		nuevo.setEmail("carlos.guia@explorworld.com");
		nuevo.setTelefono("600111222");
		nuevo.setFechaNacimiento(new Date()); // Ajustar a una fecha real si hay validación de edad
		nuevo.setPassword("secreto123");
		nuevo.setGeneroId(1L); // 1: Masculino, 2: Femenino (según tu DB)

		GuiaDTO registrado = service.register(nuevo);
		if (registrado != null && registrado.getId() != null) {
			System.out.println("Guía registrado exitosamente con ID: " + registrado.getId());
		} else {
			System.out.println("Fallo al registrar el guía.");
		}
	}

	/**
	 * Prueba el inicio de sesión.
	 */
	public void testLogin(String email, String pass) {
		System.out.println("\n--- Test: GuiaService.login ---");
		GuiaDTO guia = service.login(email, pass);
		if (guia != null) {
			System.out.println("Login exitoso. Bienvenido, " + guia.getNombre() + " " + guia.getPrimerApellido());
		} else {
			System.out.println("Login fallido para: " + email);
		}
	}

	/**
	 * Prueba la búsqueda por ID.
	 */
	public void testFindById(Long id) {
		System.out.println("\n--- Test: GuiaService.findById(" + id + ") ---");
		GuiaDTO guia = service.findById(id);
		if (guia != null) {
			System.out.println("Guía encontrado: " + guia.getNombre() + " | Email: " + guia.getEmail());
		} else {
			System.out.println("No se encontró guía con ID: " + id);
		}
	}

	/**
	 * Prueba las búsquedas estructuradas por criterios (Criteria).
	 */
	public void testFindByCriteria() {
		System.out.println("\n=== INICIANDO PRUEBAS DE CRITERIOS PARA GUÍAS ===");

		// 1. FILTRO POR NOMBRE
		GuiaCriteria critNombre = new GuiaCriteria();
		critNombre.setNombre("Carlos");
		ejecutarBusqueda("Filtro por Nombre (Carlos)", critNombre);

		// 2. FILTRO POR GÉNERO (Ejemplo: ID 2 - Femenino)
		GuiaCriteria critGenero = new GuiaCriteria();
		critGenero.setGeneroId(2L);
		ejecutarBusqueda("Filtro por Género (ID 2)", critGenero);
	}

	/**
	 * Método auxiliar para imprimir resultados de búsqueda.
	 */
	private void ejecutarBusqueda(String descripcion, GuiaCriteria criteria) {
		System.out.println("\n--- " + descripcion + " ---");
		List<GuiaDTO> resultados = service.findBy(criteria);

		if (resultados != null && !resultados.isEmpty()) {
			System.out.println("Se encontraron " + resultados.size() + " guías:");
			for (GuiaDTO g : resultados) {
				System.out.println("  - [" + g.getId() + "] " + g.getNombre() + " " + g.getPrimerApellido() + " | NIE: " + g.getNie());
			}
		} else {
			System.out.println("No se encontraron resultados para este criterio.");
		}
	}

	/**
	 * Prueba la actualización de datos del guía.
	 */
	public void testUpdate(Long id) {
		System.out.println("\n--- Test: GuiaService.update ---");
		GuiaDTO guia = service.findById(id);
		if (guia != null) {
			System.out.println("Nombre actual: " + guia.getNombre());
			guia.setNombre(guia.getNombre() + " (Modificado)");
			
			boolean actualizado = service.update(guia);
			if (actualizado) {
				GuiaDTO check = service.findById(id);
				System.out.println("Actualización exitosa. Nuevo nombre: " + check.getNombre());
			} else {
				System.out.println("El servicio reportó que no se pudo actualizar.");
			}
		} else {
			System.out.println("Guía con ID " + id + " no existe.");
		}
	}

	/**
	 * Prueba el cambio de contraseña.
	 */
	public void testChangePassword(Long id) {
		System.out.println("\n--- Test: GuiaService.changePassword ---");
		GuiaDTO guia = service.findById(id);
		if (guia != null) {
			guia.setPassword("nuevaPassword2026");
			service.changePassword(guia);
			System.out.println("Solicitud de cambio de contraseña enviada para: " + guia.getEmail());
		}
	}

	/**
	 * Prueba la eliminación de un guía.
	 */
	public void testDelete(Long id) {
		System.out.println("\n--- Test: GuiaService.delete(" + id + ") ---");
		service.delete(id);
		GuiaDTO eliminado = service.findById(id);
		if (eliminado == null) {
			System.out.println("Guía eliminado correctamente.");
		} else {
			System.out.println("El guía sigue existiendo.");
		}
	}

	public static void main(String[] args) {
		GuiaServiceTest test = new GuiaServiceTest();

		// 1. Registro
		test.testRegister();

		// 2. Login (Usar credenciales que existan en tu DB)
		// test.testLogin("carlos.guia@explorworld.com", "secreto123");

		// 3. Búsquedas
		// test.testFindById(1L);
		// test.testFindByCriteria();

		// 4. Actualización y Password
		// test.testUpdate(1L);
		// test.testChangePassword(1L);

		// 5. Borrado
		// test.testDelete(1L);
	}
}