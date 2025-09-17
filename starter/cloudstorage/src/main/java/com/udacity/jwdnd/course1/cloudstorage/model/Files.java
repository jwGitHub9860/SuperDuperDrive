package com.udacity.jwdnd.course1.cloudstorage.model;

public class Files {
    private final String filename;
    private final Integer fileId;
    private final String contentType;
    private final String filesize;
    private final byte[] filedata;

    public Files(String filename, Integer fileId, String contentType, String filesize, byte[] filedata) {
        this.filename = filename;
        this.fileId = fileId;
        this.contentType = contentType;
        this.filesize = filesize;
        this.filedata = filedata;
    }

    public String getFilename() { return filename; }
    public Integer getFileId() { return fileId; }
    public String getContenttype() { return contentType; }
    public String getFilesize() { return filesize; }
    public byte[] getFiledata() { return filedata; }

    public String setFilename() { this.filename = filename; }
    public Integer setFileId() { this.fileId = fileId; }
    public String setContenttype() { this.contentType = contentType; }
    public String setFilesize() { this.filesize = filesize; }
    public byte[] setFiledata() { this.filedata = filedata; }
}