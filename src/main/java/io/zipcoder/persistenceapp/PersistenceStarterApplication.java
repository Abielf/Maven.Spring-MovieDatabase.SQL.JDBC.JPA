package io.zipcoder.persistenceapp;

import io.zipcoder.persistenceapp.DAO.DAO;
import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class PersistenceStarterApplication {

	public static void main(String[] args) {

		SpringApplication.run(PersistenceStarterApplication.class, args);

	}

	@Bean
	ServletRegistrationBean h2servletRegistration(){
		ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
		registrationBean.addUrlMappings("/console/*");
		return registrationBean;
	}
}
