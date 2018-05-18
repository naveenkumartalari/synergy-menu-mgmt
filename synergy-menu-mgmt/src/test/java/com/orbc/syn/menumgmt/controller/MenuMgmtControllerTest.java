package com.orbc.syn.menumgmt.controller;

import org.springframework.boot.test.context.SpringBootTest;

import com.orbc.syn.menumgmt.SynMenuMgmtAppTests;
import com.orbc.syn.menumgmt.dao.MenuMgmtDAO;
import com.orbc.syn.menumgmt.service.MenuMgmtService;

@SpringBootTest(classes = { MenuMgmtService.class, MenuMgmtDAO.class, MenuMgmtController.class })
public class MenuMgmtControllerTest extends SynMenuMgmtAppTests {

	MenuMgmtControllerTest() {
		super();
	}

	/*@Test
	public final void testAddMenu() {
		MenuDto menu = getMenuMgmtController().addMenu();
	}

	@Test
	public final void testEditMenu() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testDeleteMenu() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testAddResourceToMenu() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetAllMenus() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetMenusList() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testEditMenusList() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testDeleteParent() {
		fail("Not yet implemented"); // TODO
	}*/

}
