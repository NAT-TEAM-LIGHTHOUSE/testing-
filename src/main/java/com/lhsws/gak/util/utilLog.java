/**
 * 
 */
package com.lhsws.gak.util;

import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


/**
 * @author akash.meshram
 *
 */
@Component
public class utilLog {
	 
	 private static String set_log;
	 
	 public utilLog() {
		 
	 }
	 
	
	public static boolean isnull(String comparion_value) {
			boolean null_value = true;
			try {
				if (comparion_value != null && !"".equals(comparion_value) && !"null".equalsIgnoreCase(comparion_value) && comparion_value.length() > 0) {
					null_value = false;
				}
			} catch (NullPointerException npe) {
				null_value = true;
			} catch (Exception ex) {
				null_value = true;
			}
			return null_value;
		}//End Method
	
	    public static String getSetValue(String value) {
	    	String setValue ="";
	    	try {
	    		Resource resource = new ClassPathResource("application.properties");
		        InputStream input = resource.getInputStream();
		        Properties prop = new Properties();
		        prop.load(input);
		        setValue = prop.getProperty(value);
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    	
	    	return setValue;
	    }//End Method
	    
	    public static void getLog(String heading , String subject) {
	    	
			try {
				String SET_LOG = getSetValue("SET_LOG");
				if (!isnull(SET_LOG) && SET_LOG.equalsIgnoreCase("T")) {
				System.out.print("LHSWS-(L): ");
				System.out.print(heading+"- ");
				System.out.println(!isnull(subject) ? subject.toUpperCase() + "" : "");
				System.out.println("\n");
					
				}
			} catch (Exception e) {
				getPST("Exception",e);
			}
		}//End Method

		public static void getSLog(String heading, String subject, Object o) {
			try {
				String SET_SLOG = getSetValue("SET_SLOG");
				if (!isnull(SET_SLOG) && SET_SLOG.equalsIgnoreCase("T")) {
					System.out.print("LHSWS-(S) : ");
					System.out.print(heading+"- ");
					System.out.print(!isnull(subject) ? subject.toUpperCase() + " : " : "");
					System.out.print(o);
					System.out.print("\n");
				}
			} catch (Exception e) {
				getPST("Exception",e);
			}
		}// End Method

		public static void getQlog(String heading, String subject, Object o) {
			try {
				String SET_QLOG = getSetValue("SET_QLOG");
				if (!isnull(SET_QLOG) && SET_QLOG.equalsIgnoreCase("T")) {
					System.out.print("LHSWS-(Q): ");
					System.out.print(heading+"- ");
					System.out.print(!isnull(subject) ? subject.toUpperCase() + " : " : "");
					System.out.println(o);
					System.out.print("\n");
					
				}
			} catch (Exception e) {
				getPST("Exception",e);
			}
		}// End Method

		public static void getPST(String heading, Exception exObj) {
			try {
				String SET_PST = getSetValue("SET_PST");
				if (!isnull(SET_PST) && SET_PST.equalsIgnoreCase("T")) {
					System.out.print("LHSWS-(P): ");
					System.out.print(heading+"- ");
					exObj.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}// End Method
		public static void getDLog(String heading,String subject, Object o) {
			try {
				String SET_DLOG = getSetValue("SET_DLOG");
				if (!isnull(SET_DLOG) && SET_DLOG.equalsIgnoreCase("T")) {
					System.out.print("LHSWS-(D): ");
					System.out.print(heading+"- ");
					System.out.print(!isnull(subject) ? subject.toUpperCase() + " : " : "");
					System.out.print(o);
					System.out.print("\n");
				}
			} catch (Exception e) {
				getPST("Exception",e);
			}
		}// End Method
}
