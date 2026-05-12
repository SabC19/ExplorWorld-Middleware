package com.sabic.explorworld.test.service;

import java.util.Date;
import com.sabic.explorworld.model.TicketDTO;
import com.sabic.explorworld.service.TicketService;
import com.sabic.explorworld.service.impl.TicketServiceImpl;

public class TicketServiceTest {

	private TicketService service = null;

	public TicketServiceTest() {
		this.service = new TicketServiceImpl();
	}

	/**
	 * Prueba la búsqueda de un ticket por su ID.
	 */
	public void testFindById(Long id) {
		System.out.println("--- Test: TicketService.findById(" + id + ") ---");
		TicketDTO ticket = service.findById(id);
		
		if (ticket != null) {
			System.out.println("Ticket encontrado:");
			System.out.println("  Número: " + ticket.getNumeroTicket());
			System.out.println("  Precio: " + ticket.getPrecio() + "€");
			System.out.println("  Fecha Registro: " + ticket.getRegistroDia());
			System.out.println("  Actividad ID: " + ticket.getActividadId());
			System.out.println("  Participante ID: " + ticket.getParticipanteId());
		} else {
			System.out.println("No se encontró el ticket con ID: " + id);
		}
	}

	/**
	 * Prueba la creación de un nuevo ticket de inscripción.
	 */
	public void testCreate() {
		System.out.println("\n--- Test: TicketService.create ---");
		TicketDTO nuevo = new TicketDTO();
		nuevo.setRegistroDia(new Date());
		nuevo.setNumeroTicket("TK-2026-XYZ");
		nuevo.setPrecio(45.50);
		nuevo.setActividadId(1L);    // ⚠ Debe existir en la tabla activity
		nuevo.setParticipanteId(1L); // ⚠ Debe existir en la tabla participant

		TicketDTO creado = service.create(nuevo);
		
		if (creado != null && creado.getId() != null) {
			System.out.println("Ticket generado con éxito. ID: " + creado.getId());
		} else {
			System.out.println("Error al generar el ticket.");
		}
	}

	/**
	 * Prueba la actualización de los datos de un ticket.
	 */
	public void testUpdate(Long id) {
		System.out.println("\n--- Test: TicketService.update ---");
		TicketDTO ticket = service.findById(id);
		
		if (ticket != null) {
			System.out.println("Precio anterior: " + ticket.getPrecio());
			
			// Aplicamos un "descuento" o cambio de precio
			ticket.setPrecio(ticket.getPrecio() - 5.0);
			ticket.setNumeroTicket(ticket.getNumeroTicket() + "-REV");
			
			service.update(ticket);
			
			TicketDTO actualizado = service.findById(id);
			System.out.println("Precio actualizado: " + actualizado.getPrecio());
			System.out.println("Nuevo número de ticket: " + actualizado.getNumeroTicket());
		} else {
			System.out.println("No se encontró el ticket ID " + id + " para actualizar.");
		}
	}

	/**
	 * Prueba la eliminación de un ticket.
	 */
	public void testDelete(Long id) {
		System.out.println("\n--- Test: TicketService.delete(" + id + ") ---");
		service.delete(id);
		
		TicketDTO eliminado = service.findById(id);
		if (eliminado == null) {
			System.out.println("Ticket con ID " + id + " eliminado correctamente.");
		} else {
			System.out.println("Error: El ticket con ID " + id + " no pudo ser eliminado.");
		}
	}

	public static void main(String[] args) {
		TicketServiceTest test = new TicketServiceTest();

		// 1. Probar búsqueda
		test.testFindById(1L);

		// 2. Probar creación (Asegúrate de tener IDs válidos de actividad y participante)
		// test.testCreate();

		// 3. Probar actualización
		// test.testUpdate(1L);

		// 4. Probar eliminación
		// test.testDelete(10L);
	}
}