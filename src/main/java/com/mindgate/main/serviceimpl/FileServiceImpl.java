package com.mindgate.main.serviceimpl;

import org.springframework.stereotype.Service;

import com.mindgate.main.domain.File;
import com.mindgate.main.repository.FileRepository;
import com.mindgate.main.service.FileService;

@Service
public class FileServiceImpl implements FileService {

    private FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public File getFileById(int fileId) {
        return fileRepository.getFileById(fileId);
    }

    @Override
    public File uploadFile(File file) {
        return fileRepository.uploadFile(file);
    }

    @Override
    public File deleteFile(int fileId) {
        return fileRepository.deleteFile(fileId);
    }

    @Override
    public int generateNextFileId() {
        return fileRepository.generateNextFileId();
    }


    
}
