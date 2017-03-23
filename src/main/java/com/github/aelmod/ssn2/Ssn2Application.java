package com.github.aelmod.ssn2;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Ssn2Application {
	private final ObjectMapper objectMapper;

	@Autowired
	public Ssn2Application(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@PostConstruct
	private void init() {
		objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
		objectMapper.registerModule(
				new Hibernate5Module().configure(
						Hibernate5Module.Feature.FORCE_LAZY_LOADING, true));
	}

	public static void main(String[] args) {
		SpringApplication.run(Ssn2Application.class, args);
	}
}
