package com.sabic.explorworld.test;

import java.util.List;

import com.sabic.explorworld.dao.GeneroDAO;
import com.sabic.explorworld.model.Genero;

public class GeneroDAOTest {

    public static final void testFindById() {
        try {
            GeneroDAO dao = new GeneroDAO();
            Genero g = dao.findById(1l);

            if (g != null) {
                System.out.println(g);
            } else {
                System.out.println("No se encontró el género");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final void testFindAll() {
        try {
            GeneroDAO dao = new GeneroDAO();
            List<Genero> generos = dao.findAll();

            for (Genero g : generos) {
                System.out.println(g);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    	Genero genero = new Genero();
		System.out.println(genero.toString());
        // testFindById();
        // testFindAll();
    }
}
