package com.sabic.explorworld.test;

import com.sabic.explorworld.dao.TransporteDAO;
import com.sabic.explorworld.model.Transporte;

public class TransporteTest {

	public static final void testFindById() {
		try {
			TransporteDAO dao = new TransporteDAO();
			Transporte t = dao.findById(7l);
			System.out.println(t.getNumeroPlaca() + ": " 
			+ t.getNumeroPuestos() + ": " + t.getTipoTransporteId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final void testCreate() {
		try {
			TransporteDAO dao = new TransporteDAO();

			Transporte t = new Transporte();
			t.setNumeroPlaca("ABC-123");
			t.setNumeroPuestos("4");
			t.setTipoTransporteId(1l);

			dao.create(t);
			System.out.println("Transporte created: " 
			+ t.getId() + " " + t.getNumeroPlaca() + " "
					+ t.getNumeroPuestos() + " " + t.getTipoTransporteId());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final void testUpdate() {
		try {
			TransporteDAO dao = new TransporteDAO();
			
			Transporte t = dao.findById(7l);
				t.setNumeroPuestos("6");
				dao.update(t);
				System.out.println("Transporte updated: " 
				+ t.getId() + " " + t.getNumeroPuestos() 
				+ " " + t.getNumeroPlaca() + " " + t.getTipoTransporteId());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final void testDelete() {
		try {
			TransporteDAO dao = new TransporteDAO();
			dao.delete(12l);
			System.out.println("Transporte eliminado.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Transporte tr = new Transporte();
		System.out.println(tr.toString());
		// testFindById();
		// testCreate();
		// testUpdate();
		// testDelete();
	}
}
