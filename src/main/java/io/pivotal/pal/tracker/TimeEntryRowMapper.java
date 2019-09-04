package io.pivotal.pal.tracker;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TimeEntryRowMapper implements RowMapper<TimeEntry> {

    @Override
    public TimeEntry mapRow(ResultSet row, int rowNum) throws SQLException {
        return new TimeEntry(
                row.getLong("id"),
                row.getLong("project_id"),
                row.getLong("user_id"),
                row.getDate("date").toLocalDate(),
                row.getInt("hours"));
    }
}