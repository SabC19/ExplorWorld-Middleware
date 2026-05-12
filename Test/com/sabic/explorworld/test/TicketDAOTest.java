package com.sabic.explorworld.test;

import com.sabic.explorworld.dao.TicketDAO;
import com.sabic.explorworld.model.TicketDTO;

public class TicketDAOTest {

	public static final void testFindById() {
		try {
			TicketDAO dao = new TicketDAO();
			TicketDTO t = dao.findById(75l);
			System.out.println(t.getNumeroTicket());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final void testCreate() {
		try {
			TicketDAO dao = new TicketDAO();

			TicketDTO t = new TicketDTO();
			
			t.setRegistroDia(new java.util.Date());
			t.setNumeroTicket("TICK12345");
			t.setPrecio(49.99);
			t.setActividadId(1l);
			t.setParticipanteId(1l);
			dao.create(t);
			System.out.println("Ticket created with ID: " 
					+ t.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static final void testUpdate() {
		try {
			TicketDAO dao = new TicketDAO();

			TicketDTO t = dao.findById(75l);
			t.setRegistroDia(new java.util.Date(2026-01-01));
			t.setNumeroTicket("TICK54321");
			t.setPrecio(179.99);
			t.setActividadId(2l);
			t.setParticipanteId(1l);
			dao.update(t);
			System.out.println("Ticket updated with new price: " 
					+ t.getPrecio()+ t.getRegistroDia());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static final void testDelete() {
		try {
			TicketDAO dao = new TicketDAO();
			
			TicketDTO t = dao.findById(75l);
			dao.delete(75l);
			System.out.println("Ticket eliminado con ID:" + t.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		TicketDTO ticket = new TicketDTO();
		System.out.println(ticket.toString());
		// testFindById();
		// testCreate();
		// testUpdate();
		// testDelete();


	}

}
