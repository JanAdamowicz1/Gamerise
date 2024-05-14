package com.example.gamerise;

import com.example.gamerise.api.model.*;
import com.example.gamerise.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.time.LocalDate;

@SpringBootApplication
public class GameriseApplication {
	public static void main(String[] args) {
		SpringApplication.run(GameriseApplication.class, args);
	}
}
