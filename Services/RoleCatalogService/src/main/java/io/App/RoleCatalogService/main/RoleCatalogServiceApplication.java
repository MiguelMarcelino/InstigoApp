package io.App.RoleCatalogService.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"io.App.RoleService"})
public class RoleCatalogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoleCatalogServiceApplication.class, args);
	}

}
