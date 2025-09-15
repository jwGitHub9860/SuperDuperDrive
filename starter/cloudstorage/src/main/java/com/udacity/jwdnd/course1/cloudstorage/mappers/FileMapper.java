import java.util.List;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.apache.ibatis.annotations.*;

package com.udacity.jwdnd.course1.cloudstorage.mappers;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM Files WHERE fileId = #{fileId}")
    File getFileByFileId(Integer fileId);
    
    @Select("SELECT * FROM Files WHERE filename = #{filename}")
    File getFileByFileName(String filename);

    @Insert("INSERT INTO Files (filename, contenttype, filesize, userid, filedata) VALUES(#{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    Integer uploadFile(File file);

    @Select("SELECT * FROM Files WHERE fileId = #{fileId}")
    String[] getFiles();

    @Insert("INSERT INTO Files (filename, contenttype, filesize, userid, filedata) VALUES(#{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    File downloadFile(String filename);

    @Delete("DELETE FROM Files WHERE fileId = #{fileId}")
    void deleteFile(Integer fileId);
}