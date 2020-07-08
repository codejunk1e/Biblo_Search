package com.robin.biblosearch.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Epub implements Parcelable
{

    @SerializedName("isAvailable")
    @Expose
    private Boolean isAvailable;
    @SerializedName("acsTokenLink")
    @Expose
    private String acsTokenLink;
    public final static Parcelable.Creator<Epub> CREATOR = new Creator<Epub>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Epub createFromParcel(Parcel in) {
            return new Epub(in);
        }

        public Epub[] newArray(int size) {
            return (new Epub[size]);
        }

    }
            ;

    protected Epub(Parcel in) {
        this.isAvailable = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.acsTokenLink = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Epub() {
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getAcsTokenLink() {
        return acsTokenLink;
    }

    public void setAcsTokenLink(String acsTokenLink) {
        this.acsTokenLink = acsTokenLink;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(isAvailable);
        dest.writeValue(acsTokenLink);
    }

    public int describeContents() {
        return 0;
    }

}