package com.mifos.objects.templates;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by antoniocarella on 6/19/14.
 */
public class OfficeData {
    
    @Expose
    private Long id;
    @Expose
    private String name;
    @Expose
    private String nameDecorated;
    @Expose
    private String externalId;
    @Expose
    private List<Integer> openingDate = new ArrayList<Integer>();
    @Expose
    private String hierarchy;
    @Expose
    private Long parentId;
    @Expose
    private String parentName;

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

    public String getNameDecorated() {
        return nameDecorated;
    }

    public void setNameDecorated(String nameDecorated) {
        this.nameDecorated = nameDecorated;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public List<Integer> getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(List<Integer> openingDate) {
        this.openingDate = openingDate;
    }

    public String getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(String hierarchy) {
        this.hierarchy = hierarchy;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
