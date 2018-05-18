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
@Table(name = "user_usergroup_mapping", catalog = "resourcemanagement", schema = "")
@NamedQueries({
    @NamedQuery(name = "UserUsergroupMapping.findAll", query = "SELECT u FROM UserUsergroupMapping u")})
public class UserUsergroupMapping implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Usergroup_id")
    private int usergroupid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "User_guid")
    private int userguid;

    public UserUsergroupMapping() {
    }

    public UserUsergroupMapping(Integer id) {
        this.id = id;
    }

    public UserUsergroupMapping(Integer id, int usergroupid, int userguid) {
        this.id = id;
        this.usergroupid = usergroupid;
        this.userguid = userguid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUsergroupid() {
        return usergroupid;
    }

    public void setUsergroupid(int usergroupid) {
        this.usergroupid = usergroupid;
    }

    public int getUserguid() {
        return userguid;
    }

    public void setUserguid(int userguid) {
        this.userguid = userguid;
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
        if (!(object instanceof UserUsergroupMapping)) {
            return false;
        }
        UserUsergroupMapping other = (UserUsergroupMapping) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.orbc.syn.menumgmt.entity.UserUsergroupMapping[ id=" + id + " ]";
    }
    
}
