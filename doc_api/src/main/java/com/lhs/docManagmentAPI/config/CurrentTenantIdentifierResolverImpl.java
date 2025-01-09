/**
 * 
 */
package com.lhs.docManagmentAPI.config;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

/**
 * @author lavanya.badham
 *
 */
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver{

	static String DEFAULT_TENANT = "DEFAULT";
	
	@Override
	public String resolveCurrentTenantIdentifier() {
		//System.out.println("resolveCurrent ..............");
		String currentTenant = TenantContext.getCurrentTenant();
		return currentTenant != null ? currentTenant : DEFAULT_TENANT;
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}
}
