package telran.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"telran"})
public class AccountingManagementTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountingManagementTestApplication.class, args);
	}

}
