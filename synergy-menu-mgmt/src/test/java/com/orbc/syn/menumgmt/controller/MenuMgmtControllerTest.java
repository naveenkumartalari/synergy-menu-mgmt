package com.orbc.syn.menumgmt.controller;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

import com.orbc.syn.menumgmt.SynMenuMgmtAppTests;
import com.orbc.syn.menumgmt.constants.ControllerConstants;
import com.orbc.syn.menumgmt.dao.MenuMgmtDAO;
import com.orbc.syn.menumgmt.dto.MenuDto;
import com.orbc.syn.menumgmt.dto.Response;
import com.orbc.syn.menumgmt.service.MenuMgmtService;

@SpringBootTest(classes = { MenuMgmtService.class, MenuMgmtDAO.class, MenuMgmtController.class })
public class MenuMgmtControllerTest extends SynMenuMgmtAppTests {

	MenuMgmtControllerTest() {
		super();
	}

	@Test
	public final void testAddMenu() {

		MenuDto menuDto = new MenuDto();
		menuDto.setName("test menu");
		menuDto.setToolTip("sample menu");
		menuDto.setResourceId(1);
		menuDto.setParent(0);

		MenuDto menu = getMenuMgmtController().addMenu(menuDto);
		assertNotNull(menu, "unexpected null");
		assertFalse(menu.getId() == 0, "menu has not created");
	}

	@Test
	public final void testEditMenu() {
		MenuDto menuDto = new MenuDto();
		menuDto.setName("test menu");
		menuDto.setToolTip("sample menu");
		menuDto.setResourceId(1);
		menuDto.setParent(0);

		MenuDto editedMenuDto = getMenuMgmtController().editMenu(menuDto);
		assertNotNull(editedMenuDto, "unexpected null");
		assertFalse(menuDto.getId() != editedMenuDto.getId(), "different menu edited");
	}

	@Test
	public final void testDeleteMenu() {
		Response response= getMenuMgmtController().deleteMenu("1");
		assertNotNull(response, "unexpected null response");
		assertFalse(response.getResult().equals(ControllerConstants.FAILURE), "parent menu not deleted");
	}

	@Test
	public final void testGetAllMenus() {
		Set<MenuDto> menusDto = getMenuMgmtController().getAllMenus();
		assertNotNull(menusDto, "unexpected null");
		assertFalse(menusDto.size() == 0, "menu set is empty");
	}

	@Test
	public final void testGetMenusList() {
		Set<MenuDto> data = getMenuMgmtController().getMenusList();
		assertNotNull(data, "unexpected null");
		assertFalse(data.size() == 0, "menu set is empty");
	}

	@Test
	public final void testEditMenusList() {
		Set<MenuDto> data = getMenuDtoSet();

		Set<MenuDto> editedMenuDtoSet = getMenuMgmtController().editMenusList((LinkedHashSet)data);
		assertNotNull(editedMenuDtoSet, "unexpected null");
		assertFalse(editedMenuDtoSet.size() == 0, "menu set is empty");
		assertFalse(editedMenuDtoSet.size() != data.size(), "menu list not edit");
	}

	@Test
	public final void testDeleteParent() {
		Set<MenuDto> data = getMenuDtoSet();

		Response response = getMenuMgmtController().deleteParent((LinkedHashSet)data);
		assertNotNull(response, "unexpected null response");
		assertFalse(response.getResult().equals(ControllerConstants.FAILURE), "parent menu not deleted");
	}
	
	private Set<MenuDto> getMenuDtoSet() {
		MenuDto menuDto1 = new MenuDto();
		menuDto1.id = 1;
		menuDto1.setName("test menu1");
		menuDto1.setToolTip("sample menu1");
		menuDto1.setResourceId(1);
		menuDto1.setParent(0);

		MenuDto menuDto2 = new MenuDto();
		menuDto2.id = 2;
		menuDto2.setName("test menu2");
		menuDto2.setToolTip("sample menu2");
		menuDto2.setResourceId(1);
		menuDto2.setParent(0);

		Set<MenuDto> menuDtoSet = new LinkedHashSet<>();
		menuDtoSet.add(menuDto1);
		menuDtoSet.add(menuDto2);
		return menuDtoSet;
	}

}
