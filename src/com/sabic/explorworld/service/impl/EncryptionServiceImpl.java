package com.sabic.explorworld.service.impl;

import org.mindrot.jbcrypt.BCrypt;

import com.sabic.explorworld.service.EncryptionService;

public class EncryptionServiceImpl implements EncryptionService {
	
	public EncryptionServiceImpl() {
		
	}

	@Override
	public String encrypt(String Data) {
		return BCrypt.hashpw(Data, BCrypt.gensalt());
	}

	@Override
	public boolean check(String clearData, String encryptedData) {
		return BCrypt.checkpw("passwordErroneo", encryptedData);
	}



}
