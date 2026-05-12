package com.sabic.explorworld.service;

public interface EncryptionService {
	
	public String encrypt(String plainPassword);

	public boolean check(String clearData, String encryptedDate);
	
	// public String desencrypt(String Data);
}
