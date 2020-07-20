package io.App.UserManagementService.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"io.App.UserManagementService"})
public class UserCatalogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserCatalogServiceApplication.class, args);
	}

}
