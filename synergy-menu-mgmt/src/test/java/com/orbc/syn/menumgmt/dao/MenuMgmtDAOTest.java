package com.orbc.syn.menumgmt.dao;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

import com.orbc.syn.menumgmt.SynMenuMgmtAppTests;
import com.orbc.syn.menumgmt.entity.Menu;

@SpringBootTest(classes = MenuMgmtDAO.class)
public class MenuMgmtDAOTest extends SynMenuMgmtAppTests {

	MenuMgmtDAOTest() {
		super();
	}

	@Test
	public final void testAddMenu() {

		Menu menu = new Menu();
		menu.setName("test menu");
		menu.setToolTip("sample menu");
		menu.setResourceId(1);

		menu = getMenuMgmtDAO().addMenu(menu);
		assertNotNull(menu, "unexpected null");
		assertFalse(menu.getId() == 0, "menu has not created");

	}

	@Test
	public final void testGetAllMenus() {

		Set<Menu> menuSet = getMenuMgmtDAO().getAllMenus();
		assertNotNull(menuSet, "unexpected null");
		assertFalse(menuSet.size() == 0, "menu set is empty");
	}

	@Test
	public final void testGetMenusList() {
		Set<Menu> menuSet = getMenuMgmtDAO().getMenusList();
		assertNotNull(menuSet, "unexpected null");
		assertFalse(menuSet.size() == 0, "menu set is empty");
	}

	@Test
	public final void testEditMenuList() {

		Set<Menu> menuSet = getMenuSet();

		Set<Menu> editedMenuSet = getMenuMgmtDAO().editMenuList(menuSet);
		assertNotNull(editedMenuSet, "unexpected null");
		assertFalse(editedMenuSet.size() == 0, "menu set is empty");
		assertFalse(editedMenuSet.size() != menuSet.size(), "menu list not edit");

	}

	private Set<Menu> getMenuSet() {
		Menu menu1 = new Menu();
		menu1.id = 1;
		menu1.setName("test menu1");
		menu1.setToolTip("sample menu1");
		menu1.setResourceId(1);
		menu1.setParentMenuId(0);
		menu1.setIsDeleted(0);

		Menu menu2 = new Menu();
		menu2.id = 2;
		menu2.setName("test menu2");
		menu2.setToolTip("sample menu2");
		menu2.setResourceId(1);
		menu2.setParentMenuId(0);
		menu2.setIsDeleted(0);

		Set<Menu> menuSet = new LinkedHashSet<>();
		menuSet.add(menu1);
		menuSet.add(menu2);
		return menuSet;
	}

	@Test
	public final void testEditMenu() {
		Menu menu = new Menu();
		menu.setId(1);
		menu.setName("test menu");
		menu.setToolTip("sample menu");
		menu.setResourceId(1);

		Menu editedMenu = getMenuMgmtDAO().editMenu(menu);
		assertNotNull(editedMenu, "unexpected null");
		assertFalse(menu.getId() != editedMenu.getId(), "different menu edited");

	}

	@Test
	public final void testDeleteMenu() {

		boolean flag = getMenuMgmtDAO().deleteMenu("1");
		assertTrue(flag, "menu not deleted");
	}

	@Test
	public final void testDeleteParent() {
		Set<Menu> menuSet = getMenuSet();

		boolean flag = getMenuMgmtDAO().deleteParent(menuSet);
		assertTrue(flag, "parent menu not deleted");
	}

}
