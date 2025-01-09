/**
 * 
 */
package com.lhs.docManagmentAPI.config;

import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lavanya.badham
 *
 */
public class DataSourceBasedMultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl{
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private Map<String,DataSource> dataSource;
	
	@Override
	protected DataSource selectAnyDataSource() {
		System.out.println("1...");
		
		return this.dataSource.values().iterator().next();
	}

	@Override
	protected DataSource selectDataSource(String tenantId) {
//		System.out.println("tenantId..."+tenantId);
//		System.out.println("2..."+this.dataSource.get(tenantId.trim()));
		return this.dataSource.get(tenantId);
	}

}
