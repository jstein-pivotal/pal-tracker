package io.pivotal.pal.tracker;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/*
Create a new class called JdbcTimeEntryRepository that:

Implements the TimeEntryRepository interface.
Takes DataSource as a constructor argument.
Use the JdbcTimeEntryRepositoryTest to guide your implementation. Keep the following
tips in mind:

Use DataSource to construct a JdbcTemplate object and store the JdbcTemplate object
to a private field.

In the create method use the JDBCTemplate#update() to persist the TimeEntry object to the database.
Retrieve the newly created record from the database using the generated id
(the id can be retrieved using a GeneratedKeyHolder)
 */

public class JdbcTimeEntryRepository implements TimeEntryRepository{

    private JdbcTemplate jdbcTemplate;
    private long nextId = 1L;

    public JdbcTimeEntryRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.id = nextId;
        nextId++;
        jdbcTemplate.update("INSERT INTO time_entries (id, project_id, user_id, date, hours) " +
                                "VALUES (" + timeEntry.id + ", " + timeEntry.projectId + ", " +
                                timeEntry.userId + ", '" + Date.valueOf(timeEntry.getDate()) +
                                "', " + timeEntry.hours + ")");
        return timeEntry;
    }
    public TimeEntry find(long timeEntryId) {
        String query = "SELECT * FROM time_entries " +
                "WHERE id=" + timeEntryId;

        List<TimeEntry> timeEntries = jdbcTemplate.query(query, new TimeEntryRowMapper());
        if (timeEntries.size() >= 1)
            return timeEntries.get(0);
        return null;
    }
    public List<TimeEntry> list() {

        String query = "SELECT * FROM time_entries";
        List<TimeEntry> timeEntries = jdbcTemplate.query(query, new TimeEntryRowMapper());
        return timeEntries;
    }
    public TimeEntry update(long timeEntryId, TimeEntry timeEntry) {
        String query = "UPDATE time_entries " +
                "SET project_id=" + timeEntry.projectId + ", user_id=" + timeEntry.userId + ", date='" +
                timeEntry.date + "', hours=" + timeEntry.hours + " " +
                "WHERE id=" + timeEntryId;
        jdbcTemplate.update(query);
        timeEntry.id = timeEntryId;
        return timeEntry;
    }

    public void delete(long timeEntryId) {
        String query = "DELETE from time_entries WHERE id=" + timeEntryId;
        jdbcTemplate.update(query);
    }

}
