package com.robin.biblosearch.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PanelizationSummary implements Parcelable
{

    @SerializedName("containsEpubBubbles")
    @Expose
    private Boolean containsEpubBubbles;
    @SerializedName("containsImageBubbles")
    @Expose
    private Boolean containsImageBubbles;
    public final static Parcelable.Creator<PanelizationSummary> CREATOR = new Creator<PanelizationSummary>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PanelizationSummary createFromParcel(Parcel in) {
            return new PanelizationSummary(in);
        }

        public PanelizationSummary[] newArray(int size) {
            return (new PanelizationSummary[size]);
        }

    }
            ;

    protected PanelizationSummary(Parcel in) {
        this.containsEpubBubbles = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.containsImageBubbles = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
    }

    public PanelizationSummary() {
    }

    public Boolean getContainsEpubBubbles() {
        return containsEpubBubbles;
    }

    public void setContainsEpubBubbles(Boolean containsEpubBubbles) {
        this.containsEpubBubbles = containsEpubBubbles;
    }

    public Boolean getContainsImageBubbles() {
        return containsImageBubbles;
    }

    public void setContainsImageBubbles(Boolean containsImageBubbles) {
        this.containsImageBubbles = containsImageBubbles;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(containsEpubBubbles);
        dest.writeValue(containsImageBubbles);
    }

    public int describeContents() {
        return 0;
    }

}

