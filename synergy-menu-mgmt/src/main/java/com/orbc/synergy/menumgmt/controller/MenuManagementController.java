/**
 * 
 */
package com.orbc.synergy.menumgmt.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.orbc.synergy.menumgmt.dto.Menu;

/**
 * @author ntalari
 *
 */
@RestController
public class MenuManagementController {
	
	private static final Logger log = LogManager.getLogger(MenuManagementController.class);


	@RequestMapping(value = "/addmenu", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String addMenu(@RequestBody Menu menu) {
		
		return "";
		
	}
	
	@RequestMapping(value = "/updatemenu", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateMenu(@RequestBody Menu menu) {
		
		return "";
		
	}
	
	@RequestMapping(value = "/deletemenu", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String deleteMenu(@RequestBody Menu menu) {
		
		return "";
		
	}
	
	@RequestMapping(value = "/addresourcetomenu", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String addResourceToMenu(@RequestBody Menu menu) {
		
		return "";
		
	}
	
	@GetMapping(value = "/getallmenus", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getAllMenus() {
		
		return "";
		
	}

}
