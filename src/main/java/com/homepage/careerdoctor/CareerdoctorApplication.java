package com.homepage.careerdoctor;

import com.homepage.careerdoctor.util.entity.BaseEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CareerdoctorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CareerdoctorApplication.class, args);
	}

}
