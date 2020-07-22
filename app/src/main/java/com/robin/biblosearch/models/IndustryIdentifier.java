package com.robin.biblosearch.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IndustryIdentifier implements Parcelable
{

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("identifier")
    @Expose
    private String identifier;

    public final static Parcelable.Creator<IndustryIdentifier> CREATOR = new Creator<IndustryIdentifier>() {


        @SuppressWarnings({
                "unchecked"
        })
        public IndustryIdentifier createFromParcel(Parcel in) {
            return new IndustryIdentifier(in);
        }

        public IndustryIdentifier[] newArray(int size) {
            return (new IndustryIdentifier[size]);
        }

    }
            ;

    protected IndustryIdentifier(Parcel in) {
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.identifier = ((String) in.readValue((String.class.getClassLoader())));
    }

    public IndustryIdentifier() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(type);
        dest.writeValue(identifier);
    }

    public int describeContents() {
        return 0;
    }

}