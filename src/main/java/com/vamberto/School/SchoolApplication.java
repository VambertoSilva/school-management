package com.vamberto.School;

import com.vamberto.School.configs.DotenvConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SchoolApplication {


	public static void main(String[] args) {
		DotenvConfig.loadEnv();
		SpringApplication.run(SchoolApplication.class, args);
	}

}
