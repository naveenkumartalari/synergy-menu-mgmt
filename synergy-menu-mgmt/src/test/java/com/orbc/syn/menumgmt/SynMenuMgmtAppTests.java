package com.orbc.syn.menumgmt;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

import java.util.LinkedHashSet;
import java.util.Set;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;

import com.orbc.syn.menumgmt.controller.MenuMgmtController;
import com.orbc.syn.menumgmt.dao.MenuMgmtDAO;
import com.orbc.syn.menumgmt.dto.MenuDto;
import com.orbc.syn.menumgmt.entity.Menu;
import com.orbc.syn.menumgmt.service.MenuMgmtService;

@SpringBootTest(classes = SynergyMenuMgmtApp.class)
public class SynMenuMgmtAppTests extends AbstractTestNGSpringContextTests {

	@Mock
	private MenuMgmtDAO menuMgmtDAO=new MenuMgmtDAO();

	public MenuMgmtDAO getMenuMgmtDAO() {
		return menuMgmtDAO;
	}
	
	//@Autowired
	private MenuMgmtService menuMgmtService=new MenuMgmtService();
	
	public MenuMgmtService getMenuMgmtService() {
		return menuMgmtService;
	}
	
	private MenuMgmtController menuMgmtController=new MenuMgmtController();
	
	public MenuMgmtController getMenuMgmtController() {
		return menuMgmtController;
	}
	
	public SynMenuMgmtAppTests(){
		setUp();
	}

	@BeforeMethod
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		getMenuMgmtService().setMenuMgmtDAO(menuMgmtDAO);
		getMenuMgmtController().setMenuMgmtService(menuMgmtService);

		Menu menu1 = new Menu();
		menu1.id = 1;
		menu1.setName("test menu");
		menu1.setToolTip("sample menu");
		menu1.setResourceId(1);
		menu1.setParentMenuId(0);
		menu1.setIsDeleted(0);
		
		Menu menu2 = new Menu();
		menu2.id = 2;
		menu2.setName("test menu");
		menu2.setToolTip("sample menu");
		menu2.setResourceId(1);
		menu2.setParentMenuId(0);
		menu2.setIsDeleted(0);
		
		Set<Menu> menuSet=new LinkedHashSet<>();
		menuSet.add(menu1);
		menuSet.add(menu2);

		when(menuMgmtDAO.addMenu(anyObject())).thenReturn(menu1);
		when(menuMgmtDAO.getAllMenus()).thenReturn(menuSet);
		when(menuMgmtDAO.getMenusList()).thenReturn(menuSet);
		when(menuMgmtDAO.editMenuList(anyObject())).thenReturn(menuSet);
		when(menuMgmtDAO.editMenu(anyObject())).thenReturn(menu1);
		when(menuMgmtDAO.deleteMenu(anyObject())).thenReturn(true);
		when(menuMgmtDAO.deleteParent(anyObject())).thenReturn(true);
		
		MenuDto menuDto = new MenuDto();
		menuDto.setId(1);
		menuDto.setName("test menu");
		menuDto.setToolTip("sample menu");
		menuDto.setResourceId(1);
		menuDto.setParent(0);
		
		//when(menuMgmtService.addMenu(anyObject())).thenReturn(menuDto);
		
	}

}
