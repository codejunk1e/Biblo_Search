package com.robin.biblosearch.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageLinks implements Parcelable
{

    @SerializedName("smallThumbnail")
    @Expose
    private String smallThumbnail;

    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    public final static Parcelable.Creator<ImageLinks> CREATOR = new Creator<ImageLinks>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ImageLinks createFromParcel(Parcel in) {
            return new ImageLinks(in);
        }

        public ImageLinks[] newArray(int size) {
            return (new ImageLinks[size]);
        }

    }
            ;

    protected ImageLinks(Parcel in) {
        this.smallThumbnail = ((String) in.readValue((String.class.getClassLoader())));
        this.thumbnail = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ImageLinks() {
    }

    public String getSmallThumbnail() {
        return smallThumbnail;
    }

    public void setSmallThumbnail(String smallThumbnail) {
        this.smallThumbnail = smallThumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(smallThumbnail);
        dest.writeValue(thumbnail);
    }

    public int describeContents() {
        return 0;
    }

}