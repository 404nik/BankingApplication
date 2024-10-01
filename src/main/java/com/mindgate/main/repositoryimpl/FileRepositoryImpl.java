package com.mindgate.main.repositoryimpl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.File;
import com.mindgate.main.repository.FileRepository;
import com.mindgate.main.rowmapper.FileRowMapper;


@Repository
public class FileRepositoryImpl implements FileRepository {
    // private int fileId;
    // private Byte[] customerAadharCard;
    // private Byte[] customerPanCard;
    // private Byte[] customerPhoto;
    // private Byte[] customerSignature;
    private JdbcTemplate jdbcTemplate;

    public FileRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String GET_FILE_FROM_FILE_ID = "SELECT * FROM file_table WHERE file_id = ?";
    private static final String UPLOAD_FILE = "INSERT INTO file_table (file_id, customer_aadhar_card, customer_pan_card, customer_photo, customer_signature) VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE_FILE = "DELETE FROM file_table WHERE file_id = ?";

    @Override
    public File getFileById(int fileId) {
        File file = jdbcTemplate.queryForObject(GET_FILE_FROM_FILE_ID, new FileRowMapper(), fileId);
        return file;
    }

    @Override
    public File uploadFile(File file) {
        Object[] params = {file.getFileId(), file.getCustomerAadharCard(), file.getCustomerPanCard(), file.getCustomerPhoto(), file.getCustomerSignature()};
        int rowsAffected = jdbcTemplate.update(UPLOAD_FILE, params);
        if (rowsAffected > 0) {
            return file;
        }
        return null;
    }

    @Override
    public File deleteFile(int fileId) {
        File file = getFileById(fileId);
        Object[] params = {fileId};
        int rowsAffected = jdbcTemplate.update(DELETE_FILE, params);
        if (rowsAffected > 0) {
            return file;
        }
        return null;
    }

    @Override
    public int generateNextFileId() {
        return jdbcTemplate.queryForObject("select file_seq.nextval FROM DUAL", Integer.class);
    }
    
}
