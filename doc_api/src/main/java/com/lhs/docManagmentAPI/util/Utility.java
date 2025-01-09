/**
 * 
 */
package com.lhs.docManagmentAPI.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.stereotype.Component;

/**
 * @author lavanya.badham
 *
 */

@Component
public class Utility {

	public boolean isNull(String comparion_value) {
		boolean null_value = true;
		try {
			if (comparion_value != null && !"".equals(comparion_value) && !"null".equalsIgnoreCase(comparion_value) && !"undefined".equalsIgnoreCase(comparion_value)) {
				null_value = false;
			}
		} catch (NullPointerException npe) {
			null_value = true;
			System.err.println("Exception in null comparion : null pointer exception");
		} catch (Exception ex) {
			null_value = true;
			System.err.println("Exception in null comparion : Exception");
		}
		return null_value;
	}// end isnull method
	
	
	
	public byte[] listBytesToZip1(Map<String, byte[]> mapReporte) throws IOException {
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
//	    FileOutputStream fos = new FileOutputStream("C://ayushi.zip");
	    ZipOutputStream zos = new ZipOutputStream(baos);
	    for (Entry<String, byte[]> reporte : mapReporte.entrySet()) {	    
	        ZipEntry entry = new ZipEntry(reporte.getKey());	        
	        entry.setSize(reporte.getValue().length);
	        zos.putNextEntry(entry);
	        zos.write(reporte.getValue());
	    }
	    zos.closeEntry();
	    zos.close();

	    
	    return baos.toByteArray();
	}
	
	public byte[] listBytesToZip(Map<String, byte[]> mapReporte) throws IOException {
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
//	    FileOutputStream fos = new FileOutputStream("C://ayushi.zip");
	    ZipOutputStream zos = new ZipOutputStream(baos);
	    for (Entry<String, byte[]> reporte : mapReporte.entrySet()) {	    
	        ZipEntry entry = new ZipEntry(reporte.getKey());	        
	        entry.setSize(reporte.getValue().length);
	        zos.putNextEntry(entry);
	        zos.write(reporte.getValue());
	    }
	    zos.closeEntry();
	    zos.close();

	    
	    return baos.toByteArray();
	}

	
}
