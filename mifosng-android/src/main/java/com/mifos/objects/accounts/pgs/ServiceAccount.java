package com.mifos.objects.accounts.pgs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by antoniocarella on 7/31/14.
 */
public class ServiceAccount {

    private Integer id;

    private Integer pgsClientId;

    private Integer currentAccountInformation;

    private List<Integer> usageRecords = new ArrayList<Integer>();

    private List<Integer> transactionRecords = new ArrayList<Integer>();

    private Double amount;

    private boolean isActivated;

    private List<Integer> serviceAccountStatusHistories =
            new ArrayList<Integer>();

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
