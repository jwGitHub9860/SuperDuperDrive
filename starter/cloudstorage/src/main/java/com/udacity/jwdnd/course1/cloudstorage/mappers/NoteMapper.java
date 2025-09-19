package com.udacity.jwdnd.course1.cloudstorage.mappers;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM Notes WHERE noteId = #{noteId}")
    getNoteByNoteId(Integer noteId);

    @Select("SELECT * FROM Notes WHERE noteTitle = #{noteTitle}")
    getNoteByNoteTitle(String noteTitle);

    @Select("SELECT * FROM Notes WHERE userId = #{userId}")
    getNoteByUserId(Integer userId);

    @Delete("DELETE FROM Notes WHERE noteId = #{noteId}")
    void deleteNoteByNoteId(Integer noteId);
}