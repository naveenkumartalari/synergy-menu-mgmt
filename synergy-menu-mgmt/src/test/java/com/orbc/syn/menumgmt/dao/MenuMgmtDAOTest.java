package com.orbc.syn.menumgmt.dao;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.orbc.syn.menumgmt.SynergyMenuMgmtApplication;
import com.orbc.syn.menumgmt.entity.Menu;

@SpringBootTest(classes = SynergyMenuMgmtApplication.class)
public class MenuMgmtDAOTest extends AbstractTestNGSpringContextTests{
	
	@Mock
	private MenuMgmtDAO menuMgmtDAO;	
	
	@BeforeTest
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		
		Menu menu=new Menu();
		menu.id=1;
		menu.setName("test menu");
		menu.setToolTip("sample menu");
		menu.setResourceId(1);
		menu.setParentMenuId(0);
		menu.setIsDeleted(0);
		
		when(menuMgmtDAO.addMenu(anyObject())).thenReturn(menu);
	}
	
	
	@Test
	public void testAddMenu() {
		
		Menu menu=new Menu();
		menu.setName("test menu");
		menu.setToolTip("sample menu");
		menu.setResourceId(1);
		
		menu=menuMgmtDAO.addMenu(menu);
		assertNotNull(menu,"unexpected null");
		assertFalse( menu.getId()==0,"menu has not created");
		
	}
	

}
