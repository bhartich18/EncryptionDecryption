package com.globallogic.encryption.service;



//encryption decryption using secrect key cipher
public interface EncryptionService {

	public String encrypt(String data);
	public String decrypt(String data);

}
