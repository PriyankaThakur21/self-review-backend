package com.ems.self_review;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
public class SelfReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(SelfReviewApplication.class, args);
	}

}
