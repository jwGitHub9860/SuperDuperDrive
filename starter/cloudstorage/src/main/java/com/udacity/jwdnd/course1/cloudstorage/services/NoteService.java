package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;

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
}