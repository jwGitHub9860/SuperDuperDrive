import java.util.List;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.apache.ibatis.annotations.*;

package com.udacity.jwdnd.course1.cloudstorage.mappers;

@Mapper
public interface FilesMapper {
    @Select("SELECT * FROM Files WHERE fileId = #{fileId}")
    File findFile(Integer fileId);

    @Insert("INSERT INTO Files (filename, contenttype, filesize, userid, filedata) VALUES(#{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    Integer insertFile(File file);

    @Delete("DELETE FROM Files WHERE fileId = #{fileId}")
    void deleteFile(Integer fileId);
}