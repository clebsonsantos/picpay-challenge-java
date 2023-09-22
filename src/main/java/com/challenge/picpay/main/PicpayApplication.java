package com.challenge.picpay.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.challenge.picpay.data.db.jpa.entities")
@ComponentScan("com.challenge.picpay.data.db.jpa.repositories")
@ComponentScan("com.challenge.picpay.presenter.rest.controllers")
@EnableJpaRepositories("com.challenge.picpay.data.db.jpa.repositories")
public class PicpayApplication {

	public static void main(String[] args) {
		System.out.println("Application running in port 8081");
		SpringApplication.run(PicpayApplication.class, args);
	}

}
