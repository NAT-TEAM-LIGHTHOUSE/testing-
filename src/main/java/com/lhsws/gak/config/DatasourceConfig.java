/**
 * 
 */
package com.lhsws.gak.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;






/**
 * @author akash.meshram
 *
 */
@Configuration
@ComponentScan("com.lhsws.gak")
public class DatasourceConfig {

	@Value("${db.se}")
	private String password;

	@Value("${db.un}")
	private String user;

	@Value("${db.url}")
	private String url;

	@Value("${db.driver}")
	private String driver;
	
	

   

	@Bean
	DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl(url);
		driverManagerDataSource.setUsername(user);
		driverManagerDataSource.setPassword(password);
		driverManagerDataSource.setDriverClassName(driver);
		return driverManagerDataSource;
	}

	
	

}
