package me.bort.destructo.retrofittest.model;

import com.google.gson.annotations.SerializedName;

public class Photo1 {
    @SerializedName("id")
    private Integer id;
    @SerializedName("blah")
    private Integer blah;


    public Photo1(Integer id, Integer blah) {
        this.id = id;
        this.blah = blah;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBlah() {
        return blah;
    }

    public void setBlah(Integer id) {
        this.blah = blah;
    }

}
