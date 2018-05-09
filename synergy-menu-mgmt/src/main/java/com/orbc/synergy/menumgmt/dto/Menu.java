package com.orbc.synergy.menumgmt.dto;

public class Menu {
	
	
		public int id;
		private String name;
		private String toolTip;
		private Resource resource;
		
		public Resource getResource() {
			return resource;
		}

		public void setResource(Resource resource) {
			this.resource = resource;
		}

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
