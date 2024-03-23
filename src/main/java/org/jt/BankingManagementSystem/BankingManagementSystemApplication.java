package org.jt.BankingManagementSystem;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.File;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditingImpl")
@EnableAsync
public class BankingManagementSystemApplication implements CommandLineRunner {
	@Value("${upload.file.name}")
	private String uploadFileLocation;

	public static void main(String[] args) {
		SpringApplication.run(BankingManagementSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var file = new File(uploadFileLocation);
		if (!file.exists()){
			file.mkdir();
		}
	}
}
