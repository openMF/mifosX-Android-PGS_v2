package com.mifos.objects.accounts;

/**
 * Created by antoniocarella on 6/9/14.
 */
public class Account {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    private Integer id;
    private String accountNo;
}
