/**
 * 
 */
package com.orbc.syn.menumgmt.controller;

import java.util.LinkedHashSet;
import java.util.Set;

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

import com.orbc.syn.menumgmt.constants.ControllerConstants;
import com.orbc.syn.menumgmt.dto.MenuDto;
import com.orbc.syn.menumgmt.dto.Response;
import com.orbc.syn.menumgmt.service.MenuMgmtService;

/**
 * @author ntalari
 *
 */
@RestController
public class MenuMgmtController {

	private static final Logger log = LogManager.getLogger(MenuMgmtController.class);

	@Autowired
	private MenuMgmtService menuMgmtService;

	public MenuMgmtService getMenuMgmtService() {
		return menuMgmtService;
	}
	
	public void setMenuMgmtService(MenuMgmtService menuMgmtService) {
		this.menuMgmtService = menuMgmtService;
	}

	@RequestMapping(value = "/addMenu", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public MenuDto addMenu(@RequestBody MenuDto menuDto) {

		log.info("addMenu(Menu menu) : starts");

			menuDto = getMenuMgmtService().addMenu(menuDto);
		
		log.info("addMenu(Menu menu) : ends");
		return menuDto;
	}

	@RequestMapping(value = "/editMenu", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public MenuDto editMenu(@RequestBody MenuDto menuDto) {

		log.info("editMenu(Menu menu) : starts");

		menuDto = getMenuMgmtService().editMenu(menuDto);

		log.info("editMenu(Menu menu) : ends");
		return menuDto;
	}

	@RequestMapping(value = "/deleteMenu/{menuId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response deleteMenu(@PathVariable("menuId") String menuId) {

		log.info("deleteMenu(Menu menu) : starts");

		boolean flag = getMenuMgmtService().deleteMenu(menuId);

		Response response = new Response();

		if (flag) {
			response.setResult(ControllerConstants.SUCCESS);
			response.setDesc("Menu Id " + menuId + " has been deleted");
		} else {
			response.setResult(ControllerConstants.FAILURE);
			response.setDesc("Menu Id " + menuId + " not deleted");
		}

		log.info("deleteMenu(Menu menu) : ends");
		return response;

	}

	@GetMapping(value = "/getAllMenus", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Set<MenuDto> getAllMenus() {

		log.info("getAllMenus() : starts");

		Set<MenuDto> menuDtos = getMenuMgmtService().getAllMenus();

		log.info("getAllMenus() : ends");
		return menuDtos;
	}

	@GetMapping(value = "/getMenus", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Set<MenuDto> getMenusList() {

		log.info("getMenusList() : starts");

		Set<MenuDto> menuDtos = getMenuMgmtService().getMenusList();

		log.info("getMenusList() : ends");
		return menuDtos;

	}

	@RequestMapping(value = "/editMenus", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Set<MenuDto> editMenusList(@RequestBody LinkedHashSet<MenuDto> menuDtoSet) {

		log.info("editMenusList(LinkedHashSet<MenuDto> menuDtoSet) : starts");

		Set<MenuDto> menuSet = getMenuMgmtService().editMenusList(menuDtoSet);

		log.info("editMenusList(LinkedHashSet<MenuDto> menuDtoSet) : ends");
		return menuSet;
	}

	@RequestMapping(value = "/deleteParent", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response deleteParent(@RequestBody LinkedHashSet<MenuDto> menuDtoSet) {

		log.info("deleteParent(LinkedHashSet<MenuDto> menuDtoSet) : starts");

		boolean flag = getMenuMgmtService().deleteParent(menuDtoSet);

		Response response = new Response();

		if (flag) {
			response.setResult(ControllerConstants.SUCCESS);
			response.setDesc("Parent has been deleted");
		} else {
			response.setResult(ControllerConstants.FAILURE);
			response.setDesc("Parent has not deleted");
		}

		log.info("deleteParent(LinkedHashSet<MenuDto> menuDtoSet) : ends");
		return response;
	}
	
	@GetMapping(value = "/getUserMenus/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Set<MenuDto> getUserMenusList(@PathVariable("userName") String userName) {

		log.info("getUserMenusList(String userName) : starts");

		Set<MenuDto> menuDtos = getMenuMgmtService().getUserMenusList(userName);

		log.info("getUserMenusList(String userName) : ends");
		return menuDtos;

	}
}
