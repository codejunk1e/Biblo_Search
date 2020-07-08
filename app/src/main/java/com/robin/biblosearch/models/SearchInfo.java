package com.robin.biblosearch.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchInfo implements Parcelable
{

    @SerializedName("textSnippet")
    @Expose
    private String textSnippet;
    public final static Parcelable.Creator<SearchInfo> CREATOR = new Creator<SearchInfo>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SearchInfo createFromParcel(Parcel in) {
            return new SearchInfo(in);
        }

        public SearchInfo[] newArray(int size) {
            return (new SearchInfo[size]);
        }

    }
            ;

    protected SearchInfo(Parcel in) {
        this.textSnippet = ((String) in.readValue((String.class.getClassLoader())));
    }

    public SearchInfo() {
    }

    public String getTextSnippet() {
        return textSnippet;
    }

    public void setTextSnippet(String textSnippet) {
        this.textSnippet = textSnippet;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(textSnippet);
    }

    public int describeContents() {
        return 0;
    }

}