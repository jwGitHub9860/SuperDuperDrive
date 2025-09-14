import java.util.List;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.apache.ibatis.annotations.*;

package com.udacity.jwdnd.course1.cloudstorage.mappers;

@Mapper
public interface FilesMapper {
    @Select("SELECT * FROM Files WHERE fileId = #{fileId}")