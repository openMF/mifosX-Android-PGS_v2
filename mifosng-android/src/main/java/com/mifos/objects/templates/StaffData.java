package com.mifos.objects.templates;

import com.google.gson.annotations.Expose;

/**
 * Created by antoniocarella on 6/19/14.
 */
public class StaffData {

    @Expose
    private Long id;
    @Expose
    private String externalId;
    @Expose
    private String firstname;
    @Expose
    private String lastname;
    @Expose
    private String displayName;
    @Expose
    private String mobileNo;
    @Expose
    private Long officeId;
    @Expose
    private String officeName;
    @Expose
    private Boolean isLoanOfficer;
    @Expose
    private Boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public Boolean getIsLoanOfficer() {
        return isLoanOfficer;
    }

    public void setIsLoanOfficer(Boolean isLoanOfficer) {
        this.isLoanOfficer = isLoanOfficer;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
