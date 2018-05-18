/**
 * 
 */
package com.orbc.syn.menumgmt.service;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orbc.syn.menumgmt.constants.ErrorCodes;
import com.orbc.syn.menumgmt.dao.MenuMgmtDAO;
import com.orbc.syn.menumgmt.dto.MenuDto;
import com.orbc.syn.menumgmt.entity.Menu;
import com.orbc.syn.menumgmt.exception.MenuMgmtServiceException;

/**
 * @author ntalari
 *
 */
@Service
public class MenuMgmtService {

	private static final Logger log = LogManager.getLogger(MenuMgmtService.class);

	@Autowired
	private MenuMgmtDAO menuMgmtDAO;

	public void setMenuMgmtDAO(MenuMgmtDAO menuMgmtDAO) {
		this.menuMgmtDAO = menuMgmtDAO;
	}

	public MenuMgmtDAO getMenuMgmtDAO() {
		return menuMgmtDAO;
	}

	public MenuDto addMenu(MenuDto menuDto) {

		log.info("addMenu(Menu menu) : starts");

		try {
			Menu menu = populateMenu(menuDto);
			menu = getMenuMgmtDAO().addMenu(menu);
			menuDto.setId(menu.getId());
		} catch (Exception e) {
			throw new MenuMgmtServiceException("add menu service exception",e, ErrorCodes.ADD_MENU_SERVICE_ERROR);
		}

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

		Set<MenuDto> menusDto=null;
		Set<Menu> menus = null;
		try {
			menus = getMenuMgmtDAO().getAllMenus();
			
			menusDto = populateMenuDtoSet(menus);
			
		} catch (Exception e) {
			throw new MenuMgmtServiceException("get all menus service exception",e, ErrorCodes.GET_ALL_MENUS_SERVICE_ERROR);
		}

		log.info("getAllMenus() : ends");
		return menusDto;
	}

	public Set<MenuDto> getMenusList() {
		log.info("getMenusList() : starts");

		Set<MenuDto> data = null;

		try {
			Set<Menu> menuSet = getMenuMgmtDAO().getMenusList();
			data = populateMenuDtoSet(menuSet);
		} catch (Exception e) {
			throw new MenuMgmtServiceException("get menus list service exception",e, ErrorCodes.GET_MENU_LIST_SERVICE_ERROR);
		}

		log.info("getMenusList() : ends");
		return data;
	}

	public Set<MenuDto> editMenusList(Set<MenuDto> menuDtos) {
		log.info("editMenusList(Set<MenuDto> menuDtos) : starts");

		try {
			Set<Menu> menuSet = populateMenuSet(menuDtos);
			menuSet = getMenuMgmtDAO().editMenuList(menuSet);
		} catch (Exception e) {
			throw new MenuMgmtServiceException("edit menus list service exception",e, ErrorCodes.EDIT_MENU_LIST_SERVICE_ERROR);
		}

		log.info("editMenusList(Set<MenuDto> menuDtos) : ends");

		return menuDtos;
	}

	public MenuDto editMenu(MenuDto menuDto) {

		log.info("editMenu(Menu menu) : starts");

		try {
			Menu menu = populateMenu(menuDto);

			menu = getMenuMgmtDAO().editMenu(menu);
		} catch (Exception e) {
			throw new MenuMgmtServiceException("edit menu service exception",e, ErrorCodes.EDIT_MENU_SERVICE_ERROR);
		}

		log.info("editMenu(Menu menu) : ends");
		return menuDto;
	}

	public boolean deleteMenu(String id) {

		log.info("deleteMenu(String id) : starts");

		boolean flag=false;
		try {
			flag = getMenuMgmtDAO().deleteMenu(id);
		} catch (Exception e) {
			throw new MenuMgmtServiceException("delete menu service exception",e, ErrorCodes.ADD_MENU_SERVICE_ERROR);
		}

		log.info("deleteMenu(String id) : ends");
		return flag;
	}

	public boolean deleteParent(Set<MenuDto> menuDtos) {

		log.info("deleteParent(Set<MenuDto> menuDtos) : starts");
		boolean flag=false;
		try {
			Set<Menu> menuSet = populateMenuSet(menuDtos);
			flag = getMenuMgmtDAO().deleteParent(menuSet);
		} catch (Exception e) {
			throw new MenuMgmtServiceException("delete parent menu service exception",e, ErrorCodes.DELETE_PARENT_MENU_SERVICE_ERROR);
		}

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
