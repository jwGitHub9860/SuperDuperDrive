import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapperMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.FileMapper;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

package com.udacity.jwdnd.course1.cloudstorage.services;

@Service

public class FileService {
    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public final File getFileByFileId(Integer fileId) {
        return fileMapper.getFileByFileId(fileId);
    }

    public final File getFileByFileName(String filename) {
        return fileMapper.getFileByFileName(filename);
    }