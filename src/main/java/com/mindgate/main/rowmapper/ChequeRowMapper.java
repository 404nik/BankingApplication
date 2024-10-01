package com.mindgate.main.rowmapper;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mindgate.main.domain.Cheque;

public class ChequeRowMapper implements RowMapper<Cheque> {
    
        @Override
        public Cheque mapRow(ResultSet rs, int rowNum) throws SQLException {
            Cheque cheque = new Cheque();
            cheque.setChequeNumber(rs.getInt("cheque_number"));
            cheque.setFromAccountNumber(rs.getInt("from_account_number"));
            cheque.setToAccountNumber(rs.getInt("to_account_number"));
            cheque.setAmount(rs.getDouble("cheque_amount"));
            cheque.setChequeStatus(rs.getString("cheque_status"));
            if (rs.getDate("cheque_issue_date") != null){
                cheque.setChequeIssueDate(rs.getDate("cheque_issue_date").toLocalDate());
            }
            if (rs.getDate("cheque_clearance_date") != null){
                cheque.setChequeClearanceDate(rs.getDate("cheque_clearance_date").toLocalDate());
            }
            return cheque;
        }
    
}
