package com.mindgate.main.repository;

import com.mindgate.main.domain.File;

public interface FileRepository {
    int generateNextFileId();
    File getFileById(int fileId);
    File uploadFile(File file);
    File deleteFile(int fileId);
}