package com.mifos.objects.accounts.pgs;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by antoniocarella on 7/31/14.
 */
public class ServiceAccountRequest {


    @Expose
    private Integer id;

    @Expose
    private Integer pgsClientId;

    @Expose
    private Integer currentAccountInformation;

    @Expose
    private List<Integer> usageRecords = new ArrayList<Integer>();

    @Expose
    private List<Integer> transactionRecords = new ArrayList<Integer>();

    @Expose
    private Double amount;

    @Expose
    private boolean isActivated;

    @Expose
    private List<Integer> serviceAccountStatusHistories =
            new ArrayList<Integer>();

    @Expose
    private Integer serviceOfferingId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPgsClientId() {
        return pgsClientId;
    }

    public void setPgsClientId(Integer pgsClientId) {
        this.pgsClientId = pgsClientId;
    }

    public Integer getCurrentAccountInformation() {
        return currentAccountInformation;
    }

    public void setCurrentAccountInformation(Integer currentAccountInformation) {
        this.currentAccountInformation = currentAccountInformation;
    }

    public List<Integer> getUsageRecords() {
        return usageRecords;
    }

    public void setUsageRecords(List<Integer> usageRecords) {
        this.usageRecords = usageRecords;
    }

    public List<Integer> getTransactionRecords() {
        return transactionRecords;
    }

    public void setTransactionRecords(List<Integer> transactionRecords) {
        this.transactionRecords = transactionRecords;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean isActivated) {
        this.isActivated = isActivated;
    }

    public List<Integer> getServiceAccountStatusHistories() {
        return serviceAccountStatusHistories;
    }

    public void setServiceAccountStatusHistories(List<Integer> serviceAccountStatusHistories) {
        this.serviceAccountStatusHistories = serviceAccountStatusHistories;
    }

    public Integer getServiceOfferingId() {
        return serviceOfferingId;
    }

    public void setServiceOfferingId(Integer serviceOfferingId) {
        this.serviceOfferingId = serviceOfferingId;
    }


}
