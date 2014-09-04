package com.mifos.objects.client;

import com.google.gson.annotations.Expose;

/**
 * Created by antoniocarella on 6/19/14.
 */
public class CreateClientTransactionRequest {


    @Expose
    private String firstname;

    @Expose
    private String middlename;

    @Expose
    private String lastname;

    @Expose
    private int officeId;

    //Requires active=true and activationDate OR active=false
    @Expose
    private Boolean active;

    @Expose
    private String locale;

    @Expose
    private String dateFormat;

    @Expose
    private Long savingsProductId;

    @Expose
    private String fullname;

    @Expose
    private String dateOfBirth;

    @Expose
    private String activationDate;

    @Expose
    private String externalId;

    @Expose
    private Long staffId;

    @Expose
    private String mobileNo;

    @Expose
    private String accountNo;

    @Expose
    private int genderId;

    //Stop commenting here

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public Long getSavingsProductId() {
        return savingsProductId;
    }

    public void setSavingsProductId(Long savingsProductId) {
        this.savingsProductId = savingsProductId;
    }

    // Start commenting here
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(String activationDate) {
        this.activationDate = activationDate;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public int getGenderId() {
        return genderId;
    }

    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }

    // Stop commenting here

}
