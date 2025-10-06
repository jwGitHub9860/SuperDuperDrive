package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;

@Service
public class FileService {
    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public final Files getFileByFileId(Integer fileId) {
        return fileMapper.getFileByFileId(fileId);
    }

    public final Files getFileByFileName(String fileName) {
        return fileMapper.getFileByFileName(fileName);
    }

    public List<Files> getAllFilesByUserId(Integer userId) {
        return fileMapper.getAllFilesByUserId(userId);
    }

    public final Files uploadFile(Files file) {
        return fileMapper.uploadFile(file);
    }

    public final Files downloadFile(String fileName) {
        return fileMapper.downloadFile(fileName);
    }

    public final void deleteFile(Integer fileId) {
        fileMapper.deleteFile(fileId);
    }
}