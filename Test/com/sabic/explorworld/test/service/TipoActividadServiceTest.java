package com.sabic.explorworld.test.service;

import java.util.List;
import com.sabic.explorworld.model.TipoActividadDTO;
import com.sabic.explorworld.service.TipoActividadService;
import com.sabic.explorworld.service.impl.TipoActividadServiceImpl;

public class TipoActividadServiceTest {

	private TipoActividadService service = null;

	public TipoActividadServiceTest() {
		this.service = new TipoActividadServiceImpl();
	}

	/**
	 * Prueba la obtención de todos los tipos de actividad.
	 */
	public void testFindAll() {
		System.out.println("--- Test: TipoActividadService.findAll() ---");
		List<TipoActividadDTO> lista = service.findAll();
		
		if (lista != null && !lista.isEmpty()) {
			System.out.println("Se encontraron " + lista.size() + " tipos de actividad:");
			for (TipoActividadDTO ta : lista) {
				System.out.println("  - [" + ta.getId() + "] " + ta.getNombre() + 
						" (Equipamiento ID: " + ta.getTipoEquipamentoId() + ")");
			}
		} else {
			System.out.println("No se encontraron tipos de actividad registrados.");
		}
	}

	/**
	 * Prueba la búsqueda por ID.
	 */
	public void testFindById(Long id) {
		System.out.println("\n--- Test: TipoActividadService.findById(" + id + ") ---");
		TipoActividadDTO ta = service.findById(id);
		
		if (ta != null) {
			System.out.println("Encontrado: " + ta.getNombre());
		} else {
			System.out.println("No se encontró el tipo de actividad con ID: " + id);
		}
	}

	/**
	 * Prueba la creación de un nuevo tipo de actividad.
	 */
	public void testCreate() {
		System.out.println("\n--- Test: TipoActividadService.create ---");
		TipoActividadDTO nuevo = new TipoActividadDTO();
		nuevo.setNombre("Senderismo Nocturno");
		nuevo.setTipoEquipamentoId(1L); // ⚠ Asegúrate que este ID de equipamiento exista

		TipoActividadDTO creado = service.create(nuevo);
		if (creado != null && creado.getId() != null) {
			System.out.println("Tipo de actividad creado con éxito. ID: " + creado.getId());
		} else {
			System.out.println("Error al crear el tipo de actividad.");
		}
	}

	/**
	 * Prueba la actualización de un tipo de actividad.
	 */
	public void testUpdate(Long id) {
		System.out.println("\n--- Test: TipoActividadService.update ---");
		TipoActividadDTO ta = service.findById(id);
		
		if (ta != null) {
			System.out.println("Nombre original: " + ta.getNombre());
			ta.setNombre(ta.getNombre() + " - Premium");
			
			service.update(ta);
			
			TipoActividadDTO actualizado = service.findById(id);
			System.out.println("Nombre actualizado: " + actualizado.getNombre());
		} else {
			System.out.println("No se encontró el ID " + id + " para actualizar.");
		}
	}

	/**
	 * Prueba la eliminación de un tipo de actividad.
	 */
	public void testDelete(Long id) {
		System.out.println("\n--- Test: TipoActividadService.delete(" + id + ") ---");
		service.delete(id);
		
		TipoActividadDTO eliminado = service.findById(id);
		if (eliminado == null) {
			System.out.println("Tipo de actividad con ID " + id + " eliminado correctamente.");
		} else {
			System.out.println("Error: El registro con ID " + id + " todavía existe.");
		}
	}

	public static void main(String[] args) {
		TipoActividadServiceTest test = new TipoActividadServiceTest();

		// 1. Listar todos
		test.testFindAll();

		// 2. Buscar uno específico
		test.testFindById(1L);

		// 3. Crear (descomentar para probar)
		// test.testCreate();

		// 4. Actualizar (usa un ID que sepas que existe)
		// test.testUpdate(1L);

		// 5. Eliminar (usa un ID que quieras borrar)
		// test.testDelete(5L);
	}
}