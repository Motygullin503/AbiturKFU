
package ru.kpfu.itis.abiturkfu.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SelectionComitee {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("body")
    @Expose
    private PartnerAndCommittee body;
    @SerializedName("errors")
    @Expose
    private Object errors;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public PartnerAndCommittee getBody() {
        return body;
    }

    public void setBody(PartnerAndCommittee body) {
        this.body = body;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }

}
