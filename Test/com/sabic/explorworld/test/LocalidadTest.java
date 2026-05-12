package com.sabic.explorworld.test;

import java.util.List;

import com.sabic.explorworld.dao.LocalidadDAO;
import com.sabic.explorworld.model.LocalidadDTO;

public class LocalidadTest {

	public static final void testFindById() {
		try {
			LocalidadDAO dao = new LocalidadDAO();
			LocalidadDTO l = dao.findById(1l);

			System.out.println(l.getId() + " "
					+ l.getNombre() + " "
					+ l.getProvinciaId());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void testByNombre() {
		try {
			LocalidadDAO dao = new LocalidadDAO();

			List<LocalidadDTO> localidades = dao.findByNombre("a");

			for (LocalidadDTO l : localidades) {
				System.out.println(
					l.getId() + " - " + l.getNombre() + " (provincia " + l.getProvinciaId() + ")"
				);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		LocalidadDTO locality = new LocalidadDTO();
		System.out.println(locality.toString());
		// testFindById();
		// testByNombre();
	}
}
