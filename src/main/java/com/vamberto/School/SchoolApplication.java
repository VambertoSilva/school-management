package com.vamberto.School;

import com.vamberto.School.config.DotenvConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchoolApplication {


	public static void main(String[] args) {
		DotenvConfig.loadEnv();
		SpringApplication.run(SchoolApplication.class, args);
	}

}
