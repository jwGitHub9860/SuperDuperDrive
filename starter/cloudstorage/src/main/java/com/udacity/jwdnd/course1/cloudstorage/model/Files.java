package com.udacity.jwdnd.course1.cloudstorage.model;

public class Files {
    private final String filename;
    private final Integer fileId;
    private final String contenttype;
    private final String filesize;
    private final byte[] filedata;

    public Files(String filename, Integer fileId, String contenttype, String filesize, byte[] filedata) {
        this.filename = filename;
        this.fileId = fileId;
        this.contenttype = contenttype;
        this.filesize = filesize;
        this.filedata = filedata;
    }

    public String getFilename() { return filename; }
    public Integer getFileId() { return fileId; }
    public String getContenttype() { return contenttype; }
    public String getFilesize() { return filesize; }