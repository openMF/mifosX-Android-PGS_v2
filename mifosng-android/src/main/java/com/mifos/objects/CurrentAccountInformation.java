package com.mifos.objects;

/**
 * Created by antoniocarella on 8/16/14.
 */
public class CurrentAccountInformation {

    private Integer id;
    private Integer clientId;
    private Integer savingsId;
    private double balance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getSavingsId() {
        return savingsId;
    }

    public void setSavingsId(Integer savingsId) {
        this.savingsId = savingsId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "CurrentAccountInformation{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", savingsId=" + savingsId +
                ", balance=" + balance +
                '}';
    }

}
