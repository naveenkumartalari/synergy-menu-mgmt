package com.orbc.syn.menumgmt.dto;

public class MenuDto {

	public int id;
	private String name;
	private String toolTip;
	private int resourceId;
	
	private int parent;
	private int order;

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

	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + order;
		result = prime * result + parent;
		result = prime * result + resourceId;
		result = prime * result + ((toolTip == null) ? 0 : toolTip.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof MenuDto)) {
			return false;
		}
		MenuDto other = (MenuDto) obj;
		if (id != other.id) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (order != other.order) {
			return false;
		}
		if (parent != other.parent) {
			return false;
		}
		if (resourceId != other.resourceId) {
			return false;
		}
		if (toolTip == null) {
			if (other.toolTip != null) {
				return false;
			}
		} else if (!toolTip.equals(other.toolTip)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "MenuDto [id=" + id + ", name=" + name + ", toolTip=" + toolTip + ", resourceId=" + resourceId
				+ ", parent=" + parent + ", order=" + order + "]";
	}

}
