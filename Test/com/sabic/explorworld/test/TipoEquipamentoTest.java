package com.sabic.explorworld.test;

import com.sabic.explorworld.dao.TipoEquipamentoDAO;
import com.sabic.explorworld.model.TipoEquipamento;


public class TipoEquipamentoTest {

	public static final void testFindById() {
		try {
			TipoEquipamentoDAO dao = new TipoEquipamentoDAO();
			TipoEquipamento te = dao.findById(1l);	
			System.out.println("Resultado: " + te.getId() + " " + te.getModel());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static final void testCreate() {
		try {
			TipoEquipamentoDAO dao = new TipoEquipamentoDAO();
			
			TipoEquipamento te = new TipoEquipamento();
			
			te.setModel("Model X");
			dao.create(te);
			System.out.println("TipoEquipamento created: " + te.getId() + " " + te.getModel());
		
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public static final void testUpdate() {
		try {
			TipoEquipamentoDAO dao = new TipoEquipamentoDAO();
			
			TipoEquipamento te = dao.findById(7l);
			te.setModel("Turbo Motor");
			dao.update(te);
			System.out.println("TipoEquipamento updated: " + te.getId() + " " + te.getModel());
		
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public static final void testDelete() {
		try {
			TipoEquipamentoDAO dao = new TipoEquipamentoDAO();
			TipoEquipamento te = dao.findById(7l);
			
			dao.delete(7l);
			System.out.println("TipoEquipamento con id" + "eliminado." + te.toString());
		
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public static void main(String[] args) {
		TipoEquipamento te = new TipoEquipamento();
		System.out.println(te.toString());
		// testFindById();
		// testCreate();
		// testUpdate();
		// testDelete();
		

	}

}
