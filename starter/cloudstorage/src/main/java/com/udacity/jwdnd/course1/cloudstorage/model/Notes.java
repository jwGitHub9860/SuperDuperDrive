package com.udacity.jwdnd.course1.cloudstorage.model;

public class Notes {
    private final String noteTitle;
    private final Integer noteId;
    private final String noteDescription;
    private final Integer userId;

    public Notes(String noteTitle, Integer noteId, String noteDescription, Integer userId) {
        this.noteTitle = noteTitle;
        this.noteId = noteId;
        this.noteDescription = noteDescription;
        this.userId = userId;
    }
}