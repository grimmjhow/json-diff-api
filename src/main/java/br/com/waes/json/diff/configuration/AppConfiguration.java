package br.com.waes.json.diff.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Configuration class responsible to
 * create and inject beans!
 */
@Configuration
@EnableJpaRepositories(basePackages = {"br.com.waes.json.diff"}) //Indicates where to find and active @Repository and @Entity classes
public class AppConfiguration {

}
