package com.sabic.explorworld.test.service;

import com.sabic.explorworld.model.Provincia;
import com.sabic.explorworld.service.ProvinciaService;
import com.sabic.explorworld.service.impl.ProvinciaServiceImpl;

public class ProvinciaServiceTest {

	private ProvinciaService service = null;

	public ProvinciaServiceTest() {
		// Inicializamos la implementación del servicio
		this.service = new ProvinciaServiceImpl();
	}

	/**
	 * Prueba la recuperación de una provincia por su ID.
	 */
	public void testFindById(Long id) {
		System.out.println("--- Test: ProvinciaService.findById(" + id + ") ---");
		Provincia prov = service.findById(id);
		
		if (prov != null) {
			System.out.println("Provincia encontrada:");
			System.out.println("  ID: " + prov.getId());
			System.out.println("  Nombre: " + prov.getNombre());
		} else {
			System.out.println("No se encontró la provincia con ID: " + id);
		}
	}

	/**
	 * Prueba la búsqueda de una provincia por su nombre.
	 */
	public void testFindByNombre(String nombre) {
		System.out.println("\n--- Test: ProvinciaService.findByNombre('" + nombre + "') ---");
		Provincia prov = service.findByNombre(nombre);
		
		if (prov != null) {
			System.out.println("Resultado de búsqueda:");
			System.out.println("  ID: " + prov.getId());
			System.out.println("  Nombre oficial: " + prov.getNombre());
		} else {
			System.out.println("No se encontró ninguna provincia que coincida con: " + nombre);
		}
	}

	public static void main(String[] args) {
		ProvinciaServiceTest test = new ProvinciaServiceTest();

		// 1. Probar búsqueda por ID (Ejemplo: 28 para Madrid o el que tengas)
		test.testFindById(1L);

		// 2. Probar búsqueda por nombre (Búsqueda parcial según tu DAO)
		test.testFindByNombre("Murcia");
		test.testFindByNombre("Alicante");
		
		// 3. Probar caso que no existe
		test.testFindByNombre("ProvinciaInexistente");
	}
}