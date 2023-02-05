package com.globallogic.encryption.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.encryption.service.EncryptionService;

@RestController
@RequestMapping("/api/*")
public class EncryptionController {

	@Autowired
	private EncryptionService encryptionService;

	@GetMapping("encrypt")
	public String encrypt(@RequestParam String encrypt) {
		System.out.println("EncryptedText" + ":" + encryptionService.encrypt(encrypt));
		String result = encryptionService.encrypt(encrypt);

		String decrypt = encryptionService.decrypt(result);
		System.out.println("DecryptedText" + ":" + decrypt);

		return result;
	}

}
