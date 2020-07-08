package com.robin.biblosearch.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Item implements Parcelable
{

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("id")
    @Expose
    @PrimaryKey
    @NonNull
    private String id;
    @SerializedName("etag")
    @Expose
    private String etag;
    @SerializedName("selfLink")
    @Expose
    private String selfLink;
    @SerializedName("volumeInfo")
    @Expose
    private VolumeInfo volumeInfo;
    @SerializedName("saleInfo")
    @Expose
    @Ignore
    private SaleInfo saleInfo;
    @SerializedName("accessInfo")
    @Expose
    @Ignore
    private AccessInfo accessInfo;
    @SerializedName("searchInfo")
    @Expose
    @Ignore
    private SearchInfo searchInfo;

    private Boolean isFavourite;

    public final static Parcelable.Creator<Item> CREATOR = new Creator<Item>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        public Item[] newArray(int size) {
            return (new Item[size]);
        }

    }
            ;

    protected Item(Parcel in) {
        this.kind = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.etag = ((String) in.readValue((String.class.getClassLoader())));
        this.selfLink = ((String) in.readValue((String.class.getClassLoader())));
        this.volumeInfo = ((VolumeInfo) in.readValue((VolumeInfo.class.getClassLoader())));
        this.saleInfo = ((SaleInfo) in.readValue((SaleInfo.class.getClassLoader())));
        this.accessInfo = ((AccessInfo) in.readValue((AccessInfo.class.getClassLoader())));
        this.searchInfo = ((SearchInfo) in.readValue((SearchInfo.class.getClassLoader())));
        this.isFavourite = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
    }

    public Item() {
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    public SaleInfo getSaleInfo() {
        return saleInfo;
    }

    public void setSaleInfo(SaleInfo saleInfo) {
        this.saleInfo = saleInfo;
    }

    public AccessInfo getAccessInfo() {
        return accessInfo;
    }

    public void setAccessInfo(AccessInfo accessInfo) {
        this.accessInfo = accessInfo;
    }

    public SearchInfo getSearchInfo() {
        return searchInfo;
    }

    public void setSearchInfo(SearchInfo searchInfo) {
        this.searchInfo = searchInfo;
    }

    public Boolean getFavourite() {
        return isFavourite;
    }

    public void setFavourite(Boolean favourite) {
        isFavourite = favourite;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(kind);
        dest.writeValue(id);
        dest.writeValue(etag);
        dest.writeValue(selfLink);
        dest.writeValue(volumeInfo);
        dest.writeValue(saleInfo);
        dest.writeValue(accessInfo);
        dest.writeValue(searchInfo);
        dest.writeValue(isFavourite);
    }

    public int describeContents() {
        return 0;
    }

}