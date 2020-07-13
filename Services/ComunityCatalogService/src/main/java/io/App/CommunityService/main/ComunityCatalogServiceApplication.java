package io.App.CommunityService.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"io.App.CommunityService"})
public class ComunityCatalogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComunityCatalogServiceApplication.class, args);
	}

}
