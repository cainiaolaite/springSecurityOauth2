package com.hua.primary;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collection;
import java.util.Date;

@SpringBootApplication
public class Primary01Application {

	public static void main(String[] args) {
		//SpringApplication.run(Primary01Application.class, args);
		System.out.println(Object.class.getClassLoader());
		System.out.println(Date.class.getClassLoader());
		System.out.println(Primary01Application.class.getClassLoader().getParent());
		System.out.println(Primary01Application.class.getClassLoader().getParent().getParent());


		Collection
	}


}
