package com.sabic.explorworld.test.service;

import com.sabic.explorworld.model.LugarDTO;
import com.sabic.explorworld.service.LugarService;
import com.sabic.explorworld.service.impl.LugarServiceImpl;

public class LugarServiceTest {

	private LugarService service = null;

	public LugarServiceTest() {
		// Inicializamos la implementación
		this.service = new LugarServiceImpl();
	}

	/**
	 * Prueba la búsqueda de un lugar por su ID.
	 */
	public void testFindById(Long id) {
		System.out.println("--- Test: LugarService.findById(" + id + ") ---");
		LugarDTO lugar = service.findById(id);
		
		if (lugar != null) {
			System.out.println("Lugar encontrado: " + lugar.getNombre());
			System.out.println("  Dirección: " + lugar.getDireccion());
			System.out.println("  Coordenadas: [" + lugar.getLatitud() + ", " + lugar.getLongitud() + "]");
			System.out.println("  Localidad ID: " + lugar.getLocalidadId());
		} else {
			System.out.println("No se encontró el lugar con ID: " + id);
		}
	}

	/**
	 * Prueba la creación de un nuevo lugar.
	 */
	public void testCreate() {
		System.out.println("\n--- Test: LugarService.create ---");
		LugarDTO nuevo = new LugarDTO();
		nuevo.setNombre("Mirador del Sol");
		nuevo.setDireccion("Calle Alta, s/n");
		nuevo.setLatitud(38.123456);
		nuevo.setLongitud(-1.654321);
		nuevo.setLocalidadId(1L); // ⚠ Asegúrate que este ID de localidad exista en tu DB

		LugarDTO creado = service.create(nuevo);
		
		if (creado != null && creado.getId() != null) {
			System.out.println("Lugar creado exitosamente con ID: " + creado.getId());
		} else {
			System.out.println("Error al crear el lugar.");
		}
	}

	/**
	 * Prueba la actualización de un lugar existente.
	 */
	public void testUpdate(Long id) {
		System.out.println("\n--- Test: LugarService.update ---");
		LugarDTO lugar = service.findById(id);
		
		if (lugar != null) {
			System.out.println("Nombre original: " + lugar.getNombre());
			
			// Modificamos datos
			lugar.setNombre(lugar.getNombre() + " (Actualizado)");
			lugar.setDireccion("Dirección Nueva 123");
			
			service.update(lugar);
			
			// Verificamos el cambio volviendo a consultar
			LugarDTO actualizado = service.findById(id);
			System.out.println("Nuevo nombre: " + actualizado.getNombre());
			System.out.println("Nueva dirección: " + actualizado.getDireccion());
		} else {
			System.out.println("No se encontró el lugar con ID " + id + " para actualizar.");
		}
	}

	/**
	 * Prueba la eliminación de un lugar.
	 */
	public void testDelete(Long id) {
		System.out.println("\n--- Test: LugarService.delete(" + id + ") ---");
		service.delete(id);
		
		LugarDTO eliminado = service.findById(id);
		if (eliminado == null) {
			System.out.println("Lugar con ID " + id + " eliminado correctamente de la base de datos.");
		} else {
			System.out.println("Error: El lugar con ID " + id + " todavía existe.");
		}
	}

	public static void main(String[] args) {
		LugarServiceTest test = new LugarServiceTest();

		// 1. Probar búsqueda de un lugar existente
		test.testFindById(1L);

		// 2. Probar creación (descomenta para ejecutar)
		// test.testCreate();

		// 3. Probar actualización
		// test.testUpdate(1L);

		// 4. Probar eliminación
		// test.testDelete(5L);
	}
}