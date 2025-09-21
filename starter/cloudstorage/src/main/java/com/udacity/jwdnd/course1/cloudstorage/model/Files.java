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
    public final String getContenttype() { return contentType; }
    public final String getFilesize() { return filesize; }
    public final byte[] getFiledata() { return filedata; }
    public final Integer getUserId() { return userId; }

    public final String setFilename() { this.filename = filename; }
    public final Integer setFileId() { this.fileId = fileId; }
    public final String setContenttype() { this.contentType = contentType; }
    public final String setFilesize() { this.filesize = filesize; }
    public final byte[] setFiledata() { this.filedata = filedata; }
}