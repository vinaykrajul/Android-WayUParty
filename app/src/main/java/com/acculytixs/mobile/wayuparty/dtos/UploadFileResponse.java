
package com.acculytixs.mobile.wayuparty.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UploadFileResponse implements Serializable
{

    @SerializedName("fileURL")
    @Expose
    private String fileURL;
    @SerializedName("fileName")
    @Expose
    private String fileName;
    @SerializedName("contentType")
    @Expose
    private String contentType;
    @SerializedName("status")
    @Expose
    private Object status;
    @SerializedName("uploadedFeedType")
    @Expose
    private Object uploadedFeedType;
    @SerializedName("isSavedGalleryImg")
    @Expose
    private Object isSavedGalleryImg;
    @SerializedName("tempFolderName")
    @Expose
    private Object tempFolderName;
    @SerializedName("fileModified")
    @Expose
    private Boolean fileModified;
    private final static long serialVersionUID = 4232410085025257977L;

    public String getFileURL() {
        return fileURL;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Object getUploadedFeedType() {
        return uploadedFeedType;
    }

    public void setUploadedFeedType(Object uploadedFeedType) {
        this.uploadedFeedType = uploadedFeedType;
    }

    public Object getIsSavedGalleryImg() {
        return isSavedGalleryImg;
    }

    public void setIsSavedGalleryImg(Object isSavedGalleryImg) {
        this.isSavedGalleryImg = isSavedGalleryImg;
    }

    public Object getTempFolderName() {
        return tempFolderName;
    }

    public void setTempFolderName(Object tempFolderName) {
        this.tempFolderName = tempFolderName;
    }

    public Boolean getFileModified() {
        return fileModified;
    }

    public void setFileModified(Boolean fileModified) {
        this.fileModified = fileModified;
    }
}
