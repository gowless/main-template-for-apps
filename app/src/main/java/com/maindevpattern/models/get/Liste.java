package com.maindevpattern.models.get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Liste {

    @SerializedName("percent")
    @Expose
    private Percent percent;
    @SerializedName("amount")
    @Expose
    private Amount amount;
    @SerializedName("term")
    @Expose
    private Term term;
    @SerializedName("detail")
    @Expose
    private Detail detail;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("offer_name")
    @Expose
    private String offerName;
    @SerializedName("offer_id")
    @Expose
    private String offerId;
    @SerializedName("top")
    @Expose
    private Boolean top;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("cpa")
    @Expose
    private String cpa;
    @SerializedName("categories")
    @Expose
    private java.util.List<String> categories = null;
    @SerializedName("push")
    @Expose
    private Push push;
    @SerializedName("isHidden")
    @Expose
    private Boolean isHidden;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("time_solution")
    @Expose
    private Object timeSolution;

    public Percent getPercent() {
        return percent;
    }

    public void setPercent(Percent percent) {
        this.percent = percent;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public Boolean getTop() {
        return top;
    }

    public void setTop(Boolean top) {
        this.top = top;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCpa() {
        return cpa;
    }

    public void setCpa(String cpa) {
        this.cpa = cpa;
    }

    public java.util.List<String> getCategories() {
        return categories;
    }

    public void setCategories(java.util.List<String> categories) {
        this.categories = categories;
    }

    public Push getPush() {
        return push;
    }

    public void setPush(Push push) {
        this.push = push;
    }

    public Boolean getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Boolean isHidden) {
        this.isHidden = isHidden;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getTimeSolution() {
        return timeSolution;
    }

    public void setTimeSolution(Object timeSolution) {
        this.timeSolution = timeSolution;
    }

}
