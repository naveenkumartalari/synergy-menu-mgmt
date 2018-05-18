package com.orbc.syn.menumgmt.service;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

import com.orbc.syn.menumgmt.SynMenuMgmtAppTests;
import com.orbc.syn.menumgmt.dao.MenuMgmtDAO;
import com.orbc.syn.menumgmt.dto.MenuDto;

@SpringBootTest(classes = {MenuMgmtService.class,MenuMgmtDAO.class})
public class MenuMgmtServiceTest extends SynMenuMgmtAppTests{

	MenuMgmtServiceTest() {
		super();
	}
	
	@Test
	public final void testAddMenu() {
		MenuDto menuDto = new MenuDto();
		menuDto.setName("test menu");
		menuDto.setToolTip("sample menu");
		menuDto.setResourceId(1);
		menuDto.setParent(0);
		
		MenuDto menu = getMenuMgmtService().addMenu(menuDto);
		assertNotNull(menu, "unexpected null");
		System.out.println("menu.getId()"+menu.getId());
		assertFalse(menu.getId() == 0, "menu has not created");
	}

	@Test
	public final void testGetAllMenus() {
		Set<MenuDto> menusDto=getMenuMgmtService().getAllMenus();
		assertNotNull(menusDto, "unexpected null");
		assertFalse(menusDto.size() == 0, "menu set is empty");
	}

	@Test
	public final void testGetMenusList() {
		Set<MenuDto> data=getMenuMgmtService().getMenusList();
		assertNotNull(data, "unexpected null");
		assertFalse(data.size() == 0, "menu set is empty");
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

	@Test
	public final void testEditMenusList() {
		Set<MenuDto> data=getMenuDtoSet();
		
		Set<MenuDto> editedMenuDtoSet = getMenuMgmtService().editMenusList(data);
		assertNotNull(editedMenuDtoSet, "unexpected null");
		assertFalse(editedMenuDtoSet.size() == 0, "menu set is empty");
		assertFalse(editedMenuDtoSet.size() != data.size(), "menu list not edit");
	}

	@Test
	public final void testEditMenu() {
		
		MenuDto menuDto = new MenuDto();
		menuDto.setName("test menu");
		menuDto.setToolTip("sample menu");
		menuDto.setResourceId(1);
		menuDto.setParent(0);
		
		MenuDto editedMenuDto = getMenuMgmtService().editMenu(menuDto);
		assertNotNull(editedMenuDto, "unexpected null");
		assertFalse(menuDto.getId() != editedMenuDto.getId(), "different menu edited");
	}

	@Test
	public final void testDeleteMenu() {
		boolean flag = getMenuMgmtService().deleteMenu("1");
		assertTrue(flag, "menu not deleted");
	}

	@Test
	public final void testDeleteParent() {
		Set<MenuDto> data=getMenuDtoSet();

		boolean flag = getMenuMgmtService().deleteParent(data);
		assertTrue(flag, "parent menu not deleted");
	}
}
