package com.warrantix.main.common.rest.model;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Rate {

    @SerializedName("customerID")
    @Expose
    private String customerID;
    @SerializedName("review")
    @Expose
    private Integer rate;

    /**
     *
     * @return
     * The customerID
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     *
     * @param customerID
     * The customerID
     */
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    /**
     *
     * @return
     * The rate
     */
    public Integer getReview() {
        return rate;
    }

    /**
     *
     * @param rate
     * The rate
     */
    public void setReview(Integer review) {
        this.rate = rate;
    }

}