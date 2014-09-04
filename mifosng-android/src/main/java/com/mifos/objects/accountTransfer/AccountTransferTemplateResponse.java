package com.mifos.objects.accountTransfer;

import com.google.gson.annotations.Expose;
import com.mifos.objects.Office;
import com.mifos.objects.accounts.Account;
import com.mifos.objects.accounts.AccountType;
import com.mifos.objects.Currency;
import com.mifos.objects.client.Client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by antoniocarella on 6/9/14.
 */
public class AccountTransferTemplateResponse {

    @Expose
    private int id;

    @Expose
    private boolean reversed;

    @Expose
    private Currency currency;

    @Expose
    private int transferAmount;

    @Expose
    private List<Integer> transferDate = new ArrayList<Integer>();

    @Expose
    private String transferDescription;

    @Expose
    private Office fromOffice;

    @Expose
    private Client fromClient;

    @Expose
    private AccountType fromAccountType;

    @Expose
    private Account fromAccount;

    @Expose
    private Office toOffice;

    @Expose
    private Client toClient;

    @Expose
    private AccountType toAccountType;

    @Expose
    private Account toAccount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isReversed() {
        return reversed;
    }

    public void setReversed(boolean reversed) {
        this.reversed = reversed;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(int transferAmount) {
        this.transferAmount = transferAmount;
    }

    public List<Integer> getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(List<Integer> transferDate) {
        this.transferDate = transferDate;
    }

    public String getTransferDescription() {
        return transferDescription;
    }

    public void setTransferDescription(String transferDescription) {
        this.transferDescription = transferDescription;
    }

    public Office getFromOffice() {
        return fromOffice;
    }

    public void setFromOffice(Office fromOffice) {
        this.fromOffice = fromOffice;
    }

    public Client getFromClient() {
        return fromClient;
    }

    public void setFromClient(Client fromClient) {
        this.fromClient = fromClient;
    }

    public AccountType getFromAccountType() {
        return fromAccountType;
    }

    public void setFromAccountType(AccountType fromAccountType) {
        this.fromAccountType = fromAccountType;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Office getToOffice() {
        return toOffice;
    }

    public void setToOffice(Office toOffice) {
        this.toOffice = toOffice;
    }

    public Client getToClient() {
        return toClient;
    }

    public void setToClient(Client toClient) {
        this.toClient = toClient;
    }

    public AccountType getToAccountType() {
        return toAccountType;
    }

    public void setToAccountType(AccountType toAccountType) {
        this.toAccountType = toAccountType;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public void setToAccount(Account toAccount) {
        this.toAccount = toAccount;
    }

}
