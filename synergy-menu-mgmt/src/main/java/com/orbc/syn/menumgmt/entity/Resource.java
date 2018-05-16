/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.orbc.syn.menumgmt.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author mtheetla
 */
@Entity
@Table(name = "resource")
@NamedQueries({
    @NamedQuery(name = "Resource.findAll", query = "SELECT r FROM Resource r")})
public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String resourceName;
    private String resourceDesc;
    private String integrationType;
    private String tags;
    private String url;
    private String status;
    private String version;
    private Set<Feature> features;
    
    public Resource() {
    }

    public Resource(Long id) {
        this.id = id;
    }

    public Resource( String resourceName, String resourceDesc, String integrationType, String tags, String url, String status, String version) {
        this.resourceName = resourceName;
        this.resourceDesc = resourceDesc;
        this.integrationType = integrationType;
        this.tags = tags;
        this.url = url;
        this.status = status;
        this.version = version;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Size(max = 50)
    @Column(name = "resource_name")
    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    @Size(max = 50)
    @Column(name = "resource_desc")
    public String getResourceDesc() {
        return resourceDesc;
    }

    public void setResourceDesc(String resourceDesc) {
        this.resourceDesc = resourceDesc;
    }

    @Size(max = 1000)
    @Column(name = "integration_type")
    public String getIntegrationType() {
        return integrationType;
    }

    public void setIntegrationType(String integrationType) {
        this.integrationType = integrationType;
    }

    @Size(max = 1000)
    @Column(name = "tags")
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Size(max = 1000)
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Size(max = 1000)
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Size(max = 100)
    @Column(name = "version")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    
    @OneToMany(targetEntity=Feature.class, cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "resource_id")
    public Set<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(Set<Feature> features) {
        this.features = features;
    }


    @Override
    public String toString() {
        return "com.orbcomm.synergy.resourceManagement.dao.Resource[ id=" + id + " ]";
    }
}
