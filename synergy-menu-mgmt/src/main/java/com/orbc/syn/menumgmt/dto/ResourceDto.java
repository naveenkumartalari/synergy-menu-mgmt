package com.orbc.syn.menumgmt.dto;

import com.orbc.syn.menumgmt.entity.Feature;
import com.orbc.syn.menumgmt.entity.IntegrationType;

/**
 *
 * @author mtheetla
 */
public class ResourceDto implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String Description;
    private String url;
    private String tag;
    private IntegrationType type;
    private Boolean status;
    private String version;
    private Feature feature;
    
    public ResourceDto() {        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public IntegrationType getType() {
        return type;
    }

    public void setType(IntegrationType type) {
        this.type = type;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    
    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
    
    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    @Override
    public String toString() {
        return "Resource{" + "id=" + id + ", name=" + name + ", url=" + url + ", tag=" + tag + ", type=" + type + ", status=" + status + ", feature=" + feature + ", version=" + version + '}';
    }

    public ResourceDto(Long id, String name, String Description, String url, String tag, IntegrationType type, Boolean status, String version, Feature feature) {
        this.id = id;
        this.name = name;
        this.Description = Description;
        this.url = url;
        this.tag = tag;
        this.type = type;
        this.status = status;
        this.version = version;
        this.feature = feature;
    }
    
    
}
