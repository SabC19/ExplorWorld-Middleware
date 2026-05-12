package com.sabic.explorworld.test;

import java.util.List;

import com.sabic.explorworld.dao.GuiaDAO;
import com.sabic.explorworld.dao.criteria.GuiaCriteria;
import com.sabic.explorworld.model.GuiaDTO;

public class GuiaDAOTest {


	public static final void testFindById() {
		try {
			GuiaDAO dao = new GuiaDAO();
			GuiaDTO g = dao.findById(1l);
			System.out.println(g.getNombre() + " " + g.getPrimerApellido() + " " 
					+ g.getNie() + " " + g.getEmail()+ " " + g.getTelefono() + " " + g.getFechaNacimiento() 
					+ " " + g.getPassword() + " " + g.getGeneroId()); 
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public static final void testFindBy() {
		try {
			GuiaDAO dao = new GuiaDAO();

			GuiaCriteria criteria = new GuiaCriteria();

			List<GuiaDTO> resultados = dao.findBy(criteria);

			for (GuiaDTO g: resultados) {
				System.out.println(g.getId()+": "
						+g.getNombre()+": "
						+g.getPrimerApellido()
						+g.getNie());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}			

	}

	public static final void testFindByEmail() {
		try {
			GuiaDAO dao = new GuiaDAO();
			GuiaDTO g = dao.findByEmail("abc@email.com");

			System.out.println(g.getNombre() + " " + g.getPrimerApellido() 
			+ " " + g.getNie() + " " + g.getEmail()+ " " + g.getTelefono() 
			+ " " + g.getFechaNacimiento() + " " + g.getPassword() + " " + g.getGeneroId()); 
		}catch (Exception e) { 
			e.printStackTrace();
		}
	}

	public static final void testFindByActividad() {
		try {
			GuiaDAO dao = new GuiaDAO(); 
			List<GuiaDTO> guias = dao.findByActividad(10l); 
			for (GuiaDTO g: guias) { System.out.println(g.getId()
					+": " +g.getNombre()+": " +g.getPrimerApellido() +g.getNie()); 
			} 
		} catch (Exception e) { 
			e.printStackTrace();

		}

	}

	public static final void testCreate() {
		try {
			GuiaDAO dao = new GuiaDAO();

			GuiaDTO g = new GuiaDTO();


			g.setNombre("Manolo");
			g.setPrimerApellido("Lopez");
			g.setNie("12345677L");
			g.setEmail("MANOLO@email.com");
			g.setTelefono("+34600123456");
			g.setFechaNacimiento(new java.util.Date());
			g.setPassword("password123");
			g.setGeneroId(2l);
			dao.create(g);
			System.out.println("Guia creado con id: "+g.getId());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final void testUpdate() {
		try {
			GuiaDAO dao = new GuiaDAO();

			GuiaDTO g = dao.findById(40l);
			if (g != null) {
				g.setNombre("Manuel");
				g.setGeneroId(22l);
				dao.update(g);
				System.out.println(g);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static final void testDelete() {
		try {
			GuiaDAO dao = new GuiaDAO();
			dao.delete(40l);
			System.out.println("Guia eliminado");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		GuiaDTO guia = new GuiaDTO();
		System.out.println(guia.toString());
		// testFindById();
		// testFindBy();
		// testFindByEmail();
		// testFindByActividad();
		// testCreate();
		// testUpdate();
		// testDelete();
	}



}
