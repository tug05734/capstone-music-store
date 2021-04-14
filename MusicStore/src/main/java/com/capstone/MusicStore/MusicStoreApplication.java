package com.capstone.MusicStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.capstone.MusicStore"})
public class MusicStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicStoreApplication.class, args);
		//test
	}

}
