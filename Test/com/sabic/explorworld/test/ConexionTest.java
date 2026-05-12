package com.sabic.explorworld.test;

import java.sql.Connection;

import com.sabic.explorworld.utils.JDBCUtils;

public class ConexionTest {
	
	public static void main(String[] args) {
	Connection prueba = JDBCUtils.getConnection();
		if (prueba == null) {
		    System.out.println("❌ PELIGRO: La conexión es NULL. Revisa tu usuario/pass o si MySQL está prendido.");
		    return; 
		} else {
		    System.out.println("✅ Conexión exitosa, seguimos...");
		}

	}
	
}
	
