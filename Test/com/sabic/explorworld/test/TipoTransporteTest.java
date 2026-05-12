package com.sabic.explorworld.test;

import com.sabic.explorworld.dao.TipoTransporteDAO;
import com.sabic.explorworld.model.TipoTransporte;

public class TipoTransporteTest {

	public static final void testFindById() {
		try {
			TipoTransporteDAO dao = new TipoTransporteDAO();
			TipoTransporte tt = dao.findById(6l);
			System.out.println("Resultado: " + tt.getId() + " " + tt.getNombre());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final void testCreate() {
		try {
			TipoTransporteDAO dao = new TipoTransporteDAO();

			TipoTransporte tt = new TipoTransporte();
			tt.setNombre("Cohete");

			dao.create(tt);
			System.out.println("TipoTransporte created: " + tt.getId() + " " + tt.getNombre());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final void testUpdate() {
		try {
			TipoTransporteDAO dao = new TipoTransporteDAO();

			TipoTransporte tt = dao.findById(6l);
				tt.setNombre("Cohete dinamico");
				dao.update(tt);
				System.out.println("TipoTransporte updated: " + tt.getId() + " " + tt.getNombre());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final void testDelete() {
		try {
			TipoTransporteDAO dao = new TipoTransporteDAO();
			TipoTransporte tt = dao.findById(6l);

			dao.delete(6l);
			System.out.println("TipoTransporte con id " + tt.getId() + " " + tt.getNombre() + " eliminado.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TipoTransporte tt = new TipoTransporte();
		System.out.println(tt.toString());
		// testFindById();
		// testCreate();
		// testUpdate();
		// testDelete();
	}
}
