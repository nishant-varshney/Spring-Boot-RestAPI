package com.nv;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import th.co.geniustree.springdata.jpa.repository.support.JpaSpecificationExecutorWithProjectionImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = JpaSpecificationExecutorWithProjectionImpl.class)
public class SpringDemoSecurityApplication {

// private static Logger logger = LogManager.getLogger(SpringDemoSecurityApplication.class);
	public static void main(String[] args) {
	/*	logger.info("APplication start");
		logger.debug("debug loogger");*/
		SpringApplication.run(SpringDemoSecurityApplication.class, args);
	}
}
