package com.hua.primary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class Primary01Application {

	public static void main(String[] args) {
		SpringApplication.run(Primary01Application.class, args);
	}


}
