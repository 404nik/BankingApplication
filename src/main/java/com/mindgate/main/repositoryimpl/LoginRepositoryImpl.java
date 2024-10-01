package com.mindgate.main.repositoryimpl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.Login;
import com.mindgate.main.repository.LoginRepository;
import com.mindgate.main.rowmapper.LoginRowMapper;

@Repository
public class LoginRepositoryImpl implements LoginRepository {

	private JdbcTemplate jdbcTemplate;

	public LoginRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	private static final String GET_DETAILS =  "SELECT * FROM admin_table WHERE admin_id = ?";
	private static final String SET_LOGIN_COUNT = "UPDATE admin_table SET admin_login_count = ? , admin_login_status = ? WHERE admin_id = ?";
	private static final String LOGIN_QUERY = "SELECT * FROM admin_table WHERE admin_id = ? AND admin_password = ?";
//	private static final String BLOCK_USER = "UPDATE admin_table SET admin_status = 'BLOCKED' WHERE admin_id = ?";

	@Override
	public Login login(Login login) {
		try {
			Object[] params = {login.getUsername(), login.getPassword()};
			System.out.println("Inside Login Repository");
			System.out.println("Before Query");
			System.out.println(login);
			Login loginFromDb = jdbcTemplate.queryForObject(LOGIN_QUERY, new LoginRowMapper(), params);
			System.out.println("After Query");
			if (loginFromDb.getStatus().equals("BLOCKED") || loginFromDb.getStatus().equals("UNAPPROVED")) {
				return loginFromDb;
			}
			else if (loginFromDb.getLoginCount()  < 3 && loginFromDb.getStatus().equals("ACTIVE")) {
				loginFromDb.setLoginCount(0);
				Object[] params1 = {loginFromDb.getLoginCount(), loginFromDb.getStatus(), loginFromDb.getUsername()};
				System.out.println("Before Query Count");
				jdbcTemplate.update(SET_LOGIN_COUNT, params1);
				System.out.println("After Query COunt");
				System.out.println("Login Success");
				return loginFromDb;
			} 
			
		} catch (Exception e) {
			try {
				Object[] params2 = {login.getUsername()};
				Login loginIfExist = jdbcTemplate.queryForObject(GET_DETAILS, new LoginRowMapper(), params2); 
				try {
					if (loginIfExist.getLoginCount() !=3 && !loginIfExist.getStatus().equals("UNAPPROVED")) {
						loginIfExist.setLoginCount(loginIfExist.getLoginCount()+1);
					}
					if (loginIfExist.getLoginCount() == 3) {
						loginIfExist.setStatus("BLOCKED");
					}
					Object[] params = {loginIfExist.getLoginCount(), loginIfExist.getStatus(), loginIfExist.getUsername()};
					jdbcTemplate.update(SET_LOGIN_COUNT, params);
					return loginIfExist;				
				} catch (Exception e2) {
					System.out.println("Error in updating login count");
					return null;
				}
			} catch (Exception e3) {
				return null;
			}
			
		}
		return login;
}

}
