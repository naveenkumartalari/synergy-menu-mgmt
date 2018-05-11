/**
 * 
 */
package com.orbc.syn.menumgmt.service;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orbc.syn.menumgmt.dao.MenuManagementDAO;
import com.orbc.syn.menumgmt.dto.MenuDto;
import com.orbc.syn.menumgmt.entity.Menu;

/**
 * @author ntalari
 *
 */
@Service
public class MenuManagementService {

	private static final Logger log = LogManager.getLogger(MenuManagementService.class);

	@Autowired
	private MenuManagementDAO menuMgmtDAO;

	public MenuManagementDAO getMenuMgmtDAO() {
		return menuMgmtDAO;
	}

	public MenuDto addMenu(MenuDto menuDto) {

		log.info("addMenu(Menu menu) : starts");

		Menu menu = populateMenu(menuDto);

		menu = getMenuMgmtDAO().addMenu(menu);

		menuDto.setId(menu.getId());

		log.info("addMenu(Menu menu) : ends");
		return menuDto;
	}

	private Menu populateMenu(MenuDto menuDto) {

		Menu menu = new Menu();
		menu.setId(menuDto.getId());
		menu.setName(menuDto.getName());
		menu.setToolTip(menuDto.getToolTip());
		menu.setResourceId(menuDto.getResourceId());
		return menu;

	}

	private MenuDto populateMenuDto(Menu menu) {

		MenuDto menuDto = new MenuDto();
		menuDto.setId(menu.getId());
		menuDto.setName(menu.getName());
		menuDto.setToolTip(menu.getToolTip());
		menuDto.setResourceId(menu.getResourceId());
		return menuDto;

	}

	public Set<MenuDto> getAllMenus() {
		log.info("getAllMenus() : starts");

		Set<MenuDto> data = new HashSet<MenuDto>();
		Set<Menu> dataDto = null;
		dataDto = getMenuMgmtDAO().getAllMenus();

		for (Menu dto : dataDto) {
			data.add(populateMenuDto(dto));
		}

		log.info("getAllMenus() : ends");
		return data;

	}

	public Set<MenuDto> getMenusList() {
		Set<MenuDto> data = null;

		return data;

	}

	public Set<MenuDto> editMenuList(Set<MenuDto> menuDto) {

		Set<MenuDto> data = null;

		return data;

	}

	public MenuDto editMenu(MenuDto menuDto) {

		log.info("editMenu(Menu menu) : starts");

		Menu menu = populateMenu(menuDto);

		menu = getMenuMgmtDAO().editMenu(menu);

		log.info("editMenu(Menu menu) : ends");
		return menuDto;
	}

	public boolean deleteMenu(String id) {

		log.info("deleteMenu(String id) : starts");

		boolean flag = getMenuMgmtDAO().deleteMenu(id);

		log.info("deleteMenu(String id) : ends");
		return flag;
	}

}
