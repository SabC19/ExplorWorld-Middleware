package com.sabic.explorworld.test.service;

import com.sabic.explorworld.model.Transporte;
import com.sabic.explorworld.service.TransporteService;
import com.sabic.explorworld.service.impl.TransporteServiceImpl;

public class TransporteServiceTest {

	private TransporteService service = null;

	public TransporteServiceTest() {
		this.service = new TransporteServiceImpl();
	}

	/**
	 * Prueba la recuperación de un transporte por su ID.
	 */
	public void testFindById(Long id) {
		System.out.println("--- Test: TransporteService.findById(" + id + ") ---");
		Transporte transporte = service.findById(id);
		
		if (transporte != null) {
			System.out.println("Transporte encontrado:");
			System.out.println("  Placa: " + transporte.getNumeroPlaca());
			System.out.println("  Puestos: " + transporte.getNumeroPuestos());
			System.out.println("  Tipo ID: " + transporte.getTipoTransporteId());
		} else {
			System.out.println("No se encontró el transporte con ID: " + id);
		}
	}

	/**
	 * Prueba la creación de un nuevo transporte.
	 */
	public void testCreate() {
		System.out.println("\n--- Test: TransporteService.create ---");
		Transporte nuevo = new Transporte();
		nuevo.setNumeroPlaca("ABC-123");
		nuevo.setNumeroPuestos("40");
		nuevo.setTipoTransporteId(1L); // ⚠ Asegúrate que este ID exista en tu DB

		Transporte creado = service.create(nuevo);
		
		if (creado != null && creado.getId() != null) {
			System.out.println("Transporte creado exitosamente con ID: " + creado.getId());
		} else {
			System.out.println("Error al crear el transporte.");
		}
	}

	/**
	 * Prueba la actualización de un transporte existente.
	 */
	public void updateTest(Long id) {
		System.out.println("\n--- Test: TransporteService.update ---");
		Transporte transporte = service.findById(id);
		
		if (transporte != null) {
			System.out.println("Antes: Placa " + transporte.getNumeroPlaca());
			
			// Modificamos datos
			transporte.setNumeroPlaca("XYZ-999");
			transporte.setNumeroPuestos("50");
			
			service.update(transporte);
			
			// Volvemos a consultar para verificar
			Transporte actualizado = service.findById(id);
			System.out.println("Después: Placa " + actualizado.getNumeroPlaca() + 
					           " | Puestos: " + actualizado.getNumeroPuestos());
		} else {
			System.out.println("No se encontró el transporte con ID " + id + " para actualizar.");
		}
	}

	/**
	 * Prueba la eliminación de un transporte.
	 */
	public void deleteTest(Long id) {
		System.out.println("\n--- Test: TransporteService.delete(" + id + ") ---");
		service.delete(id);
		
		Transporte eliminado = service.findById(id);
		if (eliminado == null) {
			System.out.println("Transporte con ID " + id + " eliminado correctamente.");
		} else {
			System.out.println("El transporte con ID " + id + " aún existe en la base de datos.");
		}
	}

	public static void main(String[] args) {
		TransporteServiceTest test = new TransporteServiceTest();

		// 1. Probar búsqueda de uno existente
		test.testFindById(6L);

		// 2. Probar creación
		// test.testCreate();

		// 3. Probar actualización (usa un ID que exista en tu tabla)
		// test.updateTest(6L);

		// 4. Probar eliminación
		// test.deleteTest(7L);
	}
}