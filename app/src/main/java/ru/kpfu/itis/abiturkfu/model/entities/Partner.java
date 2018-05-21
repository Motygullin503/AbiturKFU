
package ru.kpfu.itis.abiturkfu.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Partner {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("image_link")
    @Expose
    private String imageLink;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

}
