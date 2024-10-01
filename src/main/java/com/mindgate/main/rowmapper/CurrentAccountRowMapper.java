package com.mindgate.main.rowmapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mindgate.main.domain.Current;
public class CurrentAccountRowMapper implements RowMapper<Current> {

    @Override
    public Current mapRow(ResultSet rs, int rowNum) throws SQLException {
        Current current = new Current();
        current.setAccountNumber(rs.getInt("account_number"));
        current.setOverDraftBalance(rs.getDouble("account_overdraft_balance"));
        current.setOverdraftInterestRate(rs.getDouble("account_interest_rate"));
        current.setAccountOverdraftLimit(rs.getDouble("account_overdraft_limit"));
        return current;
    }
    
}
