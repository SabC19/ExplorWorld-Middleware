package com.sabic.explorworld.test;

import com.sabic.explorworld.dao.TipoActividadDAO;
import com.sabic.explorworld.model.TipoActividadDTO;

public class TipoActividadTest {

	public static final void testFindById() {
		try {
			TipoActividadDAO dao = new TipoActividadDAO();
			TipoActividadDTO ta = dao.findById(1l);
			System.out.println(ta.getId() + " " + ta.getNombre());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static final void testCreate() {
		try {
			TipoActividadDAO dao = new TipoActividadDAO();

			TipoActividadDTO ta = new TipoActividadDTO();

			ta.setNombre("Senderismo");
			ta.setTipoEquipamentoId(1l);

			dao.create(ta);
			System.out.println("Tipo de actividad creado correctamente: " + ta.getId() + ta.getNombre());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static final void testUpdate() {
		try {
			TipoActividadDAO dao = new TipoActividadDAO();

			TipoActividadDTO ta = dao.findById(38l);
			ta.setNombre("Senderismo espacial");

			dao.update(ta);
			System.out.println("Tipo de actividad actualizado correctamente: " + ta.getNombre());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static final void testDelete() {
		try {
			TipoActividadDAO dao = new TipoActividadDAO();
			TipoActividadDTO ta = dao.findById(39l);
			
			dao.delete(39l);
			System.out.println("Tipo de actividad eliminado correctamente"+ ta.getNombre());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		TipoActividadDTO ta = new TipoActividadDTO();
		System.out.println(ta.toString());
		// testFindById();
		// testCreate();
		// testUpdate();
		// testDelete();
	}

}
