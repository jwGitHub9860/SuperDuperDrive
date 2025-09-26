package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;

import org.apache.ibatis.annotations.*;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM Notes WHERE noteId = #{noteId}")
    Notes getNoteByNoteId(Integer noteId);

    @Select("SELECT * FROM Notes WHERE noteTitle = #{noteTitle}")
    Notes getNoteByNoteTitle(String noteTitle);

    @Select("SELECT * FROM Notes WHERE userId = #{userId}")
    Notes getNoteByUserId(Integer userId);

    @Insert("INSERT INTO Notes (noteTitle, noteDescription, userId) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    Notes createNote(String noteTitle, String noteDescription, Integer userId);

    @Update("UPDATE Notes SET noteTitle = #{noteTitle}, noteDescription = #{noteDescription} WHERE noteId = #{noteId}")
    Notes editNote(String noteTitle, String noteDescription, Integer noteId);

    @Delete("DELETE FROM Notes WHERE noteId = #{noteId}")
    void deleteNoteByNoteId(Integer noteId);
}