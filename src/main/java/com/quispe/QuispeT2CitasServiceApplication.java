package com.quispe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class QuispeT2CitasServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuispeT2CitasServiceApplication.class, args);
	}

}
