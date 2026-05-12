package com.sabic.explorworld.test.service;

import java.util.List;

import com.sabic.explorworld.dao.criteria.ActividadCriteria;
import com.sabic.explorworld.model.ActividadDTO;
import com.sabic.explorworld.service.ActividadService;
import com.sabic.explorworld.service.impl.ActividadServiceImpl; // Asumiendo que esta es la impl

public class ActividadServiceTest {

	private ActividadService service = null;

	public ActividadServiceTest() {
		// Inicializamos la implementación del servicio
		this.service = new ActividadServiceImpl();
	}

	/**
	 * Prueba la recuperación de una actividad por su ID.
	 */
	public void testFindById(Long id) {
		System.out.println("--- Test: ActividadService.findById(" + id + ") ---");
		ActividadDTO act = service.findById(id);
		if (act != null) {
			System.out.println("Actividad encontrada: " + act.getNombre());
			System.out.println("  Descripción: " + act.getDescripcion());
		} else {
			System.out.println("No se encontró la actividad con ID: " + id);
		}
	}

	/**
	 * Prueba los filtros de búsqueda según ActividadCriteria.
	 */
	public void testFindByCriteriaExtendido() {
		System.out.println("=== INICIANDO PRUEBAS DE CRITERIOS DE ACTIVIDAD ===");

		// 1. FILTRO POR CATEGORÍA (Ejemplo: Senderismo - ID 5)
		ActividadCriteria critCat = new ActividadCriteria();
		critCat.setActividadTipoId(5L); 
		ejecutarBusqueda("Filtro por Categoría (ID 5)", critCat);

		// 2. FILTRO POR UBICACIÓN (Ejemplo: Destino ID 10)
		ActividadCriteria critDestino = new ActividadCriteria();
		critDestino.setFinLugarId(10L);
		ejecutarBusqueda("Filtro por Destino (ID 10)", critDestino);

		// 3. FILTRO COMBINADO: Actividades de bajo coste en un destino
		ActividadCriteria critCombo = new ActividadCriteria();
		critCombo.setFinLugarId(10L);
		critCombo.setPrecioMax(50.0);
		ejecutarBusqueda("Filtro Combinado: Destino 10 + Precio Max 50€", critCombo);
	}

	/**
	 * Método auxiliar para imprimir los resultados de cada búsqueda.
	 */
	private void ejecutarBusqueda(String descripcion, ActividadCriteria criteria) {
		System.out.println("\n--- " + descripcion + " ---");
		List<ActividadDTO> resultados = service.findByCriteria(criteria);

		if (resultados != null && !resultados.isEmpty()) {
			System.out.println("Se encontraron " + resultados.size() + " actividades:");
			for (ActividadDTO dto : resultados) {
				System.out.println("  - [" + dto.getId() + "] " + dto.getNombre() + 
						" | Precio: " + dto.getPrecioMin() + "€");
			}
		} else {
			System.out.println("No se encontraron resultados para este criterio.");
		}
	}

	/**
	 * Prueba la creación de una nueva actividad.
	 */
	public void testCreate() {
		System.out.println("\n--- Test: ActividadService.create ---");
		ActividadDTO nueva = new ActividadDTO();
		nueva.setNombre("Tour Gastronómico Test");
		nueva.setDescripcion("Probando la creación desde el service");
		nueva.setPrecioMin(25.50);

		ActividadDTO creada = service.create(nueva);
		if (creada != null && creada.getId() != null) {
			System.out.println("Actividad creada exitosamente con ID: " + creada.getId());
		} else {
			System.out.println("Fallo al crear la actividad.");
		}
	}

	/**
	 * Modifica una actividad existente para verificar el update.
	 */
	public void updateTest(Long id) {
		System.out.println("\n--- Test: ActividadService.update ---");
		ActividadDTO actividad = service.findById(id);
		if (actividad != null) {
			System.out.println("Antes: " + actividad.getNombre());
			actividad.setNombre(actividad.getNombre() + " (Actualizada)");
			actividad.setPrecioMin(99.99);
			
			service.update(actividad);

			ActividadDTO updated = service.findById(id);
			System.out.println("Después: " + updated.getNombre() + " | Nuevo Precio: " + updated.getPrecioMin());
		} else {
			System.out.println("No se encontró la actividad con ID " + id + " para actualizar.");
		}
	}

	/**
	 * Prueba la eliminación de una actividad.
	 */
	public void deleteTest(Long id) {
		System.out.println("\n--- Test: ActividadService.delete(" + id + ") ---");
		service.delete(id);
		ActividadDTO deleted = service.findById(id);
		if (deleted == null) {
			System.out.println("Actividad con ID " + id + " eliminada correctamente.");
		} else {
			System.out.println("La actividad con ID " + id + " aún existe.");
		}
	}

	public static void main(String[] args) {
		ActividadServiceTest test = new ActividadServiceTest();

		// 1. Buscar una actividad conocida
		test.testFindById(1L);

		// 2. Probar creación
		// test.testCreate();

		// 3. Probar criterios
		// test.testFindByCriteriaExtendido();

		// 4. Probar actualización (usa un ID que sepas que existe)
		// test.updateTest(1L);

		// 5. Probar borrado
		// test.deleteTest(2L);
	}
}