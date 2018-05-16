/**
 * 
 */
package com.orbc.syn.menumgmt.dao;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.orbc.syn.menumgmt.constants.DBConstants;
import com.orbc.syn.menumgmt.entity.Menu;
import com.orbc.syn.menumgmt.utils.HibernateUtil;

/**
 * @author ntalari
 *
 */
@Component
public class MenuManagementDAO {

	private static final Logger log = LogManager.getLogger(MenuManagementDAO.class);

	public Menu addMenu(Menu menu) {

		log.info("addMenu(Menu menu) : starts");

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			session.save(menu);

			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			return menu;
		} finally {
			session.close();
		}
		log.info("addMenu(Menu menu) : ends");
		return menu;
	}

	public Set<Menu> getAllMenus() {

		log.info("getAllMenus() : starts");

		Set<Menu> data = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Criteria appcrt = session.createCriteria(Menu.class);
			appcrt.add(Restrictions.eq("isDeleted", DBConstants.MENU_NOT_DELETED));

			data = new LinkedHashSet<Menu>(appcrt.list());

			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		log.info("getAllMenus() : ends");
		return data;

	}

	public Set<Menu> getMenusList() {
		log.info("getMenusList() : starts");

		Set<Menu> data = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			Criteria appcrt = session.createCriteria(Menu.class);
			// appcrt.add(Restrictions.isNull("parent"));
			appcrt.add(Restrictions.eq("isDeleted", DBConstants.MENU_NOT_DELETED));
			appcrt.addOrder(Order.asc("menuOrder"));
			data = new LinkedHashSet<Menu>(appcrt.list());

			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		log.info("getMenusList() : ends");
		return data;

	}

	public Set<Menu> editMenuList(Set<Menu> menus) {

		log.info("editMenuList(Set<Menu> menus) : starts");

		Set<Menu> data = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
			log.info("editMenuList's size : "+menus.size());
			for (Menu menu : menus) {
				session.update(menu);
			}

			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		log.info("editMenuList(Set<Menu> menus) : ends");
		return data;

	}

	public Menu editMenu(Menu menu) {

		log.info("editMenu(Menu menu) : starts");

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.update(menu);

			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			return menu;
		} finally {
			session.close();
		}

		log.info("editMenu(Menu menu) : ends");
		return menu;
	}

	public boolean deleteMenu(String Id) {

		log.info("deleteMenu(String Id) : starts");

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Set<Menu> data = null;
		try {
			transaction = session.beginTransaction();

			Menu menu = (Menu) session.load(Menu.class, new Integer(Id));
			menu.setIsDeleted(DBConstants.MENU_DELETED);// soft delete.

			/*if (menu.getParent() != null) {
				menu.getParent().getchildren().remove(menu);
				session.save(menu.getParent());
			} 
				 * else { Criteria appcrt = session.createCriteria(Menu.class);
				 * appcrt.add(Restrictions.eq("parent_menu_item_id",menu.getId())); data = new
				 * HashSet<Menu>(appcrt.list()); }
				 */

			session.update(menu);

			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}

		log.info("deleteMenu(String Id) : ends");
		return true;
	}
	
	public boolean deleteParent(Set<Menu> menus) {

		log.info("deleteParent(Set<Menu> menus) : starts");

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
			log.info("list size : "+menus.size());
			for (Menu menu : menus) {
				menu.setIsDeleted(DBConstants.MENU_DELETED);// soft delete.
				session.update(menu);
			}

			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}

		log.info("deleteParent(Set<Menu> menus) : ends");
		return true;
	}

}
