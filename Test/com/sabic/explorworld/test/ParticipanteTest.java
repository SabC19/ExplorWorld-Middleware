package com.sabic.explorworld.test;

import java.util.List;

import com.sabic.explorworld.dao.ParticipanteDAO;
import com.sabic.explorworld.dao.criteria.ParticipanteCriteria;
import com.sabic.explorworld.model.Participante;

public class ParticipanteTest {

	public static final void testFindById() {
		try {
			ParticipanteDAO dao = new ParticipanteDAO();
			Participante p = dao.findById(75l);
			System.out.println(p.getNombre());
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public static final void testFindBy() {
		try {
			ParticipanteDAO dao = new ParticipanteDAO();

			ParticipanteCriteria criteria = new ParticipanteCriteria();
			criteria.setNombre("m");

			List<Participante> resultados = dao.findByCriteria(criteria);

			for (Participante p: resultados) {
				System.out.println(p.getId()+": "
						+p.getNombre()+": "
						+p.getPrimerApellido()

						);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final void testCreate() {
		try {
			ParticipanteDAO dao = new ParticipanteDAO();

			Participante p = new Participante();

			p.setNombre("Ana");
			p.setPrimerApellido("Garcia");
			p.setEmail("abcc@gmail.com");
			p.setTelefono("+34600987654");
			p.setFechaNacimiento(new java.util.Date());
			p.setPassword("password123");
			p.setGeneroId(24l);
			dao.create(p);
			System.out.println("Participante creado con id: " + p.getId() + " " + p.getNombre() + " " + p.getPrimerApellido());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public static final void testUpdate() {
		try {
			ParticipanteDAO dao = new ParticipanteDAO();

			Participante p = dao.findById(75l);
				p.setNombre("Ana Maria");
				p.setPrimerApellido("Garcia Lopez");
				p.setTelefono("+34600111222");
				p.setEmail("ana.maria@test.com");
				
				dao.update(p);
				System.out.println("Participante actualizado con id: " 
						+ p.getId() + " " + p.getNombre() + " " 
						+ p.getPrimerApellido());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static final void testDelete() {
		try {
			ParticipanteDAO dao = new ParticipanteDAO();
			dao.delete(75l);
			System.out.println("Participante eliminado");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public static final void testFindByEmail() {
		try {
			ParticipanteDAO dao = new ParticipanteDAO();
			String email = "al";

			List<Participante> resultados = dao.findByEmail(email);

			if (resultados == null || resultados.isEmpty()) {
				System.out.println("No se encontró ningún participante con email: " + email);
			} else {
				for (Participante p : resultados) {
					System.out.println(
						p.getId() + ": " +
						p.getNombre() + " " +
						p.getPrimerApellido() + " - " +
						p.getEmail()
					);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public static void main(String[] args) {
		Participante participante = new Participante();
		System.out.println(participante.toString());
		// testFindById();
		testFindBy();
		// testCreate();
		// testUpdate();
		// testDelete();
		// testFindByEmail();
	}

}
