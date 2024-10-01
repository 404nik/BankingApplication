package com.mindgate.main.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mindgate.main.domain.File;

public class FileRowMapper implements RowMapper<File> {
	// private int fileId;
    // private Byte[] customerAadharCard;
    // private Byte[] customerPanCard;
    // private Byte[] customerPhoto;
    // private Byte[] customerSignature;
    @Override
    public File mapRow(ResultSet rs, int rowNum) throws SQLException {
        File file = new File();
        file.setFileId(rs.getInt("file_id"));
        file.setCustomerAadharCard(rs.getBytes( "customer_aadhar_card"));
        file.setCustomerPanCard(rs.getBytes("customer_pan_card"));
        file.setCustomerPhoto(rs.getBytes("customer_photo"));
        file.setCustomerSignature(rs.getBytes("customer_signature"));
        return file;}
}
