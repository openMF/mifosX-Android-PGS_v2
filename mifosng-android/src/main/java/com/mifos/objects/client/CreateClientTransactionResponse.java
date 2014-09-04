package com.mifos.objects.client;

import com.google.gson.annotations.Expose;

/**
 * Created by antoniocarella on 6/19/14.
 */
public class CreateClientTransactionResponse {

    @Expose
    private int officeId;

    @Expose
    private int clientId;

    @Expose
    private int resourceId;

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getResourceId() {
        return resourceId;
    }

}
