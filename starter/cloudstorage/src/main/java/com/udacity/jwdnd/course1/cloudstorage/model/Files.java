package com.udacity.jwdnd.course1.cloudstorage.model;

public class Files {
    private final String filename;
    private final Integer fileId;
    private final String contentType;
    private final String filesize;
    private final byte[] filedata;
    private final Integer userId;

    public Files(String filename, Integer fileId, String contentType, String filesize, byte[] filedata, Integer userId) {
        this.filename = filename;
        this.fileId = fileId;
        this.contentType = contentType;
        this.filesize = filesize;
        this.filedata = filedata;
        this.userId = userId;
    }

    public final String getFilename() { return filename; }
    public final Integer getFileId() { return fileId; }
    public final String getContentType() { return contentType; }
    public final String getFileSize() { return filesize; }
    public final byte[] getFiledata() { return filedata; }
    public final Integer getUserId() { return userId; }

    /*public final void setFilename(String filename) { this.filename = filename; }
    public final void setFileId(Integer fileId) { this.fileId = fileId; }
    public final void setContenttype(String contentType) { this.contentType = contentType; }
    public final void setFilesize(String filesize) { this.filesize = filesize; }
    public final void setFiledata(byte[] filedata) { this.filedata = filedata; }
    public final void setUserId(Integer userId) { this.userId = userId; }*/
}