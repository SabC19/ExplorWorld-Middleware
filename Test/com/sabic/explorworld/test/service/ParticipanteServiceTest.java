package com.sabic.explorworld.test.service;

import java.util.Date;
import java.util.List;

import com.sabic.explorworld.dao.criteria.ParticipanteCriteria;
import com.sabic.explorworld.model.Participante;
import com.sabic.explorworld.service.ParticipanteService;
import com.sabic.explorworld.service.impl.ParticipanteServiceImpl;

public class ParticipanteServiceTest {

	private ParticipanteService service = null;

	public ParticipanteServiceTest() {
		// Asumiendo que esta es tu implementación
		this.service = new ParticipanteServiceImpl();
	}

	/**
	 * Prueba la creación (registro) de un nuevo participante.
	 */
	public void testCreate() {
		System.out.println("\n--- Test: ParticipanteService.create ---");
		Participante nuevo = new Participante();
		nuevo.setNombre("Lucía");
		nuevo.setPrimerApellido("Mendoza");
		nuevo.setEmail("lucia.test@mail.com");
		nuevo.setTelefono("655443322");
		nuevo.setFechaNacimiento(new Date()); 
		nuevo.setPassword("pasi123");
		nuevo.setGeneroId(2L); // 2: Femenino (según estándar habitual)

		Participante creado = service.create(nuevo);
		if (creado != null && creado.getId() != null) {
			System.out.println("Participante creado exitosamente con ID: " + creado.getId());
		} else {
			System.out.println("Error: No se pudo crear el participante.");
		}
	}

	/**
	 * Prueba la búsqueda por ID.
	 */
	public void testFindById(Long id) {
		System.out.println("\n--- Test: ParticipanteService.findById(" + id + ") ---");
		Participante p = service.findById(id);
		if (p != null) {
			System.out.println("Participante encontrado: " + p.getNombre() + " " + p.getPrimerApellido());
			System.out.println("  Email: " + p.getEmail() + " | Tel: " + p.getTelefono());
		} else {
			System.out.println("No se encontró el participante con ID: " + id);
		}
	}

	/**
	 * Prueba la búsqueda por Email (parcial).
	 */
	public void testFindByEmail(String email) {
		System.out.println("\n--- Test: ParticipanteService.findByEmail('" + email + "') ---");
		List<Participante> lista = service.findByEmail(email);
		if (lista != null && !lista.isEmpty()) {
			System.out.println("Se encontraron " + lista.size() + " coincidencias:");
			lista.forEach(p -> System.out.println("  - " + p.getEmail() + " (" + p.getNombre() + ")"));
		} else {
			System.out.println("No hay participantes con ese email.");
		}
	}

	/**
	 * Prueba la búsqueda avanzada por Criteria.
	 */
	public void testFindByCriteria() {
		System.out.println("\n=== INICIANDO PRUEBAS DE CRITERIOS DE PARTICIPANTES ===");

		// 1. FILTRO POR NOMBRE Y APELLIDO
		ParticipanteCriteria crit = new ParticipanteCriteria();
		crit.setNombre("Lucía");
		crit.setPrimerApellido("Mendoza");
		ejecutarBusqueda("Filtro por Nombre y Apellido", crit);

		// 2. FILTRO POR GÉNERO
		ParticipanteCriteria critGen = new ParticipanteCriteria();
		critGen.setGeneroId(1L); // Masculino
		ejecutarBusqueda("Filtro por Género (ID 1)", critGen);
	}

	/**
	 * Método auxiliar para imprimir resultados.
	 */
	private void ejecutarBusqueda(String descripcion, ParticipanteCriteria criteria) {
		System.out.println("\n--- " + descripcion + " ---");
		List<Participante> resultados = service.findByCriteria(criteria);

		if (resultados != null && !resultados.isEmpty()) {
			System.out.println("Resultados: " + resultados.size());
			for (Participante p : resultados) {
				System.out.println("  - ID: " + p.getId() + " | " + p.getNombre() + " | Email: " + p.getEmail());
			}
		} else {
			System.out.println("Sin resultados para este criterio.");
		}
	}

	/**
	 * Prueba la actualización de un participante.
	 */
	public void testUpdate(Long id) {
		System.out.println("\n--- Test: ParticipanteService.update ---");
		Participante p = service.findById(id);
		if (p != null) {
			String antiguoNombre = p.getNombre();
			p.setNombre(antiguoNombre + " MOD");
			p.setTelefono("900000000");

			service.update(p);
			
			Participante actualizado = service.findById(id);
			System.out.println("Antes: " + antiguoNombre + " | Ahora: " + actualizado.getNombre());
			System.out.println("Nuevo teléfono: " + actualizado.getTelefono());
		} else {
			System.out.println("No se puede actualizar: ID " + id + " no encontrado.");
		}
	}

	/**
	 * Prueba el borrado de un participante.
	 */
	public void testDelete(Long id) {
		System.out.println("\n--- Test: ParticipanteService.delete(" + id + ") ---");
		service.delete(id);
		
		Participante p = service.findById(id);
		if (p == null) {
			System.out.println("Participante con ID " + id + " eliminado con éxito.");
		} else {
			System.out.println("ERROR: El participante aún existe.");
		}
	}

	public static void main(String[] args) {
		ParticipanteServiceTest test = new ParticipanteServiceTest();

		// 1. Crear uno nuevo para probar
		// test.testCreate();

		// 2. Búsquedas
		test.testFindById(1L);
		test.testFindByEmail("gmail.com"); // Búsqueda parcial por dominio
		test.testFindByCriteria();

		// 3. Modificar (usa un ID que exista en tu DB)
		// test.testUpdate(1L);

		// 4. Eliminar
		// test.testDelete(10L);
	}
}