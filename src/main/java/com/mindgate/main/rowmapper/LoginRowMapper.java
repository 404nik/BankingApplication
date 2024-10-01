package com.mindgate.main.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mindgate.main.domain.Login;

public class LoginRowMapper implements RowMapper<Login> {

    @Override
    public Login mapRow(ResultSet rs, int rowNum) throws SQLException {
        Login login = new Login();
        login.setUsername(rs.getString("admin_id"));
        login.setPassword(rs.getString("admin_password"));
        login.setStatus(rs.getString("admin_login_status"));
        login.setLoginCount(rs.getInt("admin_login_count"));
        return login;
    }
	
}
