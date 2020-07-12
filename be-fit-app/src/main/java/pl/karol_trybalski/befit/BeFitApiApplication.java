package pl.karol_trybalski.befit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "pl.karol_trybalski.befit")
public class BeFitApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeFitApiApplication.class, args);
	}

}
