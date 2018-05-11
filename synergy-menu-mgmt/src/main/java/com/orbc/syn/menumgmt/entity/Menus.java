package com.orbc.syn.menumgmt.entity;

import java.util.HashSet;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name ="menu_item")
public class Menus {
	
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "menu_item_id", unique = true, nullable = false)
		public int id;
		@Column(name="item_name")
		private String name;
		@Column(name="tool_tip")
		private String toolTip;
		
		@OneToOne
		@JoinColumn(name="resource_id")
		//@Column(name="resource_id")
		private Resource resource;
		public Resource getResource() {
			return resource;
		}

		public void setResource(Resource resource) {
			this.resource = resource;
		}

		/*@Column(name="menuorder")
		private int menuOrder;
		@Transient
		private boolean enable;
		
		public boolean isEnable() {
			return enable;
		}

		public void setEnable(boolean enable) {
			this.enable = enable;
		}

		public int getMenuOrder() {
			return menuOrder;
		}

		public void setMenuOrder(int order) {
			this.menuOrder = order;
		}*/

		@ManyToOne
		@JoinColumn(name="parent_menu_item_id")
		@JsonBackReference
		private Menus parent;

		@OneToMany(fetch = FetchType.EAGER, mappedBy="parent", cascade={CascadeType.ALL})
		@JsonManagedReference
		private Set<Menus> children = new HashSet<Menus>();

		public int getId() {
			return id;
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

		public Menus getParent() {
			return parent;
		}

		public void setParent(Menus parent) {
			this.parent = parent;
		}

		public Set<Menus> getchildren() {
			return children;
		}

		public void setchildren(Set<Menus> children) {
			this.children = children;
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
		@Override
		public int hashCode(){
			return this.getId();
		}
		@Override
		public boolean equals(Object o){
			Menus m1 =(Menus)o;
			return m1.getId() == this.getId();
		}

}
