/**
 * 
 */
package com.lhs.docManagmentAPI.config;

/**
 * @author lavanya.badham
 *
 */
public class TenantContext {

	public static ThreadLocal<String> currentTenant = new ThreadLocal<>();

	public static void setCurrentTenant(String tenantId) {
		currentTenant.set(tenantId.toUpperCase());
	}

	public static String getCurrentTenant() {
		
		return currentTenant.get();
	}

	public static void clear() {
    	currentTenant.remove();
    }
}
