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

    public final String getNoteTitle() { return noteTitle; }
    public final Integer getNoteId() { return noteId; }
    public final String getNoteDescription() { return noteDescription; }
    public final Integer getUserId() { return userId; }

    /*public final String setNoteTitle() { this.noteTitle = noteTitle; }
    public final Integer setNoteId() { this.noteId = noteId; }
    public final String setNoteDescription() { this.noteDescription = noteDescription; }
    public final Integer setUserId() { this.userId = userId; }*/
}