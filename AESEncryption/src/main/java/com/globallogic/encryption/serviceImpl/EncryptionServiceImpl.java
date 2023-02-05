package com.globallogic.encryption.serviceImpl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;
//
//import com.ex.encrption.model.Token;

import com.globallogic.encryption.service.EncryptionService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class EncryptionServiceImpl implements EncryptionService {

	private static final String AES_KEY = "TOKEN_SECURITY";
	private static final String secret_key="bharti@123";

	@Override
	public String encrypt(String data) {
		AES aes = new AES(AES_KEY);
		return aes.encrypt(data);
	}

	@Override
	public String decrypt(String data) {
		AES aes = new AES(AES_KEY);
		return aes.decrypt(data);
	}

	// lets create class called AES

	private class AES {

		private SecretKeySpec secretKey;
		private byte[] key;

		AES(String secret) {
			MessageDigest sha = null;
			try {
				key = secret.getBytes(StandardCharsets.ISO_8859_1);
				sha = MessageDigest.getInstance("SHA-1");
				key = sha.digest(key);
				key = Arrays.copyOf(key, 16);
				secretKey = new SecretKeySpec(key, "AES");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String encrypt(String strToEncrypt) {
			try {
				Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
				cipher.init(Cipher.ENCRYPT_MODE, secretKey);
				return Base64.getEncoder()
						.encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.ISO_8859_1)));

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;

		}

		String decrypt(String strToDecrypt) {
			try {
				Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
				cipher.init(Cipher.DECRYPT_MODE, secretKey);
				return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}



}
