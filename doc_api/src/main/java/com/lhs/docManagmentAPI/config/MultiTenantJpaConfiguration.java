/**
 * 
 */
package com.lhs.docManagmentAPI.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.hibernate.MultiTenancyStrategy;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.lhs.docManagmentAPI.model.entity.UserMast;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @author lavanya.badham
 *
 */
@Configuration
@EnableConfigurationProperties({ JpaProperties.class })
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories(basePackages = { "com.lhs.docManagmentAPI.model.*","com.lhs.docManagmentAPI.model.dto.*","com.lhs.docManagmentAPI.*" }, transactionManagerRef = "transactionManager")
public class MultiTenantJpaConfiguration {
	
	@Autowired
	private JpaProperties jpaProperties;
	@Autowired
	private DataSourceProperties properties;
	
	@Value("${dataSourceFilePath}")
	private String dataSourcePath;
	
	@Bean
	public MultiTenantConnectionProvider multiTenantConnectionProvider() {
		return new DataSourceBasedMultiTenantConnectionProviderImpl();
	}

	@Bean
	public CurrentTenantIdentifierResolver currentTenantIdentifierResolver() {
		return new CurrentTenantIdentifierResolverImpl();
	}
	
	@Bean(name = "dataSource")
	public Map<String, DataSource> dataSource() throws SQLException {
		System.out.println("dataSource bean Configuration...");
		
		String path =dataSourcePath; //client server
		
	//	String path ="E:\\erp_tenants1\\"; //233 server
		//String path ="E:\\erp_tnt\\";

		File[] files = new File(path).listFiles();
//		File[] files = Paths.get("tenants").toFile().listFiles();
		Map<String, DataSource> datasources = new LinkedHashMap<>();
		System.out.println("files..."+files.length);

		for (File propertyFile : files) {
			System.out.println("files..."+propertyFile.getName());
			Properties tenantProperties = new Properties();
//	            DataSourceBuilder dataSourceBuilder = new DataSourceBuilder(this.getClass().getClassLoader());
			

			try {

				if (propertyFile.isDirectory() && propertyFile.getName().equalsIgnoreCase("ERP")
						|| propertyFile.getName().equalsIgnoreCase("TEST")|| propertyFile.getName().equalsIgnoreCase("GAM")) {
					File[] file = new File(path + File.separator + propertyFile.getName()).listFiles();
					for (File prop : file) {

						if (prop.getName().endsWith("properties") || prop.getName().endsWith("PROPERTIES")) {
							HikariDataSource ds = new HikariDataSource();
							System.out.println("prop.." + prop.getName());
							tenantProperties.load(new FileInputStream(prop));
							String tenantId = tenantProperties.getProperty("name");
							
							ds.setDriverClassName(properties.getDriverClassName());
							ds.setJdbcUrl(tenantProperties.getProperty("datasource.url"));
							
							ds.setUsername(tenantProperties.getProperty("datasource.username"));
							ds.setPassword(tenantProperties.getProperty("datasource.password"));
							ds.setMinimumIdle(1);
							ds.setMaximumPoolSize(3);
							ds.setConnectionTestQuery("select 1 from dual");
							ds.setPoolName("SpringBootHikariCP");
							ds.setIdleTimeout(10000);
							ds.setMaxLifetime(60000);
							ds.setConnectionTimeout(30000);
							ds.addDataSourceProperty("dataSource.cachePrepStmts", "true");
							ds.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
							ds.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "2048");
							ds.addDataSourceProperty("dataSource.useServerPrepStmts", "true");

							System.out.println("tenants...." + tenantId);
							datasources.put(tenantId, ds);

						}
					}
				} else {
					if (propertyFile.getName().endsWith("properties")
							|| propertyFile.getName().endsWith("PROPERTIES")) {
						tenantProperties.load(new FileInputStream(propertyFile));
						String tenantId = tenantProperties.getProperty("name");
						HikariDataSource ds = new HikariDataSource();
						System.out.println("url"+tenantProperties.getProperty("datasource.url"));
						ds.setDriverClassName(properties.getDriverClassName());
						ds.setJdbcUrl(tenantProperties.getProperty("datasource.url"));
						ds.setUsername(tenantProperties.getProperty("datasource.username"));
						ds.setPassword(tenantProperties.getProperty("datasource.password"));
						ds.setMinimumIdle(1);
						ds.setMaximumPoolSize(3);
						ds.setConnectionTestQuery("select 1 from dual");
						ds.setPoolName("SpringBootHikariCP");
						ds.setIdleTimeout(10000);
						ds.setMaxLifetime(60000);
						ds.setConnectionTimeout(30000);
						ds.addDataSourceProperty("dataSource.cachePrepStmts", "true");
						ds.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
						ds.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "2048");
						ds.addDataSourceProperty("dataSource.useServerPrepStmts", "true");

						System.out.println("tenants...." + tenantId);
						datasources.put(tenantId, ds);
					}

				}

			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}

		return datasources;
	}
	
	@PersistenceContext
	@Primary
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
			MultiTenantConnectionProvider multiTenantConnectionProvider,
			CurrentTenantIdentifierResolver currentTenantIdentifierResolver) {

		Map<String, Object> hibernateProps = new LinkedHashMap<>();
		hibernateProps.putAll(this.jpaProperties.getProperties());
		hibernateProps.put(Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
		hibernateProps.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
		hibernateProps.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolver);

		// No dataSource is set to resulting entityManagerFactoryBean
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setPackagesToScan(new String[] { UserMast.class.getPackage().getName() });
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		em.setJpaPropertyMap(hibernateProps);

		return em;
	} 

	@Bean
	public EntityManagerFactory entityManagerFactory(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
		return entityManagerFactoryBean.getObject();
	}

	@Bean(name = "transactionManager")
	public PlatformTransactionManager txManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager jpa = new JpaTransactionManager();
		jpa.setEntityManagerFactory(entityManagerFactory);
		return jpa;
	}

	@Bean(name = "sessionFactory")
	public SessionFactory sessionFactory(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
		EntityManagerFactory emf = entityManagerFactoryBean.getObject();

		return emf.unwrap(SessionFactory.class);
	}

		
}
