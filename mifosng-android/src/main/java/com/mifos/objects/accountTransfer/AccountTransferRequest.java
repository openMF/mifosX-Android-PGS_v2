package com.mifos.objects.accountTransfer;

import com.google.gson.annotations.Expose;

/**
 * Created by antoniocarella on 6/9/14.
 */
public class AccountTransferRequest {

    @Expose
    private int fromOfficeId;

    @Expose
    private int fromClientId;

    @Expose
    private int fromAccountType;

    @Expose
    private int fromAccountId;

    @Expose
    private int toOfficeId;

    @Expose
    private int toClientId;

    @Expose
    private int toAccountType;

    @Expose
    private int toAccountId;

    @Expose
    private String dateFormat;

    @Expose
    private String locale;

    @Expose
    private String transferDate;

    @Expose
    private String transferAmount;

    @Expose
    private String transferDescription;

    public int getFromOfficeId() {
        return fromOfficeId;
    }

    public void setFromOfficeId(int fromOfficeId) {
        this.fromOfficeId = fromOfficeId;
    }

    public int getFromClientId() {
        return fromClientId;
    }

    public void setFromClientId(int fromClientId) {
        this.fromClientId = fromClientId;
    }

    public int getFromAccountType() {
        return fromAccountType;
    }

    public void setFromAccountType(int fromAccountType) {
        this.fromAccountType = fromAccountType;
    }

    public int getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(int fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public int getToOfficeId() {
        return toOfficeId;
    }

    public void setToOfficeId(int toOfficeId) {
        this.toOfficeId = toOfficeId;
    }

    public int getToClientId() {
        return toClientId;
    }

    public void setToClientId(int toClientId) {
        this.toClientId = toClientId;
    }

    public int getToAccountType() {
        return toAccountType;
    }

    public void setToAccountType(int toAccountType) {
        this.toAccountType = toAccountType;
    }

    public int getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(int toAccountId) {
        this.toAccountId = toAccountId;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(String transferDate) {
        this.transferDate = transferDate;
    }

    public String getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(String transferAmount) {
        this.transferAmount = transferAmount;
    }

    public String getTransferDescription() {
        return transferDescription;
    }

    public void setTransferDescription(String transferDescription) {
        this.transferDescription = transferDescription;
    }

}
