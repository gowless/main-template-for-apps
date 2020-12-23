package com.maindevpattern.models.get;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("appId")
    @Expose
    private String appId;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("categories")
    @Expose
    private java.util.List<Category> categories = null;
    @SerializedName("list")
    @Expose
    private java.util.List<Liste> list = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public java.util.List<Category> getCategories() {
        return categories;
    }

    public void setCategories(java.util.List<Category> categories) {
        this.categories = categories;
    }

    public java.util.List<Liste> getList() {
        return list;
    }

    public void setList(java.util.List<Liste> list) {
        this.list = list;
    }

}