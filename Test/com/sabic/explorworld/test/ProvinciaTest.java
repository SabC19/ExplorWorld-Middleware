package com.sabic.explorworld.test;

import com.sabic.explorworld.dao.ProvinciaDAO;
import com.sabic.explorworld.model.Provincia;


public class ProvinciaTest {

	public static final void testFindById() {
		try {
			ProvinciaDAO dao = new ProvinciaDAO();
			Provincia p = dao.findById(1l);

			if (p != null) {
				System.out.println(p.getId() + " " + p.getNombre());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final void testFindByNombre() {
		try {
			ProvinciaDAO dao = new ProvinciaDAO();
			Provincia p = dao.findByNombre("");

				System.out.println(p.getId() + " " + p.getNombre());


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Provincia provincia = new Provincia();
		System.out.println(provincia.toString());
		// testFindById();
		testFindByNombre();
	}
}
