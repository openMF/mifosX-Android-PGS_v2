package com.mifos.objects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by antoniocarella on 6/9/14.
 */
public class Office {

    private Integer id;
    private Integer parentId;
    private Integer externalId;
    private String name;
    private List<Integer> openingDate = new ArrayList<Integer>();

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getExternalId() {
        return externalId;
    }

    public void setExternalId(Integer externalId) {
        this.externalId = externalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(List<Integer> openingDate) {
        this.openingDate = openingDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
