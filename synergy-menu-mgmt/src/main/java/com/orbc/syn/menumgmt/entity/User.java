/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orbc.syn.menumgmt.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author mtheetla
 */
@Entity
@Table(name = "user", catalog = "resourcemanagement", schema = "")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 5)
    @Column(name = "title")
    private String title;
    @Size(max = 30)
    @Column(name = "guid")
    private String guid;
    @Size(max = 30)
    @Column(name = "username")
    private String username;
    @Size(max = 10)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 10)
    @Column(name = "last_name")
    private String lastName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 10)
    @Column(name = "email")
    private String email;
    @Column(name = "mobile_number")
    private Integer mobileNumber;
    @Size(max = 100)
    @Column(name = "primary_email")
    private String primaryEmail;
    @Size(max = 100)
    @Column(name = "secondary_email")
    private String secondaryEmail;
    @Column(name = "primary_phone")
    private Integer primaryPhone;
    @Column(name = "secondary_phone")
    private Integer secondaryPhone;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "created_by")
    private Integer createdBy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;
    @Column(name = "last_updated_by")
    private Integer lastUpdatedBy;
    @Column(name = "deleted_by")
    private Integer deletedBy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "deleted_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "password_expired_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date passwordExpiredDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_login_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "locked_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lockedDate;
    @Column(name = "active")
    private Integer active;
    @Column(name = "disabled_flag")
    private Integer disabledFlag;
    @Column(name = "soft_delete_flag")
    private Integer softDeleteFlag;
    @Column(name = "failed_login_attempts")
    private Integer failedLoginAttempts;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, Date createdDate, Date lastUpdatedDate, Date deletedDate, Date passwordExpiredDate, Date lastLoginDate, Date lockedDate) {
        this.id = id;
        this.createdDate = createdDate;
        this.lastUpdatedDate = lastUpdatedDate;
        this.deletedDate = deletedDate;
        this.passwordExpiredDate = passwordExpiredDate;
        this.lastLoginDate = lastLoginDate;
        this.lockedDate = lockedDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Integer mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getSecondaryEmail() {
        return secondaryEmail;
    }

    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }

    public Integer getPrimaryPhone() {
        return primaryPhone;
    }

    public void setPrimaryPhone(Integer primaryPhone) {
        this.primaryPhone = primaryPhone;
    }

    public Integer getSecondaryPhone() {
        return secondaryPhone;
    }

    public void setSecondaryPhone(Integer secondaryPhone) {
        this.secondaryPhone = secondaryPhone;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Integer getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(Integer deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    public Date getPasswordExpiredDate() {
        return passwordExpiredDate;
    }

    public void setPasswordExpiredDate(Date passwordExpiredDate) {
        this.passwordExpiredDate = passwordExpiredDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Date getLockedDate() {
        return lockedDate;
    }

    public void setLockedDate(Date lockedDate) {
        this.lockedDate = lockedDate;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getDisabledFlag() {
        return disabledFlag;
    }

    public void setDisabledFlag(Integer disabledFlag) {
        this.disabledFlag = disabledFlag;
    }

    public Integer getSoftDeleteFlag() {
        return softDeleteFlag;
    }

    public void setSoftDeleteFlag(Integer softDeleteFlag) {
        this.softDeleteFlag = softDeleteFlag;
    }

    public Integer getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(Integer failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.orbc.syn.menumgmt.entity.User[ id=" + id + " ]";
    }
    
}
