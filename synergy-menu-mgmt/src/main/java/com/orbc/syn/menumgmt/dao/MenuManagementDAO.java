/**
 * 
 */
package com.orbc.syn.menumgmt.dao;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

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

			data = new HashSet<Menu>(appcrt.list());

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
		Set<Menu> data = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Criteria appcrt = session.createCriteria(Menu.class);
			appcrt.add(Restrictions.isNull("parent"));
			data = new HashSet<Menu>(appcrt.list());
			// for(Menu m1: data){
			// System.out.println(m1.getName() +" "+m1.getChildern().size());
			// for(Menu m12: m1.getChildern()){
			// System.out.println(m12.getName() +" "+m12.getChildern().size());
			// }
			// }

			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return data;

	}

	public Set<Menu> editMenuList(Set<Menu> menu) {

		Set<Menu> data = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			// Criteria appcrt = session.createCriteria(Menu.class);
			// appcrt.add(Restrictions.isNull("parent"));
			// data = new HashSet<Menu>(appcrt.list());
			//// for(Menu m1: data){
			//// System.out.println(m1.getName() +" "+m1.getChildern().size());
			//// for(Menu m12: m1.getChildern()){
			//// System.out.println(m12.getName() +" "+m12.getChildern().size());
			//// }
			//// }

			for (Menu m1 : menu) {
				session.update(m1);
			}

			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
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
		try {
			transaction = session.beginTransaction();

			Menu menu = (Menu) session.load(Menu.class, new Integer(Id));
			
			if(menu.getParent() != null){
				menu.getParent().getchildren().remove(menu);
				session.save(menu.getParent());
			}

			session.delete(menu);

			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
		
		log.info("deleteMenu(String Id) : starts");
		return true;
	}

}
