package br.com.waes.json.diff.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"br.com.waes.json.diff"})
public class AppConfiguration {

}
