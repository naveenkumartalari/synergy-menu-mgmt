/**
 * 
 */
package com.orbc.syn.menumgmt.controller;

import java.util.Set;

import javax.websocket.server.PathParam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.orbc.syn.menumgmt.dto.MenuDto;
import com.orbc.syn.menumgmt.dto.Response;
import com.orbc.syn.menumgmt.service.MenuManagementService;

/**
 * @author ntalari
 *
 */
@RestController
public class MenuManagementController {

	private static final Logger log = LogManager.getLogger(MenuManagementController.class);

	@Autowired
	private MenuManagementService menuMgmtService;

	public MenuManagementService getMenuMgmtService() {
		return menuMgmtService;
	}

	@RequestMapping(value = "/addmenu", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public MenuDto addMenu(@RequestBody MenuDto menuDto) {

		log.info("addMenu(Menu menu) : starts");

		menuDto = getMenuMgmtService().addMenu(menuDto);

		log.info("addMenu(Menu menu) : ends");
		return menuDto;

	}

	@RequestMapping(value = "/editmenu", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public MenuDto editMenu(@RequestBody MenuDto menuDto) {

		log.info("editMenu(Menu menu) : starts");

		menuDto = getMenuMgmtService().editMenu(menuDto);

		log.info("editMenu(Menu menu) : ends");
		return menuDto;

	}

	@RequestMapping(value = "/deletemenu/{menuId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response deleteMenu(@PathVariable("menuId") String menuId) {

		log.info("deleteMenu(Menu menu) : starts");

		boolean flag = getMenuMgmtService().deleteMenu(menuId);

		Response response = new Response();

		if (flag) {
			response.setResult("success");
			response.setDesc("Menu Id "+menuId+" has been deleted");
		} else {
			response.setResult("failure");
			response.setDesc("Menu Id "+menuId + " not deleted");
		}

		log.info("deleteMenu(Menu menu) : ends");
		return response;

	}

	@RequestMapping(value = "/addresourcetomenu/{menuId}/{resourceId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String addResourceToMenu(@PathParam("menuId") String menuId, @PathParam("resourceId") String resourceId) {

		return "resource ID: " + resourceId + "has been added to Menu ID: " + menuId;

	}

	@GetMapping(value = "/getallmenus", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Set<MenuDto> getAllMenus() {

		log.info("getAllMenus() : starts");

		/*
		 * Menu menu1=new Menu(); menu1.setName("Menu1"); menu1.setToolTip("menu1");
		 * menu1.setResourceId(1); menu1.id=1;
		 */

		Set<MenuDto> menuDtos = getMenuMgmtService().getAllMenus();

		log.info("getAllMenus() : ends");
		return menuDtos;

	}

}
