package com.mifos.objects.accountTransfer;

import com.google.gson.annotations.Expose;

/**
 * Created by antoniocarella on 6/9/14.
 */
public class AccountTransferResponse {

    @Expose
    private int savingsId;

    @Expose
    private int resourceId;

    public int getSavingsId() {
        return savingsId;
    }

    public void setSavingsId(int savingsId) {
        this.savingsId = savingsId;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}
