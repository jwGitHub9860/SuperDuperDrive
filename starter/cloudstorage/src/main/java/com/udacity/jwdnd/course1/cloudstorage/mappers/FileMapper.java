package com.udacity.jwdnd.course1.cloudstorage.mappers;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM Files WHERE fileId = #{fileId}")
    Files getFileByFileId(Integer fileId);
    
    @Select("SELECT * FROM Files WHERE filename = #{filename}")
    Files getFileByFileName(String filename);

    @Select("SELECT * FROM Files WHERE userId = #{userId}")
    List<Files> getFileByUserId(Integer userId);

    @Insert("INSERT INTO Files (filename, contenttype, filesize, userid, filedata) VALUES(#{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    Files uploadFile(Files file);

    @Insert("INSERT INTO Files (filename, contenttype, filesize, userid, filedata) VALUES(#{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    Files downloadFile(String filename);

    @Delete("DELETE FROM Files WHERE fileId = #{fileId}")
    void deleteFile(Integer fileId);
}