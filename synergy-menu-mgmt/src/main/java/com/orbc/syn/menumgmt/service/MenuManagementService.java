/**
 * 
 */
package com.orbc.syn.menumgmt.service;

import java.util.HashSet;
import java.util.LinkedHashSet;
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

		menu.setMenuOrder(menuDto.getOrder());
		menu.setParentMenuId(menuDto.getParent());

		return menu;
	}

	private MenuDto populateMenuDto(Menu menu) {

		MenuDto menuDto = new MenuDto();
		menuDto.setId(menu.getId());
		menuDto.setName(menu.getName());
		menuDto.setToolTip(menu.getToolTip());
		menuDto.setResourceId(menu.getResourceId());

		menuDto.setParent(menu.getParentMenuId());
		menuDto.setOrder(menu.getMenuOrder());

		return menuDto;
	}

	public Set<MenuDto> getAllMenus() {
		log.info("getAllMenus() : starts");

		Set<MenuDto> menusDto = new HashSet<MenuDto>();
		Set<Menu> menus = null;
		menus = getMenuMgmtDAO().getAllMenus();

		for (Menu menu : menus) {
			menusDto.add(populateMenuDto(menu));
		}

		log.info("getAllMenus() : ends");
		return menusDto;
	}

	public Set<MenuDto> getMenusList() {
		log.info("getMenusList() : starts");

		Set<MenuDto> data = null;

		Set<Menu> menuSet = getMenuMgmtDAO().getMenusList();
		data = populateMenuDtoSet(menuSet);

		log.info("getMenusList() : ends");
		return data;
	}

	public Set<MenuDto> editMenusList(Set<MenuDto> menuDtos) {
		log.info("editMenusList(Set<MenuDto> menuDtos) : starts");

		Set<Menu> menuSet = populateMenuSet(menuDtos);
		menuSet = getMenuMgmtDAO().editMenuList(menuSet);

		log.info("editMenusList(Set<MenuDto> menuDtos) : ends");

		return menuDtos;
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

	public boolean deleteParent(Set<MenuDto> menuDtos) {

		log.info("deleteParent(Set<MenuDto> menuDtos) : starts");
		Set<Menu> menuSet = populateMenuSet(menuDtos);
		boolean flag = getMenuMgmtDAO().deleteParent(menuSet);

		log.info("deleteParent(Set<MenuDto> menuDtos) : ends");
		return flag;
	}

	private Set<Menu> populateMenuSet(Set<MenuDto> menuDtos) {

		Set<Menu> menuSet = new LinkedHashSet<>();
		for (MenuDto menuDto : menuDtos) {

			menuSet.add(populateMenu(menuDto));
		}
		return menuSet;
	}

	private Set<MenuDto> populateMenuDtoSet(Set<Menu> menus) {

		Set<MenuDto> menuDtoSet = new LinkedHashSet<>();
		for (Menu menu : menus) {
			menuDtoSet.add(populateMenuDto(menu));
		}

		return menuDtoSet;
	}

}
