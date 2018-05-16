package com.orbc.syn.menumgmt.entity;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name ="menu_item")
public class Menu implements Serializable{
	
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id", unique = true, nullable = false)
		public int id;
		@Column(name="name")
		private String name;
		@Column(name="tooltip")
		private String toolTip;
		
		/*@OneToOne
		@JoinColumn(name="resource_id")*/
		@Column(name="resource_id")
		private int resourceId;
		public int getResourceId() {
			return resourceId;
		}

		public void setResourceId(int resourceId) {
			this.resourceId = resourceId;
		}

		@Column(name="menu_order")
		private int menuOrder;
		/*
		@Transient
		private boolean enable;
		
		public boolean isEnable() {
			return enable;
		}

		public void setEnable(boolean enable) {
			this.enable = enable;
		}*/

		public int getMenuOrder() {
			return menuOrder;
		}

		public void setMenuOrder(int order) {
			this.menuOrder = order;
		}

		/*@ManyToOne
		@JoinColumn(name="parent_menu_item_id")
		@JsonBackReference
		private Menu parent;

		@OneToMany(fetch = FetchType.EAGER, mappedBy="parent", cascade={CascadeType.ALL})
		@JsonManagedReference*/
		//@Column(name="parent_menu_item_id")
		//private Menu parent;
		//private Set<Menu> children = new LinkedHashSet<Menu>();

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getToolTip() {
			return toolTip;
		}

		public void setToolTip(String toolTip) {
			this.toolTip = toolTip;
		}

		/*public Menu getParent() {
			return parent;
		}

		public void setParent(Menu parent) {
			this.parent = parent;
		}

		public Set<Menu> getchildren() {
			return children;
		}

		public void setchildren(Set<Menu> children) {
			this.children = children;
		}*/
		
		@Column(name="parent_menu_id")
		private int parentMenuId;


		public int getParentMenuId() {
			return parentMenuId;
		}

		public void setParentMenuId(int parentMenuId) {
			this.parentMenuId = parentMenuId;
		}
//		@Override
//		public int compareTo(Object o) {
//			Menu menutoCOmpare = (Menu)o;
//			int diff = this.order - menutoCOmpare.order;
//			if(diff == 0){
//				return this.id-menutoCOmpare.id;
//			}
//			return diff;
//		}
		
		@Column(name=" is_deleted")
		private int  isDeleted;
		
		
		public int getIsDeleted() {
			return isDeleted;
		}

		public void setIsDeleted(int isDeleted) {
			this.isDeleted = isDeleted;
		}

		@Override
		public int hashCode(){
			return this.getId();
		}
		@Override
		public boolean equals(Object o){
			Menu m1 =(Menu)o;
			return m1.getId() == this.getId();
		}

}
