package com.orbc.syn.menumgmt.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.orbc.syn.menumgmt.SynergyMenuMgmtApplication;
import com.orbc.syn.menumgmt.entity.Menu;

@SpringBootTest(classes = SynergyMenuMgmtApplication.class)
public class MenuManagementDAOTest extends AbstractTestNGSpringContextTests{
	
	@Mock
	private MenuManagementDAO menuMgmtDAO;	
	
	@Autowired
	private MenuManagementDAO menuMgmtDao;
	
	@Before
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
		
		menu=menuMgmtDao.addMenu(menu);
		assertNotNull("unexpected null",menu);
		assertFalse("menu has not created", menu.getId()==0);
		
	}
	

}
