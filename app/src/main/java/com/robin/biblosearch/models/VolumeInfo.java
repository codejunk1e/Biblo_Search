package com.robin.biblosearch.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "recent_book_table")
public class VolumeInfo implements Parcelable {

    @PrimaryKey
    @NonNull
    private String id;

    @ColumnInfo(name = "updated_at")
    private Date updatedAt;

    private String thumbnail;

    private Boolean favourite;

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("authors")
    @Expose
    private ArrayList<String> authors = new ArrayList<String>();
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("publishedDate")
    @Expose
    private String publishedDate;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("industryIdentifiers")
    @Expose
    @Ignore
    private List<IndustryIdentifier> industryIdentifiers = new ArrayList<IndustryIdentifier>();
    @SerializedName("readingModes")
    @Expose
    @Ignore
    private ReadingModes readingModes;
    @SerializedName("pageCount")
    @Expose
    private Integer pageCount;
    @SerializedName("printType")
    @Expose
    private String printType;
    @SerializedName("categories")
    @Expose
    @Ignore
    private List<String> categories = new ArrayList<String>();
    @SerializedName("averageRating")
    @Expose
    private Double averageRating;
    @SerializedName("ratingsCount")
    @Expose
    private Integer ratingsCount;
    @SerializedName("maturityRating")
    @Expose
    private String maturityRating;
    @SerializedName("allowAnonLogging")
    @Expose
    private Boolean allowAnonLogging;
    @SerializedName("contentVersion")
    @Expose
    private String contentVersion;
    @SerializedName("panelizationSummary")
    @Expose
    @Ignore
    private PanelizationSummary panelizationSummary;
    @SerializedName("imageLinks")
    @Expose
    @Ignore
    private ImageLinks imageLinks;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("previewLink")
    @Expose
    private String previewLink;
    @SerializedName("infoLink")
    @Expose
    private String infoLink;
    @SerializedName("canonicalVolumeLink")
    @Expose
    private String canonicalVolumeLink;
    @SerializedName("subtitle")
    @Expose
    private String subtitle;
    public final static Parcelable.Creator<VolumeInfo> CREATOR = new Creator<VolumeInfo>() {


        @SuppressWarnings({
                "unchecked"
        })
        public VolumeInfo createFromParcel(Parcel in) {
            return new VolumeInfo(in);
        }

        public VolumeInfo[] newArray(int size) {
            return (new VolumeInfo[size]);
        }

    };

    protected VolumeInfo(Parcel in) {
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.authors, (java.lang.String.class.getClassLoader()));
        this.publisher = ((String) in.readValue((String.class.getClassLoader())));
        this.publishedDate = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.industryIdentifiers, (IndustryIdentifier.class.getClassLoader()));
        this.readingModes = ((ReadingModes) in.readValue((ReadingModes.class.getClassLoader())));
        this.pageCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.printType = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.categories, (java.lang.String.class.getClassLoader()));
        this.averageRating = ((Double) in.readValue((Integer.class.getClassLoader())));
        this.ratingsCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.maturityRating = ((String) in.readValue((String.class.getClassLoader())));
        this.allowAnonLogging = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.contentVersion = ((String) in.readValue((String.class.getClassLoader())));
        this.panelizationSummary = ((PanelizationSummary) in.readValue((PanelizationSummary.class.getClassLoader())));
        this.imageLinks = ((ImageLinks) in.readValue((ImageLinks.class.getClassLoader())));
        this.language = ((String) in.readValue((String.class.getClassLoader())));
        this.previewLink = ((String) in.readValue((String.class.getClassLoader())));
        this.infoLink = ((String) in.readValue((String.class.getClassLoader())));
        this.canonicalVolumeLink = ((String) in.readValue((String.class.getClassLoader())));
        this.subtitle = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.updatedAt = ((Date) in.readValue((Date.class.getClassLoader())));
        this.thumbnail = ((String) in.readValue((String.class.getClassLoader())));
        this.favourite = ((Boolean) in.readValue((Boolean.class.getClassLoader())));

    }

    public VolumeInfo() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public String getAuthorsInString(){
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < authors.size(); i++) {
            builder.append(authors.get(i));
            if (i != authors.size() - 1){
                builder.append(", ");
            }
        }
        return builder.toString();
    }

    public List<String> setAuthorstoList(String authors){
        String[] elements = authors.split(",");
        List<String> fixedLenghtList = Arrays.asList(elements);
        return new ArrayList<String>(fixedLenghtList);
    }


    public void setAuthors(ArrayList<String> authors) {
        this.authors =  authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<IndustryIdentifier> getIndustryIdentifiers() {
        return industryIdentifiers;
    }

    public void setIndustryIdentifiers(List<IndustryIdentifier> industryIdentifiers) {
        this.industryIdentifiers = industryIdentifiers;
    }

    public ReadingModes getReadingModes() {
        return readingModes;
    }

    public void setReadingModes(ReadingModes readingModes) {
        this.readingModes = readingModes;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public String getPrintType() {
        return printType;
    }

    public void setPrintType(String printType) {
        this.printType = printType;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Integer getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(Integer ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public String getMaturityRating() {
        return maturityRating;
    }

    public void setMaturityRating(String maturityRating) {
        this.maturityRating = maturityRating;
    }

    public Boolean getAllowAnonLogging() {
        return allowAnonLogging;
    }

    public void setAllowAnonLogging(Boolean allowAnonLogging) {
        this.allowAnonLogging = allowAnonLogging;
    }

    public String getContentVersion() {
        return contentVersion;
    }

    public void setContentVersion(String contentVersion) {
        this.contentVersion = contentVersion;
    }

    public PanelizationSummary getPanelizationSummary() {
        return panelizationSummary;
    }

    public void setPanelizationSummary(PanelizationSummary panelizationSummary) {
        this.panelizationSummary = panelizationSummary;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPreviewLink() {
        return previewLink;
    }

    public void setPreviewLink(String previewLink) {
        this.previewLink = previewLink;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    public String getCanonicalVolumeLink() {
        return canonicalVolumeLink;
    }

    public void setCanonicalVolumeLink(String canonicalVolumeLink) {
        this.canonicalVolumeLink = canonicalVolumeLink;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }


    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }


    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(title);
        dest.writeList(authors);
        dest.writeValue(publisher);
        dest.writeValue(publishedDate);
        dest.writeValue(description);
        dest.writeList(industryIdentifiers);
        dest.writeValue(readingModes);
        dest.writeValue(pageCount);
        dest.writeValue(printType);
        dest.writeList(categories);
        dest.writeValue(averageRating);
        dest.writeValue(ratingsCount);
        dest.writeValue(maturityRating);
        dest.writeValue(allowAnonLogging);
        dest.writeValue(contentVersion);
        dest.writeValue(panelizationSummary);
        dest.writeValue(imageLinks);
        dest.writeValue(language);
        dest.writeValue(previewLink);
        dest.writeValue(infoLink);
        dest.writeValue(canonicalVolumeLink);
        dest.writeValue(subtitle);
        dest.writeValue(id);
        dest.writeValue(updatedAt);
        dest.writeValue(thumbnail);
        dest.writeValue(favourite);
    }

    public int describeContents() {
        return 0;
    }
}