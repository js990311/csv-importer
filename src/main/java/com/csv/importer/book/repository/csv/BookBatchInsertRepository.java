package com.csv.importer.book.repository.csv;

import com.csv.importer.csv.repository.CsvBatchInsertRepository;
import com.csv.importer.csv.type.CsvEntityType;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Repository

public class BookBatchInsertRepository implements CsvBatchInsertRepository {
    private final JdbcTemplate jdbcTemplate;

    private final String BATCH_INSERT = "INSERT INTO temp_library_book (title, library_id, book_id) VALUES (?,?,?)";

    @Override
    public void batchInsert(List<String[]> rows) {
        jdbcTemplate.batchUpdate(BATCH_INSERT, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                String[] row = rows.get(i);
                ps.setString(1, row[6]);
                ps.setLong(2, Long.parseLong(row[7]));
                ps.setLong(3, Long.parseLong(row[23]));
            }

            @Override
            public int getBatchSize() {
                return rows.size();
            }
        });

    }

    @Override
    public boolean isSupport(CsvEntityType type) {
        return type == CsvEntityType.Book;
    }
}
