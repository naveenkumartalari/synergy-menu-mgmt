/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orbc.syn.menumgmt.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author mtheetla
 */
@Entity
@Table(name = "permission_usergroup_mapping", catalog = "resourcemanagement", schema = "")
@NamedQueries({
    @NamedQuery(name = "PermissionUsergroupMapping.findAll", query = "SELECT p FROM PermissionUsergroupMapping p")})
public class PermissionUsergroupMapping implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "permission_id")
    private int permissionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "role_id")
    private int roleId;

    public PermissionUsergroupMapping() {
    }

    public PermissionUsergroupMapping(Integer id) {
        this.id = id;
    }

    public PermissionUsergroupMapping(Integer id, int permissionId, int roleId) {
        this.id = id;
        this.permissionId = permissionId;
        this.roleId = roleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PermissionUsergroupMapping)) {
            return false;
        }
        PermissionUsergroupMapping other = (PermissionUsergroupMapping) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.orbc.syn.menumgmt.entity.PermissionUsergroupMapping[ id=" + id + " ]";
    }
    
}
