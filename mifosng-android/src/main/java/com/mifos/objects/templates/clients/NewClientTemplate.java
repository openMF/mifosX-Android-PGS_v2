package com.mifos.objects.templates.clients;

import com.google.gson.annotations.Expose;
import com.mifos.objects.system.CodeValueData;
import com.mifos.objects.templates.OfficeData;
import com.mifos.objects.templates.StaffData;
import com.mifos.objects.templates.savings.SavingsProductData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by antoniocarella on 6/19/14.
 */
public class NewClientTemplate {
    
    @Expose
    private Long officeId;
    
    @Expose
    private List<Integer> joinedDate = new ArrayList<Integer>();

    @Expose
    private Collection<OfficeData> officeOptions;

    @Expose
    private Collection<StaffData> staffOptions;

    @Expose
    private Collection<CodeValueData> closureReasons;

    @Expose
    private Collection<CodeValueData> genderOptions;

    @Expose
    private Collection<SavingsProductData> savingProductOptions;

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public List<Integer> getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(List<Integer> joinedDate) {
        this.joinedDate = joinedDate;
    }

    public Collection<OfficeData> getOfficeOptions() {
        return officeOptions;
    }

    public void setOfficeOptions(Collection<OfficeData> officeOptions) {
        this.officeOptions = officeOptions;
    }

    public Collection<StaffData> getStaffOptions() {
        return staffOptions;
    }

    public void setStaffOptions(Collection<StaffData> staffOptions) {
        this.staffOptions = staffOptions;
    }

    public Collection<CodeValueData> getClosureReasons() {
        return closureReasons;
    }

    public void setClosureReasons(Collection<CodeValueData> closureReasons) {
        this.closureReasons = closureReasons;
    }

    public Collection<CodeValueData> getGenderOptions() {
        return genderOptions;
    }

    public void setGenderOptions(Collection<CodeValueData> genderOptions) {
        this.genderOptions = genderOptions;
    }

    public Collection<SavingsProductData> getSavingProductOptions() {
        return savingProductOptions;
    }

    public void setSavingProductOptions(Collection<SavingsProductData> savingProductOptions) {
        this.savingProductOptions = savingProductOptions;
    }
}
