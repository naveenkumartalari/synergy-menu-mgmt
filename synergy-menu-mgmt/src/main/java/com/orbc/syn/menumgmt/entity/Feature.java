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
import javax.validation.constraints.Size;

/**
 *
 * @author mtheetla
 */
@Entity
@Table(name = "feature")
@NamedQueries({
    @NamedQuery(name = "Feature.findAll", query = "SELECT f FROM Feature f")})
public class Feature implements Serializable {

    private static final long serialVersionUID = 1L;
 
    private Integer id;
    private String featureName;
    private String featureDesc;
    private Long resource_id;

    public Long getResource_id() {
        return resource_id;
    }

    public void setResource_id(Long resource_id) {
        this.resource_id = resource_id;
    }

    public Feature() {
    }

    public Feature(Integer id) {
        this.id = id;
    }
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }


    @Size(max = 100)
    @Column(name = "feature_name")
    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }
    
      @Size(max = 100)
    @Column(name = "feature_desc")
    public String getFeatureDesc() {
        return featureDesc;
    }

    public void setFeatureDesc(String featureDesc) {
        this.featureDesc = featureDesc;
    }

    @Override
    public String toString() {
        return "com.orbcomm.synergy.resourceManagement.dao.Feature[ id=" + id + " ]";
    }
    
}
