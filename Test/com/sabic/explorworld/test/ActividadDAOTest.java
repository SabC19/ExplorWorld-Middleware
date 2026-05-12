package com.sabic.explorworld.test;

import java.util.List;

import com.sabic.explorworld.dao.ActividadDAO;
import com.sabic.explorworld.dao.criteria.ActividadCriteria;
import com.sabic.explorworld.model.ActividadDTO;
import com.sabic.explorworld.model.GuiaDTO;

public class ActividadDAOTest {

	public static final void testFindById() {
		try {
			ActividadDAO dao = new ActividadDAO();
			ActividadDTO a = dao.findById(39l);
			GuiaDTO g = null;
			System.out.println(a.getId() + ": "
					+ a.getNombre() + ": "
					+ a.getDescripcion() + ": "
					+ a.getFechaInicio() + ": "
					+ a.getFechaFin() + ": "
					+ a.getActividadTipoId()
			);
			System.out.println(g);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final void testFindByCriteria() {
		try {
			ActividadDAO dao = new ActividadDAO();

			ActividadCriteria criteria = new ActividadCriteria();

			List<ActividadDTO> resultados = dao.findByCriteria(criteria);

			for (ActividadDTO a : resultados) {
				System.out.println(a.getId()
						+ a.getNombre() + ":"
						+ a.getDescripcion()
						+ a.getFechaInicio() + ": "
						+ a.getFechaFin() + ": "
						+ a.getActividadTipoId()
				);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final void testCreate() {
		try {
			ActividadDAO dao = new ActividadDAO();

			ActividadDTO a = new ActividadDTO();
			
			a.setNombre("Ruta por la montaña");
			a.setDescripcion("Actividad de senderismo guiada");
			a.setFechaInicio(new java.util.Date());
			a.setFechaFin(new java.util.Date());
			a.setCapacidad(20);
			a.setPrecioMin(10.0);
			a.setPrecioMax(25.0);
			a.setActividadTipoId(1l);
			a.setInicioLugarId(1l);
			a.setFinLugarId(2l);

			dao.create(a);
			System.out.println("Actividad creada con id: " + a.getId());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final void testUpdate() {
		try {
			ActividadDAO dao = new ActividadDAO();

			ActividadDTO a = dao.findById(39l);

				if (a != null) {
				    a.setNombre("Descenso por montaña");
				    a.setDescripcion("Actividad de descenso guiada");
				    System.out.println("Actividad actualizada: " + a.getId() + ": " + a.getNombre() + ": " + a.getDescripcion());
				    dao.update(a);
				} else {
				    System.out.println("No se encontró la actividad con ID 39");
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final void testDelete() {
		try {
			ActividadDAO dao = new ActividadDAO();
			dao.delete(39l);
			System.out.println("Actividad eliminada");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	public static void main(String[] args) {
		ActividadDTO actividad = new ActividadDTO();
		System.out.println(actividad.toString());
		// testFindById();
		testFindByCriteria();
		// testCreate();
		// testUpdate();
		// testDelete();
	}

}
