package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;

@Service
public class NoteService {
    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public Notes getNoteByNoteId(Integer noteId) {
        return noteMapper.getNoteByNoteId(noteId);
    }

    public Notes getNoteByNoteTitle(String noteTitle) {
        return noteMapper.getNoteByNoteTitle(noteTitle);
    }

    public Notes getNoteByUserId(Integer userId) {
        return noteMapper.getNoteByUserId(userId);
    }

    public Notes createNote(String noteTitle, String noteDescription, Integer userId) {
        return noteMapper.createNote(noteTitle, noteDescription, userId);
    }

    public Notes editNote(String noteTitle, String noteDescription, Integer noteId) {
        return noteMapper.editNote(noteTitle, noteDescription, noteId);
    }

    public void deleteNoteByNoteId(Integer noteId) {
        return noteMapper.deleteNoteByNoteId(noteId);
    }
}