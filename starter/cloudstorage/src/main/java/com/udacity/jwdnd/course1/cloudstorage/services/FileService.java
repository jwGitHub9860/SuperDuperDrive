package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapperMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.FileMapper;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service

public class FileService {
    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public final Files getFileByFileId(Integer fileId) {
        return fileMapper.getFileByFileId(fileId);
    }

    public final Files getFileByFileName(String filename) {
        return fileMapper.getFileByFileName(filename);
    }

    public final Files uploadFile(Files file) {
        return fileMapper.uploadFile(file);
    }

    public final Files downloadFile(String filename) {
        return fileMapper.downloadFile(filename);
    }

    public final void deleteFile(Integer fileId) {
        fileMapper.deleteFile(fileId);
    }
}