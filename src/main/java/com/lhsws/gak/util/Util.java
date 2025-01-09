package com.lhsws.gak.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
/**
 * @author akash.dev
 *
 */
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.text.DateFormat;

public class Util {

	static String log_generate_flag = "";

	static Connection conn = null;

	public Util(Connection conn) {
		this.conn = conn;
	}

	static void findLogGenerateFlag() {
		Connection con = conn;
		PreparedStatement ps = null;
		ResultSet res = null;

		try {
			if (log_generate_flag == "") {

//            String query="select parameter_value from lhssys_parameters where parameter_name='LOG_GENERATE_FLAG'";
				String query = "select mobile_app_server_log from view_default_user_links";
				ps = con.prepareStatement(query);
				res = ps.executeQuery();
				if (res != null && res.next()) {
//                    if (res.getString(1).equalsIgnoreCase("Y")) {

					log_generate_flag = res.getString(1);
//                    }
				} else {
					log_generate_flag = "F";
				}
			}
		} catch (Exception ex) {

		}
	}

	static public void log(Object s) {

		findLogGenerateFlag();
//		if (log_generate_flag.equalsIgnoreCase("T")) {
			System.out.println(s);
//		}
	}

	static public void errorLog(Object s) {
//        findLogGenerateFlag();
//        if(log_generate_flag=="Y"){
		System.out.println((char) 27 + "[31m" + "ERROR message=>>" + s);
//        }
	}

	static public String matchString(String string, String regex, int group) {
		String value = "";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(string);

		List<String> listMatches = new ArrayList<String>();

		while (matcher.find()) {
			listMatches.add(matcher.group(group));
		}

		for (String s : listMatches) {
			value = value + s + " ";
			System.out.println(s);
		}
		return value;
	}

	public static String match(String html, String expression, int groupNum) {

		Matcher m = Pattern.compile(expression, Pattern.CASE_INSENSITIVE).matcher(html);

		if (groupNum <= 0) {
			if (m.find()) {
				return m.group();
			} else {
				return null;
			}
		}

		if (m.find()) {
			return m.group(groupNum).trim();
		} else {
			return null;
		}
	}

	public static int getDocumentListCount(String tableName, Connection connection) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		int listCount = 0;

		try {

			String selectQry = "select max(seq_id) from " + tableName;
			Util.log("sq id query==" + selectQry);
			pst = connection.prepareStatement(selectQry);

			rs = pst.executeQuery();
			while (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException ex) {
			System.out.println("Exception-" + ex);
		} finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
				}
			}
		}
		Util.log("listCount===" + listCount);
		return listCount;
	}

	static public int nextSeqID(String tableName, Connection connection) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		int listCount = 0;
		try {
			String selectQry = "select portal_app_tran_seq.nextval from dual";
			Util.log("Get SEQ_ID query :  " + selectQry);
			pst = connection.prepareStatement(selectQry);
			rs = pst.executeQuery();
			while (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException ex) {
			System.out.println("Exception-" + ex);
		} finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
				}
			}
		}
		Util.log("OBTAINED SEQ_ID :  " + listCount);
		return listCount;
	}
	
	 public static String formatedDate(String DOB) throws ParseException {
	        Date date = new Date();
	        DOB = DOB.replace("GMT ", "GMT+");
	        java.util.Date tempDate = new java.util.Date(DOB);
	        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	        DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
	        date = (Date) formatter.parse(tempDate.toString());
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
	        return formatedDate;
	    }

	    public static byte[] getImgstreamToBytes(InputStream imgstream) {
	        byte[] bytes = null;
	        try {
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            byte[] buf = new byte[1024];
	            InputStream in = imgstream;
	            if (in != null) {
	                int n = 0;
	                while ((n = in.read(buf)) > 0) {
	                    baos.write(buf, 0, n);
	                }
	                in.close();
	            }
	            bytes = baos.toByteArray();
	        } catch (IOException e) {
	        }
	        return bytes;
	    }
	    
	    public static String getClientAddress(HttpServletRequest request) {
			InetAddress ip = null ;
			 String ipAddress = null ;
			
			  try {
				ip = InetAddress.getLocalHost();
				String ipAddresss = ip.getHostAddress().trim();
				ipAddress = request.getRemoteAddr();
				ipAddress = ipAddress.trim();
				 
			  } catch (UnknownHostException e) {

				e.printStackTrace();

			  }

			return ipAddress;
			}  
	    
	    public static String getClientName(HttpServletRequest request) {
	    	
	    	InetAddress ip = null ;
			 String hostname = null ;
			
			  try {
				ip = InetAddress.getLocalHost();
				String ipAddresss = ip.getHostAddress().trim();
				String ipAddress = request.getRemoteAddr();
				 InetAddress addr = InetAddress.getByName(ipAddress);
				 hostname = addr.getHostName();
				
			  } catch (UnknownHostException e) {

				e.printStackTrace();

			  }

			return hostname;
	    	
	    }

	    public static String getClientBrowser(HttpServletRequest request) {
	        final String browserDetails = request.getHeader("User-Agent");
	        final String user = browserDetails.toLowerCase();

	        String browser = "";

	        //===============Browser===========================
	        if (user.contains("msie")) {
	            String substring = browserDetails.substring(browserDetails.indexOf("MSIE")).split(";")[0];
	            browser = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
	        } else if (user.contains("safari") && user.contains("version")) {
	            browser = (browserDetails.substring(browserDetails.indexOf("Safari")).split(" ")[0]).split(
	                    "/")[0] + "-" + (browserDetails.substring(
	                    browserDetails.indexOf("Version")).split(" ")[0]).split("/")[1];
	        } else if (user.contains("opr") || user.contains("opera")) {
	            if (user.contains("opera"))
	                browser = (browserDetails.substring(browserDetails.indexOf("Opera")).split(" ")[0]).split(
	                        "/")[0] + "-" + (browserDetails.substring(
	                        browserDetails.indexOf("Version")).split(" ")[0]).split("/")[1];
	            else if (user.contains("opr"))
	                browser = ((browserDetails.substring(browserDetails.indexOf("OPR")).split(" ")[0]).replace("/",
	                                                                                                           "-")).replace(
	                        "OPR", "Opera");
	        } else if (user.contains("chrome")) {
	            browser = (browserDetails.substring(browserDetails.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
	        } else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1) || (user.indexOf(
	                "mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) || (user.indexOf(
	                "mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1)){
	  
	            browser = "Netscape-?";

	        } else if (user.contains("firefox")) {
	            browser = (browserDetails.substring(browserDetails.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
	        } else if (user.contains("rv")) {
	            browser = "IE";
	        } else {
	            browser = "UnKnown, More-Info: " + browserDetails;
	        }
	        
	        browser = browser.trim();
	        return browser;
	    }

	    
	    public static String getClientOS(HttpServletRequest request) {
	        final String browserDetails = request.getHeader("User-Agent");

	        //=================OS=======================
	        final String lowerCaseBrowser = browserDetails.toLowerCase();
	        if (lowerCaseBrowser.contains("windows")) {
	            return "Windows";
	        } else if (lowerCaseBrowser.contains("mac")) {
	            return "Mac";
	        } else if (lowerCaseBrowser.contains("x11")) {
	            return "Unix";
	        } else if (lowerCaseBrowser.contains("android")) {
	            return "Android";
	        } else if (lowerCaseBrowser.contains("iphone")) {
	            return "IPhone";
	        } else {
	            return "UnKnown, More-Info: " + browserDetails;
	        }
	    }

}
