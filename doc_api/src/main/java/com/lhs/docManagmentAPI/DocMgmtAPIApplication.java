package com.lhs.docManagmentAPI;

import org.jodconverter.boot.autoconfigure.JodConverterLocalAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(
		exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,JodConverterLocalAutoConfiguration.class},
		scanBasePackages = { "com.lhs.docManagmentAPI.*"}
)
//@SpringBootApplication
public class DocMgmtAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocMgmtAPIApplication.class, args);
	}

}
