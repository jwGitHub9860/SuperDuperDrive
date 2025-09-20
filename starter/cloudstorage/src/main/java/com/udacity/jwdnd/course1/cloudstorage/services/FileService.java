package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;

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

    public String[] getFileByUserId(Integer userId) {
        return fileMapper.getFileByUserId(userId);
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