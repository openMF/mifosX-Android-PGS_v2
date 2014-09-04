package com.mifos.objects.templates.savings;

import com.google.gson.annotations.Expose;

/**
 * Created by antoniocarella on 6/19/14.
 */
public class SavingsProductData {

    @Expose
    private Long id;
    @Expose
    private String name;
    @Expose
    private boolean withdrawalFeeForTransfers;
    @Expose
    private boolean allowOverdraft;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isWithdrawalFeeForTransfers() {
        return withdrawalFeeForTransfers;
    }

    public void setWithdrawalFeeForTransfers(boolean withdrawalFeeForTransfers) {
        this.withdrawalFeeForTransfers = withdrawalFeeForTransfers;
    }

    public boolean isAllowOverdraft() {
        return allowOverdraft;
    }

    public void setAllowOverdraft(boolean allowOverdraft) {
        this.allowOverdraft = allowOverdraft;
    }
}
