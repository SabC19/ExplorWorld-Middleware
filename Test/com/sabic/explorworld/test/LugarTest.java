package com.sabic.explorworld.test;

import com.sabic.explorworld.dao.LugarDAO;
import com.sabic.explorworld.model.LugarDTO;

public class LugarTest {

	public static final void testFindById() {
		try {
			LugarDAO dao = new LugarDAO();
			LugarDTO l = dao.findById(38l);

			System.out.println(l.getId() + " "
					+ l.getNombre() + " "
					+ l.getDireccion() + " "
					+ l.getLatitud() + " "
					+ l.getLongitud() + " "
					+ l.getLocalidadId());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final void testCreate() {
		try {
			LugarDAO dao = new LugarDAO();

			LugarDTO l = new LugarDTO();
			l.setNombre("Parque Central");
			l.setDireccion("Calle Mayor 10");
			l.setLatitud(40.4168);
			l.setLongitud(-3.7038);
			l.setLocalidadId(1l);

			dao.create(l);
			System.out.println("Lugar creado con id: " + l.getId());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final void testUpdate() {
		try {
			LugarDAO dao = new LugarDAO();

			LugarDTO l = dao.findById(38l);
			if (l != null) {
				l.setNombre("Parque Espacial");
				dao.update(l);
				System.out.println("Lugar actualizado: " + l.getId() + " " + l.getNombre());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final void testDelete() {
		try {
			LugarDAO dao = new LugarDAO();
			dao.delete(38l);
			System.out.println("Lugar eliminado");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		LugarDTO lugar = new LugarDTO();
		System.out.println(lugar.toString());
		// testFindById();
		// testCreate();
		// testUpdate();
		// testDelete();
	}
}
