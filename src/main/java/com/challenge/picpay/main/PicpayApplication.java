package com.challenge.picpay.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PicpayApplication {

	public static void main(String[] args) {
		System.out.println("Application running in port 8081");
		SpringApplication.run(PicpayApplication.class, args);
	}

}
