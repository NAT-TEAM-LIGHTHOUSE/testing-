/**
 * 
 */
package com.lhs.docManagmentAPI.controller;

import java.net.InetAddress;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.docManagmentAPI.config.JwtTokenUtil;
import com.lhs.docManagmentAPI.config.TenantContext;
import com.lhs.docManagmentAPI.dao.UserMastRepositorySupport;
import com.lhs.docManagmentAPI.dao.UserOptionTranRepository;
import com.lhs.docManagmentAPI.model.ApiResponse;
import com.lhs.docManagmentAPI.model.AuthToken;
import com.lhs.docManagmentAPI.model.dto.LoginUser;
import com.lhs.docManagmentAPI.model.entity.UserMast;
import com.lhs.docManagmentAPI.model.entity.UserOptionTran;
import com.lhs.docManagmentAPI.service.UserService;
import com.lhs.docManagmentAPI.util.Utility;

/**
 * @author lavanya.badham
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	Utility utl;
	
	@Autowired
	UserMastRepositorySupport rep;
	
	@Autowired
	UserOptionTranRepository userOptionTranRepo;
	
	
	@RequestMapping(value = "/generate-token", method = {RequestMethod.POST,RequestMethod.GET})
	public ApiResponse<AuthToken> register(@RequestBody LoginUser loginUser)  {
		ApiResponse<AuthToken> returnObj = new ApiResponse<>(200, "success", null);
		try {
		System.out.println("Inside authenication...222222222222."+loginUser.getTenantId());
	
			TenantContext.setCurrentTenant(loginUser.getTenantId());
			String userCode =loginUser.getUsername();
			String entityCode="";
			String divCode="";
			String accYear="";
			String entityName="";
			String divName="";
//			if(!utl.isNull(loginUser.getSessionId())) {
//				String user = rep.getLoginDetails(loginUser.getSessionId());
//				System.out.println("sessionID///."+loginUser.getSessionId());
//				System.out.println("user.."+user);
//				if(!utl.isNull(user)) {
//					userCode=user.split("#")[0];
//					entityCode = user.split("#")[1];
//					divCode = user.split("#")[2];
//					accYear = user.split("#")[3];
//					entityName = user.split("#")[5];
//					divName = user.split("#")[4];
//					System.out.println("user........"+user);
//				
//				}
//			}
			
			final UserMast user = userService.findById(userCode);
			try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userCode, user.getPassword()));
			}catch(Exception e) {
			
			}
			
		

		final String token = jwtTokenUtil.generateToken(user);
		System.out.println("token..." + token);
		System.out.println("user..." + user);
		returnObj = new ApiResponse<>(200, "success", new AuthToken(token, user.getUser_code(),loginUser.getTenantId(),user.getUser_name(),entityCode,divCode,accYear,entityName,divName,null));
		}catch(Exception e) {
		System.out.println("catcg...");
			e.printStackTrace();
		}
	
		return returnObj;
	}

	
	@GetMapping("/getValidateUser")
	public 	boolean getValidateUser(@RequestParam String sessionId){
	
		return userService.validateUser(sessionId);
	}
	
	@RequestMapping(value = "/saveSessionId", method = {RequestMethod.POST,RequestMethod.GET})
	public ApiResponse<AuthToken> saveSessionId(@RequestBody LoginUser loginUser,HttpServletRequest request)  {
		ApiResponse<AuthToken> returnObj = new ApiResponse<>(200, "success", null);
		Long sessionId=null;
		try {
		System.out.println("Inside authenication...222222222222."+loginUser.getTenantId());
	
			TenantContext.setCurrentTenant(loginUser.getTenantId());
			String userCode =loginUser.getUsername();
			System.out.println("userCode..."+userCode);
//			String entityCode="";
//			String divCode="";
//			String accYear="";
//			String entityName="";
//			String divName="";
			
			
			final UserMast user = userService.getUserByPassword(userCode,loginUser.getPassword());
			System.out.println("user ------------"+user);
			try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userCode, user.getPassword()));
			String ipAddress = request.getRemoteAddr();
			InetAddress addr = InetAddress.getByName(ipAddress);
			String host = addr.getHostName();
		//	UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
			UserOptionTran option = new UserOptionTran();
//			option.setAcc_year(loginUser.getAccYear());
//			option.setDiv_code(loginUser.getDivision());
			option.setMenu_option_code("0");
			option.setSession_id(userOptionTranRepo.getMaxSessionId());
//			option.setEntity_code(loginUser.getEntity());
			option.setLogin_time(new Date());
			//option.setParent_session_id(loginUser.getSessionId());
			option.setMenu_seq("0");
			option.setUser_code(loginUser.getUsername());
			option.setMachine_ip(ipAddress);
			option.setMachine_name(host);
			//option.setMachine_user(stringUtility.isNull(loginUser.getLoginUser().getApp_server_user_name()) ? "" : loginUser.getLoginUser().getApp_server_user_name());
			option.setPlant_code("W");
			option.setSession_user(loginUser.getTenantId());
			UserOptionTran userOp = userOptionTranRepo.save(option);
			sessionId=userOp.getSession_id();
			if(userOp!=null) {
				String userStr = rep.getLoginDetails(sessionId+"");
				System.out.println("sessionID///."+loginUser.getSessionId());
				
				if(!utl.isNull(userStr)) {
					userCode=userStr.split("#")[0];
//					entityCode = userStr.split("#")[1];
//					divCode = userStr.split("#")[2];
//					accYear = userStr.split("#")[3];
//					entityName = userStr.split("#")[5];
//					divName = userStr.split("#")[4];
					System.out.println("userStr........"+userStr);
				
				}
				
			}
			
			}catch(Exception e) {
			e.printStackTrace();
			}
			
		

		final String token = jwtTokenUtil.generateToken(user);
		System.out.println("token..." + token);
		System.out.println("user..." + user);
		returnObj = new ApiResponse<AuthToken>(200, "success", new AuthToken(token, user.getUser_code(),loginUser.getTenantId(),user.getUser_name(),"","","","","",sessionId));
		}catch(Exception e) {
		System.out.println("catcg...");
			e.printStackTrace();
		}
	System.out.println("return..."+returnObj);
		return returnObj;
	}
}
