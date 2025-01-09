
package com.lhsws.gak.controller;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author akash.meshram
 *
 */

@RestController
@RequestMapping("/default")
public class DefaultController {

	@Autowired
	BuildProperties buildProperties;

	@GetMapping(value = "/")
	public String defaultService() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a")
				.withZone(ZoneId.systemDefault());
		String lastBuildTime = dateTimeFormatter.format(buildProperties.getTime());

		return "Last Build Time: " + lastBuildTime;
	}// End Method
	
	@GetMapping(value = "/time")
	public String defaults() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a")
				.withZone(ZoneId.systemDefault());
		String lastBuildTime = dateTimeFormatter.format(buildProperties.getTime());

		return "Last Build Time: " + lastBuildTime;
	}// End Method
}// End Class
