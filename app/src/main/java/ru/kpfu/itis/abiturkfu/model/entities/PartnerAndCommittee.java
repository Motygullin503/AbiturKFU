
package ru.kpfu.itis.abiturkfu.model.entities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PartnerAndCommittee {

    @SerializedName("partners")
    @Expose
    private List<Partner> partners = null;
    @SerializedName("committee")
    @Expose
    private List<Committee> committee = null;

    public List<Partner> getPartners() {
        return partners;
    }

    public void setPartners(List<Partner> partners) {
        this.partners = partners;
    }

    public List<Committee> getCommittee() {
        return committee;
    }

    public void setCommittee(List<Committee> committee) {
        this.committee = committee;
    }

}
