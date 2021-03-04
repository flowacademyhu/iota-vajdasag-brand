package hu.flowacademy.vajdasagbrand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;

@SpringBootApplication(exclude = JpaRepositoriesAutoConfiguration.class)
public class VajdasagBrandApplication {

	public static void main(String[] args) {
		SpringApplication.run(VajdasagBrandApplication.class, args);
	}

}
