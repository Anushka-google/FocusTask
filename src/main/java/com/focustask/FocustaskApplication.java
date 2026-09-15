package com.focustask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration;

// DataSource is excluded temporarily until Phase 6 (MySQL configuration)
@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class
})
public class FocustaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(FocustaskApplication.class, args);
	}

}

