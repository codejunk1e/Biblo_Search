package com.robin.biblosearch.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaleInfo implements Parcelable
{
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("saleability")
    @Expose
    private String saleability;
    @SerializedName("isEbook")
    @Expose
    private Boolean isEbook;
    public final static Parcelable.Creator<SaleInfo> CREATOR = new Creator<SaleInfo>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SaleInfo createFromParcel(Parcel in) {
            return new SaleInfo(in);
        }

        public SaleInfo[] newArray(int size) {
            return (new SaleInfo[size]);
        }

    }
            ;

    protected SaleInfo(Parcel in) {
        this.country = ((String) in.readValue((String.class.getClassLoader())));
        this.saleability = ((String) in.readValue((String.class.getClassLoader())));
        this.isEbook = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
    }

    public SaleInfo() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSaleability() {
        return saleability;
    }

    public void setSaleability(String saleability) {
        this.saleability = saleability;
    }

    public Boolean getIsEbook() {
        return isEbook;
    }

    public void setIsEbook(Boolean isEbook) {
        this.isEbook = isEbook;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(country);
        dest.writeValue(saleability);
        dest.writeValue(isEbook);
    }

    public int describeContents() {
        return 0;
    }

}