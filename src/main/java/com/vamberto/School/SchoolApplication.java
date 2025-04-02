package com.vamberto.School;

import com.vamberto.School.configs.DotenvConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableScheduling;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;


@SpringBootApplication
@EnableScheduling
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
public class SchoolApplication {


	public static void main(String[] args) {
		DotenvConfig.loadEnv();
		SpringApplication.run(SchoolApplication.class, args);
	}

}
