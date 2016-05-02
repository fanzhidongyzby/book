package com.upc.book;

import org.apache.commons.configuration.BaseConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.DefaultConfigurationBuilder;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@EnableJpaRepositories(basePackages = {"com.upc.book"})
@EnableSpringDataWebSupport
@EnableAutoConfiguration
@EnableScheduling
@ComponentScan
public class ApplicationConfig extends WebMvcConfigurerAdapter {
	private static Logger LOG = LoggerFactory.getLogger(ApplicationConfig.class);

	@Autowired
	org.apache.commons.configuration.Configuration configuration;
	
  @Bean
  public org.apache.commons.configuration.Configuration configuration() {
    try {
      DefaultConfigurationBuilder configurationBuilder = new DefaultConfigurationBuilder(
        "app.config.xml");
      configurationBuilder
        .setReloadingStrategy(new FileChangedReloadingStrategy());
      return configurationBuilder.getConfiguration();
    } catch (ConfigurationException e) {
      e.printStackTrace();
    }
    return new BaseConfiguration();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new UserSecurityInterceptor()).addPathPatterns("/**").excludePathPatterns("/api/login").excludePathPatterns("/api/login/**").excludePathPatterns("/");
  }
}
