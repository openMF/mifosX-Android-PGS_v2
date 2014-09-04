
package com.mifos.objects.accounts;

import com.mifos.objects.accounts.savings.SavingsAccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientAccounts {


    private List<SavingsAccount> savingsAccounts = new ArrayList<SavingsAccount>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<SavingsAccount> getSavingsAccounts() {
        return savingsAccounts;
    }

    public void setSavingsAccounts(List<SavingsAccount> savingsAccounts) {
        this.savingsAccounts = savingsAccounts;
    }

    public ClientAccounts withSavingsAccounts(List<SavingsAccount> savingsAccounts) {
        this.savingsAccounts = savingsAccounts;
        return this;
    }

    @Override
    public String toString() {
        return "ClientAccounts{" +
                ", savingsAccounts=" + savingsAccounts +
                ", additionalProperties=" + additionalProperties +
                '}';
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
