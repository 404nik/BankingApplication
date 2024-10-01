package com.mindgate.main.service;

import com.mindgate.main.domain.File;

public interface FileService {
    File getFileById(int fileId);
    File uploadFile(File file);
    File deleteFile(int fileId);
    int generateNextFileId();
}
