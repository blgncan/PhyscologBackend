package com.physcolog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PhyscologApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhyscologApplication.class, args);

//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		System.out.println("Şifrelenmiş Şifre: " + encoder.encode("123456"));
	}

}
