package com.robin.biblosearch.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AccessInfo implements Parcelable
{

    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("viewability")
    @Expose
    private String viewability;
    @SerializedName("embeddable")
    @Expose
    private Boolean embeddable;
    @SerializedName("publicDomain")
    @Expose
    private Boolean publicDomain;
    @SerializedName("textToSpeechPermission")
    @Expose
    private String textToSpeechPermission;
    @SerializedName("epub")
    @Expose
    private Epub epub;
    @SerializedName("pdf")
    @Expose
    private Pdf pdf;
    @SerializedName("webReaderLink")
    @Expose
    private String webReaderLink;
    @SerializedName("accessViewStatus")
    @Expose
    private String accessViewStatus;
    @SerializedName("quoteSharingAllowed")
    @Expose
    private Boolean quoteSharingAllowed;
    public final static Parcelable.Creator<AccessInfo> CREATOR = new Creator<AccessInfo>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AccessInfo createFromParcel(Parcel in) {
            return new AccessInfo(in);
        }

        public AccessInfo[] newArray(int size) {
            return (new AccessInfo[size]);
        }

    }
            ;

    protected AccessInfo(Parcel in) {
        this.country = ((String) in.readValue((String.class.getClassLoader())));
        this.viewability = ((String) in.readValue((String.class.getClassLoader())));
        this.embeddable = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.publicDomain = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.textToSpeechPermission = ((String) in.readValue((String.class.getClassLoader())));
        this.epub = ((Epub) in.readValue((Epub.class.getClassLoader())));
        this.pdf = ((Pdf) in.readValue((Pdf.class.getClassLoader())));
        this.webReaderLink = ((String) in.readValue((String.class.getClassLoader())));
        this.accessViewStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.quoteSharingAllowed = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
    }

    public AccessInfo() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getViewability() {
        return viewability;
    }

    public void setViewability(String viewability) {
        this.viewability = viewability;
    }

    public Boolean getEmbeddable() {
        return embeddable;
    }

    public void setEmbeddable(Boolean embeddable) {
        this.embeddable = embeddable;
    }

    public Boolean getPublicDomain() {
        return publicDomain;
    }

    public void setPublicDomain(Boolean publicDomain) {
        this.publicDomain = publicDomain;
    }

    public String getTextToSpeechPermission() {
        return textToSpeechPermission;
    }

    public void setTextToSpeechPermission(String textToSpeechPermission) {
        this.textToSpeechPermission = textToSpeechPermission;
    }

    public Epub getEpub() {
        return epub;
    }

    public void setEpub(Epub epub) {
        this.epub = epub;
    }

    public Pdf getPdf() {
        return pdf;
    }

    public void setPdf(Pdf pdf) {
        this.pdf = pdf;
    }

    public String getWebReaderLink() {
        return webReaderLink;
    }

    public void setWebReaderLink(String webReaderLink) {
        this.webReaderLink = webReaderLink;
    }

    public String getAccessViewStatus() {
        return accessViewStatus;
    }

    public void setAccessViewStatus(String accessViewStatus) {
        this.accessViewStatus = accessViewStatus;
    }

    public Boolean getQuoteSharingAllowed() {
        return quoteSharingAllowed;
    }

    public void setQuoteSharingAllowed(Boolean quoteSharingAllowed) {
        this.quoteSharingAllowed = quoteSharingAllowed;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(country);
        dest.writeValue(viewability);
        dest.writeValue(embeddable);
        dest.writeValue(publicDomain);
        dest.writeValue(textToSpeechPermission);
        dest.writeValue(epub);
        dest.writeValue(pdf);
        dest.writeValue(webReaderLink);
        dest.writeValue(accessViewStatus);
        dest.writeValue(quoteSharingAllowed);
    }

    public int describeContents() {
        return 0;
    }

}