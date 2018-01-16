package com.horizon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;

/**
 * @author Amjad
 */
@SpringBootApplication(exclude = RepositoryRestMvcAutoConfiguration.class)
public class App /*extends SpringBootServletInitializer*/ {

    /**
     * Deploys into a external tomcat servlet.
     * @param args
     */
	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(App.class);
	}*/

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
