package com.udacity.jwdnd.course1.cloudstorage.mappers;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM Notes WHERE noteId = #{noteId}")
}